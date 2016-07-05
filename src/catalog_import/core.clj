(ns catalog-import.core)

(defn import-catalog [data]
  (reduce #(conj %1 %2) [] data))

;; localized mutability to make things faster?
(defn import-catalog-fast [data]
  (persistent!
   (reduce #(conj! %1 %2) (transient []) data)))



