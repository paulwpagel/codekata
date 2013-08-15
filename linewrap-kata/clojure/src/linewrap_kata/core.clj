(ns linewrap-kata.core
  (:require [clojure.string :as str])
  (:use [midje.sweet :only [unfinished]]))

;;;; Breaks a string into multiple lines,
;;;; split after the last word that fits into a line

;;; Reference implementation w/o lazy processing.

(declare combine-lines)

;; String Natural -> String
(defn break-string
  "Takes the string and returns the broken string."
  [s breakpoint]
  (as-> s _ 
      (str/split _ #"\s")
      (combine-lines _ breakpoint)
      (str/join "\n" _)))

;; [Strings] Natural -> [Strings]
(defn combine-lines
  "Combines strings in a collection into lines not longer than the breakpoint."
  [coll breakpoint]
    (reduce (fn [result el]
              (let [combined (str (last result) " " el)
                    comb-len (count combined)]
                (if (<= comb-len breakpoint)
                  (conj (pop result) combined)
                  (conj result el))))
            [(first coll)]
            (rest coll)))
