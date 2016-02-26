(ns strike.library
  (:require [strike.powers :as powers]
            [strike.monsters :as monsters]))

  (def content
    {:abilities
     [
      ; All monsters
      (powers/->Ability
        "Extra Damage", :monster, [6 8]
        "+1 damage to all attacks."
        (fn [monster] (monsters/add-to-all-damage monster 1)))

      (powers/->Ability
        "Recharge", :monster, [6 8 10 12]
        "When you roll a 5 or 6 on a single-target attack or a 6 on a multi-target attack, you recharge one Encounter Power."
        (fn [monster] (monsters/add-to-all-effects monster "If you rolled a 6, or if this attack has a single target and you rolled a 5, you recharge one Encounter Power.")))

      (powers/->Ability
        "Super Damage", :monster, [10 12]
        "+2 damage to all attacks."
        (fn [monster] (monsters/add-to-all-damage monster 2)))

      (powers/->Ability
        "Pathetic", :stooge, [2 4 6 8 10 12]
        "You automatically fail all Saving Throws."
        nil)

      (powers/->Ability
        "Weakling", :stooge, [2 4 6 8 10 12]
        "All damage in the effect lines of your attacks is reduced by 1."
        nil) ;TODO actually do this.

      ; Blaster (monster)
      (powers/->Ability
        "Friendly Blaster", :blaster, [2 4 6 8 10 12]
        "You may omit one ally from an area attack."
        nil)

      (powers/->Ability
        "Damage on a Miss", :blaster, [2 4 6 8 10 12]
        "When you miss, the target takes 1 damage anyway."
        (fn [monster] (monsters/add-to-all-specials monster "If you miss, the target takes 1 damage anyway.")))

      (powers/->Ability
        "Deadly Blaster", :blaster, [4 8]
        "You may add one enemy to an area attack if it is adjacent to the area."
        nil)
      ]

     :powers
     [(powers/->Attack
        "Blast", :blaster, [2 4 6 8 10 12]
        :at-will, [{:ranged 10}], 1, nil
        1, "Ongoing 2 Damage (save ends).", nil)

      (powers/->Attack
        "Make Space", :blaster, [2 4 6 8 10 12]
        :at-will, [:burst], 2, nil
        1, "Push target 3 squares.", nil)

      (powers/->Attack
        "Big Blast", :blaster, [2 4 6 8 10 12]
        :encounter, [{:ranged 10}], 3, nil
        2, "Target is knocked Prone and takes Ongoing 2 Damage (save ends).", nil)

      (powers/->Triggered
        "Miss Trigger", :blaster, [2 4 6 8 10 12]
        :at-will, nil
        "When an enemy misses you"
        "Create a 3x3 zone including the enemy, lasting until the end of your next turn. Any creature except you that enters the zone or ends its turn there takes 1 damage.", nil)
      ]})

(defn find-content [type level trait]
  (->> (filter (fn [ability] (some #{level} (flatten [(:level ability)]))) (type content))
       (filter (fn [ability] (some #{trait} (flatten [(:origin ability)]))))))