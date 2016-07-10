(ns ch3.cart
  (:require [ch1.money :refer [make-money +$ *$]]))

(defrecord CatalogItem [number dept desc price])
(defrecord Cart        [number customer line-items settled?])
(defrecord LineItem    [quantity catalog-item price])
(defrecord Customer    [cname email membership-number])

(defn- line-summary
  "Given a LineItem with a CatalogItem, returns a map
   containing the CatalogItem's :dept as :dept and LineItem's :price
   as :total"
  [line-item]
  {:dept  (get-in line-item [:catalog-item :dept])
   :total (:price line-item)})

(defn- dept-total
  [m k v]
  (assoc m k (reduce +$ (map :total v))))

(defn revenue-by-department [carts]
  (->> (filter :settled? carts)
       (mapcat :line-items)
       (map  line-summary)
       (group-by :dept)
       (reduce-kv dept-total)))
