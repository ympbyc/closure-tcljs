closure-tcljs
====

> Generates core.typed annotation files from [Closure Library JSDoc annotations](https://developers.google.com/closure/compiler/docs/js-for-compiler).

**WIP**

## Example

From this JavaScript code with annotations,
```javascript
/**
 * @typedef {Array|NodeList|Arguments|{length: number}}
 */
goog.array.ArrayLike;


/**
 * Returns the last element in an array without removing it.
 * @param {Array.<T>|goog.array.ArrayLike} array The array.
 * @return {T} Last item in array.
 * @template T
 */
goog.array.peek = function(array) {
  return array[array.length - 1];
};
```

closure-tcljs generates this declaration file (d.clj)

```clojure
(ns goog.array)

(defprotocol ArrayLike)

(ann peek (All [T] [(U (Array T) goog.array.ArrayLike) -> T]))
```

## Usage

```bash
$ bin/closure-tcljs some-jsdoced-code.js
$ ls
some-jsdoced-code.d.clj
some-jsdoced-code.js
```

## Project status

WIP. Not yet usable from core.typed

### Implemented

* Variable with `@type`
* Function with `@param` and `@return`
* Namespace to tcljs `ns`
* Classes (`@constructor` and `@extends`) to tcljs datatype
* Generic type like `(Array number)`
* Generic classes and function with `@template`
* Union type
* Record type
* Rest parameters in `@param` and FunctionType
* Exclude `@private` definitions
* `@typedef` (partialy)
* Ignore features TypeScript doesn't have
    * `@this`, `new` of function type
    * Nullable, Non-Nullable

### TODO

* Interfaces
* `@lends`
* Enum with `@enum` to TypeScript `Interface`
* Dependencies of Closure Library files
* Arity overloading with `IFn` (Optional Parameters)
* Derive annotations for constructor functions such as `Number.` from corresponding classes' constructor
* `Object.<string, Some>` this should be easy
* One stop build system with Grunt or Gulp


## LICENSE

[MIT](http://opensource.org/licenses/mit-license.php)
(original repo doesn't have LICENCE.md but package.json says it's MIT)

+ **Original Work** Copyright (c) teppeis https://github.com/teppeis
+ **Modified Work** Copyright 2014 Minori Yamashita <ympbyc@gmail.com>

TODO: Add LICENSE.md after confirming w/ teppeis
