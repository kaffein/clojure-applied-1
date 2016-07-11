(ns shopping.family-async
  (:require [shopping.store :as store]
            [clojure.core.async :refer [>!! chan go-loop <! go]]))

(def my-kids #{:alice :bobbi :cindy :donnie})

(defn maybe?
  "invoke f 1/3 of the time"
  [f]
  (if (= 0 (rand-int 3))
    (f)))

(def shopping-list (ref #{}))
(def assignments (ref {}))
(def shopping-cart (ref #{}))

(defn notify-parent
  [k r _ nv]
  (if (contains? nv :candy)
    (println "there is candy in the cart!")))

(add-watch shopping-cart :candy notify-parent)

(defn assignment
  [child]
  (get @assignments child))

(defn buy-candy []
  (dosync
   (commute shopping-cart conj (store/grab :candy))))

(defn dawdle
  "screw around, get lost, maybe buy candy"
  []
  (let [t (rand-int 5000)]
    (Thread/sleep t)
    (maybe? buy-candy)
    ))

(defn collect-assignment
  [child]
  (let [item (assignment child)]
    (dosync
     (alter shopping-cart conj item)
     (alter assignments dissoc child)
     (ensure shopping-list)

     )
    item))

(defn assign-item-to-child [child]
  (let [item (first @shopping-list)]
    (dosync
     (alter assignments assoc child item)
     (alter shopping-list disj item))
    item))

(defn init []
  (store/init {:eggs  2 :bacon   3 :apples   3
                        :candy 5 :soda    2 :milk     1
                        :bread 3 :carrots 1 :potatoes 1
                        :chees 3})
  (dosync
   (ref-set shopping-list #{:milk :butter :bacon :eggs
                            :carrots :potatoes :cheese :apples})
   (ref-set assignments {})
   (ref-set shopping-cart #{})))

(defn send-child-for-item
  "eventually shop for an item"
  [child item q]
  (println child "is searching for" item)
  (dawdle)
  (collect-assignment child)
  (>!! q child))

(defn report []
  (println "store inventory" @store/inventory)
  (println "shopping-list" @shopping-list)
  (println "assignments" @assignments)
  (println "shopping-cart" @shopping-cart))

(defn go-shopping []
  (init)
  (report)
  (let [kids (chan 10)]
    (doseq [k my-kids]
      (>!! kids k))
    (go-loop [kid (<! kids)]
      (if (seq @shopping-list)
        (do
          (go
            (send-child-for-item kid (assign-item-to-child kid) kids))
          (recur (<! kids)))
        (do
          (println "done shopping.")
          (report))))))
