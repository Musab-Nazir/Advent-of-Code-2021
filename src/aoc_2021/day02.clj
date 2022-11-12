(ns aoc-2021.day02
  (:require
   [clojure.string :as str]))

;; Load data
(def course-input (str/split-lines (slurp "./resources/day2_input.edn")))

(def test-input ["forward 5"
                 "down 5"
                 "forward 8"
                 "up 3"
                 "down 8"
                 "forward 2"])

(defn update-position
  [input-data]
  (reduce (fn [org command]
            (let [[com num] (str/split command #" ")
                  nump (Integer/parseInt num)]
              ; (println org)
              (condp = com
                "forward" (assoc org :x (+ nump (:x org)))
                "up" (assoc org :y (- (:y org) nump))
                "down" (assoc org :y (+ nump (:y org))))))
          {:x 0 :y 0}
          input-data))

(def final (update-position course-input))
(def part1-answer (* (:x final) (:y final)))

(defn update-position-and-aim
  [input-data]
  (reduce (fn [org command]
            (let [[com num] (str/split command #" ")
                  nump (Integer/parseInt num)]
              ; (println org)
              (condp = com
                "forward" (assoc org :x (+ nump (:x org)) :y (+ (:y org) (* (:aim org) nump)))
                "up" (assoc org :aim (- (:aim org) nump))
                "down" (assoc org :aim (+ nump (:aim org))))))
          {:x 0 :y 0 :aim 0}
          input-data))

(def final2 (update-position-and-aim course-input))
(def part2-answer (* (:x final2) (:y final2)))
