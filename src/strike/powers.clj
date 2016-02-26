(ns strike.powers)

(defrecord Ability
  [name
   origin
   level

   effect

   function
   ])

(defrecord Attack
  [name
   origin
   level

   cooldown
   range
   area
   cost

   damage
   effect
   special
   ])

(defrecord Triggered
  [name
   origin
   level

   cooldown
   cost

   trigger
   effect
   special
   ])

(defn set-damage [value power]
  (assoc power :damage value))
(defn add-to-damage [value power]
  (assoc power :damage (+ value (:damage power))))
(defn add-to-effect [text power]
  (assoc power :effect (str text (:effect power))))
(defn add-to-special [text power]
  (assoc power :special (str text (:special power))))