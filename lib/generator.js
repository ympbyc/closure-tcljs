'use strict';

var doctrine = require('doctrine');
var esprima = require('esprima');
var estraverse = require('estraverse');
var Syntax = estraverse.Syntax;

function generate(code) {
  var ast = esprima.parse(code, {
    comment: true,
    attachComment: true,
    loc: true
  });
  var declarations = {};
  ast.body.forEach(function(statement) {
    parseStatement(statement, declarations);
  });
  return outputDeclarationFile(declarations);
}

function parseStatement(statement, declarations) {
  var comments = statement.leadingComments;
  if (!comments || !comments.length) {
    return;
  }
  comments = comments.filter(function(comment) {
    return comment.type === 'Block' && comment.value.charAt(0) === '*';
  });
  var comment = comments[comments.length - 1];
  var doc = doctrine.parse(comment.value, {
    unwrap: true,
    tags: [
      'param',
      'enum',
      'return',
      'private',
      'type'
    ]
  });
  // console.log(doc.tags);

  if (isPrivate(doc.tags)) {
    return;
  }

  var fullname = getFullName(statement);
  if (!fullname) {
    return;
  }
  var name = fullname.pop();
  var moduleName = fullname.join('.');

  var moduleInfo = declarations[moduleName];
  if (!moduleInfo) {
    moduleInfo = declarations[moduleName] = {
      vars: [],
      enums: []
    };
  }

  var enumTag = getEnumTag(doc.tags);
  if (enumTag) {
    moduleInfo.enums.push({
      name: name,
      type: getTsType(enumTag.type),
      keys: getEnumKeys(statement),
      comment: comment
    });
  } else {
    try {
      moduleInfo.vars.push({
        name: name,
        type: getTypeAnnotation(doc.tags, statement),
        comment: comment
      });
    } catch (e) {
      console.error(statement);
      throw e;
    }
  }
}

function isPrivate(tags) {
  return tags.some(function(tag) {
    return tag.title === 'private';
  });
}

function getEnumTag(tags) {
  for (var i = 0; i < tags.length; i++) {
    if (tags[i].title === 'enum') {
      return tags[i];
    }
  }
  return null;
}

function getEnumKeys(statement) {
  return statement.expression.right.properties.map(function(property) {
    return property.key.name;
  });
}

function getTsType(type) {
  if (!type) {
    // no type property if doctrine fails to parse type.
    return 'any';
  }

  // TODO: use doctrine.Styntax
  switch (type.type) {
    case 'NameExpression':
      return type.name;
    case 'AllLiteral':
    case 'NullableLiteral':
      return 'any';
    case 'OptionalType':
      return getTsType(type.expression);
    case 'NullableType':
    case 'NonNullableType':
      // Every types in TypeScript is a nullable.
      // There is not any non-nullables.
      return getTsType(type.expression);
    case 'UnionType':
      // TODO: Now just select a first element.
      return getTsType(type.elements[0]);
    case 'RestType':
      return getTsType(type.expression) + '[]';
    case 'TypeApplication':
      return getTsType(type.expression) + '<' + type.applications.map(function(app) {
        return getTsType(app);
      }).join(', ') + '>';
    case 'FunctionType':
      var params = type.params.map(function(paramType, index) {
        return {
          type: getTsType(paramType),
          name: 'arg' + index
        };
      });
      return toFunctionTypeString(params, getTsType(type.result));
    default:
      throw new Error('Unexpected type: ' + type.type);
  }
}

function getArgName(tag) {
  if (!tag.type) {
    return tag.name;
  }

  switch (tag.type.type) {
    case 'OptionalType':
      return tag.name + '?';
    case 'RestType':
      return '...' + tag.name;
    default:
      return tag.name;
  }
}

function getTypeAnnotation(tags, statement) {
  var type = {
    enum: null,
    params: [],
    type: null,
    returns: null
  };

  tags.forEach(function(tag) {
    switch (tag.title) {
      case 'enum':
        type.enum = {type: getTsType(tag.type)};
        break;
      case 'type':
        type.type = {type: getTsType(tag.type)};
        break;
      case 'param':
        type.params.push({type: getTsType(tag.type), name: getArgName(tag)});
        break;
      case 'return':
        type.returns = getTsType(tag.type);
        break;
    }
  });

  if (type.type) {
      return type.type.type;
  } else if (isAssignement(statement)) {
    if (isFunctionAssignement(statement)) {
      if (type.returns || type.params.length > 0) {
        return toFunctionTypeString(type.params, type.returns);
      } else {
        return '() => void';
      }
    } else {
      return 'any';
    }
  } else {
    console.error(tags);
    throw new Error('Unsupported type annotations.');
  }
}

function toFunctionTypeString(params, ret) {
  // TODO: paramがないやつもあるからdocからは推測できない
  var args = params.map(function(param) {
    return param.name + ': ' + param.type;
  }).join(', ');
  var returns = ret ? ret : 'void';
  return '(' + args + ') => ' + returns;
}

function isAssignement(statement) {
  return statement.type === Syntax.ExpressionStatement &&
    statement.expression.type === Syntax.AssignmentExpression;
}

function isFunctionAssignement(statement) {
  return isAssignement(statement) &&
    statement.expression.right.type === Syntax.FunctionExpression;
}

function getFullName(statement) {
  switch (statement.type) {
    case Syntax.ExpressionStatement:
      return getFullNameFromExpressionStatement(statement);
    case Syntax.FunctionDeclaration:
      return getFullNameFromFunctionDeclaration(statement);
    case Syntax.VariableDeclaration:
      return getFullNameFromVariableDeclaration(statement);
    default:
      throw new Error('Unexpected statement');
  }
}

function getFullNameFromFunctionDeclaration(statement) {
  console.log(statement);
  throw new Error('TODO');
}

function getFullNameFromVariableDeclaration(statement) {
  // No variable declarations in Closure Library
  return null;
}

function getFullNameFromExpressionStatement(statement) {
  var expression = statement.expression;
  var targetExpression;
  switch (expression.type) {
    case Syntax.AssignmentExpression:
      targetExpression = expression.left;
      break;
    case Syntax.MemberExpression:
      targetExpression = expression;
      break;
    case Syntax.CallExpression:
      // Like: @fileoverview => goog.provide('foo')
      return null;
    default:
      console.error(statement);
      throw new Error('Unexpected expression: ' + expression.type);
  }

  var fullname = [];
  var error = false;
  estraverse.traverse(targetExpression, {
    enter: function(node, parent) {
      if (node.computed || node.type === Syntax.Literal) {
        error = true;
        this.break();
      }
    },
    leave: function(node, parent) {
      if (node.type === Syntax.Identifier) {
        fullname.push(node.name);
      }
    }
  });

  if (error) {
    throw new Error('');
  }

  return fullname;
}

function outputDeclarationFile(declarations) {
  var output = [];
  for (var name in declarations) {
    output.push(outputModule(declarations[name], name));
  }
  return output.filter(function(str) {return !!str;}).join('\n\n');
}

function outputModule(moduleDeclaration, name) {
  var output = ['declare module ' + name + ' {'];
  var indent = '    ';
  output.push(moduleDeclaration.enums.map(outputEnumDeclaration.bind(null, indent)).join('\n'));
  output.push(moduleDeclaration.vars.map(outputVarDeclaration.bind(null, indent)).join('\n'));
  output.push('}');
  return output.filter(function(section) {return !!section;}).join('\n');
}

function outputEnumDeclaration(indent, declare) {
  var output = ('/*' + declare.comment.value + '*/').split('\n');
  output.push('export interface ' + declare.name + ' {');
  declare.keys.forEach(function(key) {
    output.push('    ' + key + ': ' + declare.type + ';');
  });
  output.push('}');
  return '\n' + output.map(function(line) {return indent + line;}).join('\n');
}

function outputVarDeclaration(indent, declare) {
  var output = ('/*' + declare.comment.value + '*/').split('\n');
  output.push('export var ' + declare.name + ': ' + declare.type + ';');
  return '\n' + output.map(function(line) {return indent + line;}).join('\n');
}

module.exports = {
  generate: generate
};