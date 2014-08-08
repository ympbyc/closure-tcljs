(ns goog.array)

(defprotocol ArrayLike 
)

(ann peek (All [T] [(U (Array T) goog.array.ArrayLike) -> T]))

(ann indexOf (All [T] [(U (Array T) goog.array.ArrayLike) T number -> number]))

(ann lastIndexOf (All [T] [(U (Array T) goog.array.ArrayLike) T number -> number]))

(ann forEach (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> any] S -> nil]))

(ann forEachRight (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> any] S -> nil]))

(ann filter (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> (Array T)]))

(ann map (All [THIS VALUE RESULT] [(U (Array VALUE) goog.array.ArrayLike) [VALUE number any -> RESULT] THIS -> (Array RESULT)]))

(ann reduce (All [T S R] [(U (Array T) goog.array.ArrayLike) [R T number any -> R] any S -> R]))

(ann reduceRight (All [T S R] [(U (Array T) goog.array.ArrayLike) [R T number any -> R] any S -> R]))

(ann some (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> boolean]))

(ann every (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> boolean]))

(ann count (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> number]))

(ann find (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> T]))

(ann findIndex (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> number]))

(ann findRight (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> T]))

(ann findIndexRight (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] Object -> number]))

(ann contains (All [] [goog.array.ArrayLike any -> boolean]))

(ann isEmpty (All [] [goog.array.ArrayLike -> boolean]))

(ann clear (All [] [goog.array.ArrayLike -> nil]))

(ann insert (All [T] [(Array T) T -> nil]))

(ann insertAt (All [] [goog.array.ArrayLike any number -> nil]))

(ann insertArrayAt (All [] [goog.array.ArrayLike goog.array.ArrayLike number -> nil]))

(ann insertBefore (All [T] [(Array T) T T -> nil]))

(ann remove (All [T] [(U (Array T) goog.array.ArrayLike) T -> boolean]))

(ann removeAt (All [] [goog.array.ArrayLike number -> boolean]))

(ann removeIf (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> boolean] S -> boolean]))

(ann concat (All [] [any * -> (Array any)]))

(ann join (All [T] [(Array T) * -> (Array T)]))

(ann toArray (All [T] [(U (Array T) goog.array.ArrayLike) -> (Array T)]))

(ann clone (All [T] [(U (Array T) goog.array.ArrayLike) -> (Array T)]))

(ann extend (All [VALUE] [(Array VALUE) (U (Array VALUE) VALUE) * -> nil]))

(ann splice (All [T] [(U (Array T) goog.array.ArrayLike) (U number void) number T * -> (Array T)]))

(ann slice (All [T] [(U (Array T) goog.array.ArrayLike) number number -> (Array T)]))

(ann removeDuplicates (All [T] [(U (Array T) goog.array.ArrayLike) (Array any) [T -> string] -> nil]))

(ann binarySearch (All [TARGET VALUE] [(U (Array VALUE) goog.array.ArrayLike) TARGET [TARGET VALUE -> number] -> number]))

(ann binarySelect (All [THIS VALUE] [(U (Array VALUE) goog.array.ArrayLike) [VALUE number any -> number] THIS -> number]))

(ann sort (All [T] [(Array T) [T T -> number] -> nil]))

(ann stableSort (All [T] [(Array T) [T T -> number] -> nil]))

(ann sortObjectsByKey (All [] [(Array Object) string Function -> nil]))

(ann isSorted (All [T] [(Array T) [T T -> number] boolean -> boolean]))

(ann equals (All [] [goog.array.ArrayLike goog.array.ArrayLike Function -> boolean]))

(ann compare3 (All [VALUE] [(U (Array VALUE) goog.array.ArrayLike) (U (Array VALUE) goog.array.ArrayLike) [VALUE VALUE -> number] -> number]))

(ann defaultCompare (All [VALUE] [VALUE VALUE -> number]))

(ann defaultCompareEquality (All [] [any any -> boolean]))

(ann binaryInsert (All [VALUE] [(U (Array VALUE) goog.array.ArrayLike) VALUE [VALUE VALUE -> number] -> boolean]))

(ann binaryRemove (All [VALUE] [(U (Array VALUE) goog.array.ArrayLike) VALUE [VALUE VALUE -> number] -> boolean]))

(ann bucket (All [T S] [(Array T) [T number (Array T) -> any] S -> Object]))

(ann toObject (All [T S] [(U (Array T) goog.array.ArrayLike) [T number any -> string] S -> (Object T)]))

(ann range (All [] [number number number -> (Array number)]))

(ann repeat (All [VALUE] [VALUE number -> (Array VALUE)]))

(ann flatten (All [] [any * -> (Array any)]))

(ann rotate (All [T] [(Array T) number -> (Array T)]))

(ann moveItem (All [] [(U (Array any) Arguments (HMap :mandatory {:length number})) number number -> nil]))

(ann zip (All [] [goog.array.ArrayLike * -> (Array (Array any))]))

(ann shuffle (All [] [(Array any) [ -> number] -> nil]))
