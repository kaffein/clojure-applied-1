(ns ch1.validate
  (:require [schema.core :as s]))

(s/defrecord Ingredient
    [name     :- s/Str
     quantity :- s/Int
     unit     :- s/Keyword])

(s/defrecord Recipe
    [name        :- s/Str
     description :- s/Str
     ingredients :- [Ingredient]
     steps       :- [s/Str]
     servings    :- s/Int])

(def spaghetti-tacos
  (map->Recipe
   {:name        "Spaghetti tacos"
    :description "It's spaghetti... in a taco."
    :ingredients [(->Ingredient "Spaghetti" 1 :lb)
                  (->Ingredient "Spaghetti sauce" 16 :oz)
                  (->Ingredient "Taco shell" 12 :shell)]
    :steps       ["Cook spaghetti according to box."
                  "Heat spaghetti sauce until warm."
                  "Mix spaghetti and sauce."
                  "Put spaghetti in taco shells and serve."]
    :servings    4}))
