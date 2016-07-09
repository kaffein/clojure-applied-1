(ns ch3.orbital
  (:require [ch1.apollo])
  (:import [ch1.apollo Planet]))

(defn semi-major-axis
  "The planet's average distance from the star"
  [p]
  (/ (+ (:aphelion p) (:perihelion p)) 2))

(def G ,,,)

(defn mu [mass] (* G mass))

(defn orbital-period
  "The time it takes for a planet to make a complete
   orbit around a mass, in seconds"
  [p mass]
  (* Math/PI 2
     (Math/sqrt (/ (Math/pow (semi-major-axis p) 3)
                   (mu mass)))))

;(defn orbital-periods
;  "Given a collection of planets, and a star, return the
;   orbital periods of every planet."
;  [planets star]
;  (let [solar-mass (:mass star)]
;    (map (fn [planet] (orbital-period planet solar-mass)) planets)))


(defn orbital-period-transformation
  "Create a map transformation for planet->orbital-period."
  [star]
  (map #(orbital-period % (:mass star))))

;(defn orbital-periods
;  [planets star]
;  (sequence (orbital-period-transformation star) planets))

;; produce a vector!
;(defn orbital-periods
;  [planets star]
;  (into [] (orbital-period-transformation star) planets))

;; produce a list
(defn orbital-periods
  [planets star]
  (into () (orbital-period-transformation star) planets))

(defn total-moons
  [planets]
  (reduce + 0 (map :moons planets)))

(defn total-moons-transduce
  [planets]
  (transduce (map :moons) + 0 planets))

(defn find-planet
  [planets pname]
  (reduce
   (fn [_ planet]
     (when (= pname (:name planet))
       (reduced planet)))
   planets))

(defn planet?
  [entity]
  (instance? Planet entity))

(defn total-moons-filter
  [entities]
  (reduce + 0
          (map :moons
               (filter planet?
                       entities))))

(defn total-moons-thread
  [entities]
  (->> entities
       (filter planet?)
       (map :moons)
       (reduce + 0)))

(def moons-transform
  (comp (filter planet?) (map :moons)))

(defn total-moons-comp-transduce
  [entities]
  (transduce moons-transform + 0 entities))


