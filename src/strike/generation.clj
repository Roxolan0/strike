(ns strike.generation
  (:require [strike.library :as lib]
            [strike.powers :as powers]
            [strike.monsters :as monsters]))

(defn add-powers-and-abilities [monster trait]
  (-> (assoc monster :abilities (lib/find-content :abilities (:level monster) trait))
      (assoc :powers (lib/find-content :powers (:level monster) trait))))

(defmulti generate-monster (fn [rank, _, _] rank))

(defmethod generate-monster :stooge [_, class, level]
  (as-> (generate-monster :standard class level) $
        (add-powers-and-abilities $ :stooge)
        (assoc $ :max-hp 1)
        (assoc $ :powers (remove (fn [power] (= :encounter (:cooldown power))) (:powers $)))
        (assoc $ :powers (remove (fn [power] (= "Miss Trigger" (:name power))) (:powers $)))
        (monsters/add-to-all-damage $ -1)
        ))

(defmethod generate-monster :goon [_, class, level]
  (let [threshold (if (< level 5) 4 5)]
    (as-> (generate-monster :standard class level) $
          (add-powers-and-abilities $ :goon)
          (assoc $ :max-hp (str "1 hit of >" threshold " dmg or 2 hits"))
          (assoc $ :powers (remove (fn [power] (= :encounter (:cooldown power))) (:powers $)))
          )))

(defmethod generate-monster :standard [rank, class, level]
  (-> (monsters/->Monster
        class, rank, class, level
        (+ 6 (* 2 level))
        1, 6
        nil, nil)
      (add-powers-and-abilities :monster)
      (add-powers-and-abilities class)))