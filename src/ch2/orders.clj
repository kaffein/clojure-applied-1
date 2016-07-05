(ns ch2.orders)

;; (defn new-orders [] [])
;; (defn new-orders [] '())

(def new-orders clojure.lang.PersistentQueue/EMPTY)

(defn add-order [orders order]
  (conj orders order))

;; bad for lists :(
;(defn add-order [orders order]
;  (concat orders (list order)))

(defn cook [] ,,,)

;(defn cook-order [orders]
;  (cook first(orders)) (rest orders))

;; queue friendly cook-order function
(defn cook-order [orders]
  (cook (peek orders))
  (pop orders))
