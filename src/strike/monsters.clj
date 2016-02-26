(ns strike.monsters
  (:require [strike.powers :as powers]))

(defrecord Monster
  [name
   rank
   class
   level

   max-hp

   size
   speed

   abilities
   powers
   ])

(defn add-to-all-damage [monster number]
  (assoc monster :powers (reduce (partial powers/add-to-damage number) (:powers monster))))
(defn add-to-all-effects [monster text]
  (assoc monster :powers (reduce (partial powers/add-to-effect text) (:powers monster))))
(defn add-to-all-specials [monster text]
  (assoc monster :powers (reduce (partial powers/add-to-special text) (:powers monster))))
