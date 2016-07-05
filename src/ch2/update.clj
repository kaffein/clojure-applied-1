(ns ch2.update
  (:require [medley.core :refer (map-keys)]))

(defn keywordize-entity
  [entity]
  (map-keys keyword entity))

(keywordize-entity {"name"       "Earth"
                    "moon"       1
                    "volumne"    1.08321e12
                    "mass"       5.98219e24
                    "aphelion"   152098232
                    "perihelion" 147098290})

(defn compute-calories [recipe],,,)

(defn- update-calories
  [recipe]
  (assoc recipe :calories (compute-calories recipe)))

(defn include-calories
  [recipe-index]
  (map-vals update-calories recipe-index))
