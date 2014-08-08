(ns goog.dom)

(defprotocol Appendable 
)

(ann-datatype DomHelper (All [] [
    constructor :- [Document ->]

    
    getDomHelper :- (All [] [Node -> goog.dom.DomHelper])
    
    setDocument :- (All [] [Document -> nil])
    
    getDocument :- (All [] [ -> Document])
    
    getElement :- (All [] [(U string Element) -> Element])
    
    getRequiredElement :- (All [] [string -> Element])
    
    $ :- (All [] [(U string Element) -> Element])
    
    getElementsByTagNameAndClass :- (All [] [string string (U Document Element) -> (HMap :mandatory {:length number})])
    
    getElementsByClass :- (All [] [string (U Element Document) -> (HMap :mandatory {:length number})])
    
    getElementByClass :- (All [] [string (U Element Document) -> Element])
    
    getRequiredElementByClass :- (All [] [string (U Element Document) -> Element])
    
    $$ :- (All [] [string string Element -> (HMap :mandatory {:length number})])
    
    setProperties :- (All [] [Element Object -> nil])
    
    getViewportSize :- (All [] [Window -> goog.math.Size])
    
    getDocumentHeight :- (All [] [ -> number])
    
    createDom :- (All [] [string (U Object string) goog.dom.Appendable * -> Element])
    
    $dom :- (All [] [string (U Object string) goog.dom.Appendable * -> Element])
    
    createElement :- (All [] [string -> Element])
    
    createTextNode :- (All [] [(U number string) -> Text])
    
    createTable :- (All [] [number number boolean -> Element])
    
    htmlToDocumentFragment :- (All [] [string -> Node])
    
    isCss1CompatMode :- (All [] [ -> boolean])
    
    getWindow :- (All [] [ -> Window])
    
    getDocumentScrollElement :- (All [] [ -> Element])
    
    getDocumentScroll :- (All [] [ -> goog.math.Coordinate])
    
    getActiveElement :- (All [] [Document -> Element])
    
    appendChild :- (All [] [Node Node -> nil])
    
    append :- (All [] [Node goog.dom.Appendable * -> nil])
    
    canHaveChildren :- (All [] [Node -> boolean])
    
    removeChildren :- (All [] [Node -> nil])
    
    insertSiblingBefore :- (All [] [Node Node -> nil])
    
    insertSiblingAfter :- (All [] [Node Node -> nil])
    
    insertChildAt :- (All [] [Element Node number -> nil])
    
    removeNode :- (All [] [Node -> Node])
    
    replaceNode :- (All [] [Node Node -> nil])
    
    flattenElement :- (All [] [Element -> (U Element void)])
    
    getChildren :- (All [] [Element -> (U (Array any) NodeList)])
    
    getFirstElementChild :- (All [] [Node -> Element])
    
    getLastElementChild :- (All [] [Node -> Element])
    
    getNextElementSibling :- (All [] [Node -> Element])
    
    getPreviousElementSibling :- (All [] [Node -> Element])
    
    getNextNode :- (All [] [Node -> Node])
    
    getPreviousNode :- (All [] [Node -> Node])
    
    isNodeLike :- (All [] [any -> boolean])
    
    isElement :- (All [] [any -> boolean])
    
    isWindow :- (All [] [any -> boolean])
    
    getParentElement :- (All [] [Element -> Element])
    
    contains :- (All [] [Node Node -> boolean])
    
    compareNodeOrder :- (All [] [Node Node -> number])
    
    findCommonAncestor :- (All [] [Node * -> Node])
    
    getOwnerDocument :- (All [] [Node -> Document])
    
    getFrameContentDocument :- (All [] [Element -> Document])
    
    getFrameContentWindow :- (All [] [Element -> Window])
    
    setTextContent :- (All [] [Node (U string number) -> nil])
    
    getOuterHtml :- (All [] [Element -> string])
    
    findNode :- (All [] [Node [Node -> boolean] -> (U Node void)])
    
    findNodes :- (All [] [Node [Node -> boolean] -> (Array Node)])
    
    isFocusableTabIndex :- (All [] [Element -> boolean])
    
    setFocusableTabIndex :- (All [] [Element boolean -> nil])
    
    isFocusable :- (All [] [Element -> boolean])
    
    getTextContent :- (All [] [Node -> string])
    
    getNodeTextLength :- (All [] [Node -> number])
    
    getNodeTextOffset :- (All [] [Node Node -> number])
    
    getNodeAtOffset :- (All [] [Node number Object -> Node])
    
    isNodeList :- (All [] [Object -> boolean])
    
    getAncestorByTagNameAndClass :- (All [] [Node (U goog.dom.TagName string) string -> Element])
    
    getAncestorByClass :- (All [] [Node string -> Element])
    
    getAncestor :- (All [] [Node [Node -> boolean] boolean number -> Node])
]))

(ann getDomHelper (All [] [(U Node Window) -> goog.dom.DomHelper]))

(ann getDocument (All [] [ -> Document]))

(ann getElement (All [] [(U string Element) -> Element]))

(ann getRequiredElement (All [] [string -> Element]))

(ann $ (All [] [(U string Element) -> Element]))

(ann getElementsByTagNameAndClass (All [] [string string (U Document Element) -> (HMap :mandatory {:length number})]))

(ann getElementsByClass (All [] [string (U Document Element) -> (HMap :mandatory {:length number})]))

(ann getElementByClass (All [] [string (U Element Document) -> Element]))

(ann getRequiredElementByClass (All [] [string (U Element Document) -> Element]))

(ann $$ (All [] [string string Element -> (HMap :mandatory {:length number})]))

(ann setProperties (All [] [Element Object -> nil]))

(ann getViewportSize (All [] [Window -> goog.math.Size]))

(ann getDocumentHeight (All [] [ -> number]))

(ann getPageScroll (All [] [Window -> goog.math.Coordinate]))

(ann getDocumentScroll (All [] [ -> goog.math.Coordinate]))

(ann getDocumentScrollElement (All [] [ -> Element]))

(ann getWindow (All [] [Document -> Window]))

(ann createDom (All [] [string (U Object (Array string) string) (U Object string Array NodeList) * -> Element]))

(ann $dom (All [] [string (U string Object) (U Object string Array NodeList) * -> Element]))

(ann createElement (All [] [string -> Element]))

(ann createTextNode (All [] [(U number string) -> Text]))

(ann createTable (All [] [number number boolean -> Element]))

(ann htmlToDocumentFragment (All [] [string -> Node]))

(ann isCss1CompatMode (All [] [ -> boolean]))

(ann canHaveChildren (All [] [Node -> boolean]))

(ann appendChild (All [] [Node Node -> nil]))

(ann append (All [] [Node goog.dom.Appendable * -> nil]))

(ann removeChildren (All [] [Node -> nil]))

(ann insertSiblingBefore (All [] [Node Node -> nil]))

(ann insertSiblingAfter (All [] [Node Node -> nil]))

(ann insertChildAt (All [] [Element Node number -> nil]))

(ann removeNode (All [] [Node -> Node]))

(ann replaceNode (All [] [Node Node -> nil]))

(ann flattenElement (All [] [Element -> (U Element void)]))

(ann getChildren (All [] [Element -> (U (Array any) NodeList)]))

(ann getFirstElementChild (All [] [Node -> Element]))

(ann getLastElementChild (All [] [Node -> Element]))

(ann getNextElementSibling (All [] [Node -> Element]))

(ann getPreviousElementSibling (All [] [Node -> Element]))

(ann getNextNode (All [] [Node -> Node]))

(ann getPreviousNode (All [] [Node -> Node]))

(ann isNodeLike (All [] [any -> boolean]))

(ann isElement (All [] [any -> boolean]))

(ann isWindow (All [] [any -> boolean]))

(ann getParentElement (All [] [Element -> Element]))

(ann contains (All [] [Node Node -> boolean]))

(ann compareNodeOrder (All [] [Node Node -> number]))

(ann findCommonAncestor (All [] [Node * -> Node]))

(ann getOwnerDocument (All [] [(U Node Window) -> Document]))

(ann getFrameContentDocument (All [] [Element -> Document]))

(ann getFrameContentWindow (All [] [Element -> Window]))

(ann setTextContent (All [] [Node (U string number) -> nil]))

(ann getOuterHtml (All [] [Element -> string]))

(ann findNode (All [] [Node [Node -> boolean] -> (U Node void)]))

(ann findNodes (All [] [Node [Node -> boolean] -> (Array Node)]))

(ann isFocusableTabIndex (All [] [Element -> boolean]))

(ann setFocusableTabIndex (All [] [Element boolean -> nil]))

(ann isFocusable (All [] [Element -> boolean]))

(ann getTextContent (All [] [Node -> string]))

(ann getRawTextContent (All [] [Node -> string]))

(ann getNodeTextLength (All [] [Node -> number]))

(ann getNodeTextOffset (All [] [Node Node -> number]))

(ann getNodeAtOffset (All [] [Node number Object -> Node]))

(ann isNodeList (All [] [Object -> boolean]))

(ann getAncestorByTagNameAndClass (All [] [Node (U goog.dom.TagName string) string -> Element]))

(ann getAncestorByClass (All [] [Node string -> Element]))

(ann getAncestor (All [] [Node [Node -> boolean] boolean number -> Node]))

(ann getActiveElement (All [] [Document -> Element]))

(ann getPixelRatio (All [] [ -> number]))
