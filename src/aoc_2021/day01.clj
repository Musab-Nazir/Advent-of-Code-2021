(ns aoc-2021.day01
  (:require [clojure.edn :as edn]))

;; Load data
(def sonar-input (edn/read-string (slurp "./resources/day1_input.edn")))

;; Counter
(def c (atom 0))

;; Count the number of times data increases
(doall (map-indexed (fn [idx input]
                      (when (> idx 0)
                        (when (> input (nth sonar-input (dec idx)))
                          (swap! c inc)))) sonar-input))

(deref c)

(def windows (partition 3 1 sonar-input))

(defn sum-window
  [[x y z]]
  (+ x y z))

;; Counter
(def d (atom 0))

(doall (map-indexed (fn [idx input]
                      (when (> idx 0)
                        (when (> (sum-window input) (sum-window (nth windows (dec idx))))
                          (swap! d inc)))) windows))

(deref d)
