(ns ch1.protocols
  (:require [ch1.validate]
            [ch1.money :refer (+$ zero-dollars)])
  (:import [ch1.validate Recipe Ingredient]))

(defrecord Store [,,,])

(defn cost-of [store ingredient] ,,,)

(defprotocol Cost
  (cost [entity store]))

(extend-protocol Cost
  Recipe
  (cost [recipe store]
    (reduce +$ zero-dollars
            (map #(cost % store) (:ingredients recipe))))
  Ingredient
  (cost [ingredient store]
    (cost-of store ingredient)))
