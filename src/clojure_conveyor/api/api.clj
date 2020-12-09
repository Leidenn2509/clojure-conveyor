(ns clojure-conveyor.api.api)
(require '[clojure.reflect :as cr])

(def ^:private node-name-n 1)

(def ^:private input-channels-types-n 2)
(def ^:private input-channels-size-n 3)
(def ^:private input-channels-n 4)

(def ^:private output-channels-types-n 5)
(def ^:private output-channels-size-n 6)
(def ^:private output-channels-n 7)

(def ^:private func-n 8)

(defn node [name input-channels-types output-channels-types func]
    (vector ::node
          name
          input-channels-types
          (count input-channels-types) []

          output-channels-types
          (count output-channels-types) []

          func))


(def test-node
    (node "node"
          (list (cr/typename Long) (cr/typename String))
          (list (cr/typename String))
          (fn [i s] (str i s))))

(defn is-node? [node]
    (= (first node) ::node))


(defn node-input-type [node input-id]
    {:pre [(and (is-node? node)
                (and (>= input-id 0) (< input-id (nth node input-channels-size-n)))
                )]}
    (nth (nth node input-channels-types-n) input-id))

;хранить в буферах аргументы
(defn send-input [node input input-id]
    {:pre [(is-node? node)]}
    (vector)

    (assoc node input-channels-n (assoc (nth node input-channels-n) in )))


