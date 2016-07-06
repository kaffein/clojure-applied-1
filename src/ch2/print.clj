(ns ch2.print
  (:require [ch2.pair])
  (:import [ch2.pair Pair]))

(defmethod print-method Pair
  [pair w]
  (.write w "#ch2.pair.Pair")
  (print-method (vec (seq pair)) w))

(defmethod print-dup Pair
  [pair w]
  (print-method pair w))
