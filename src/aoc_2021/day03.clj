(ns aoc-2021.day03
  (:require [clojure.string :as str]))

;; Load data
(def input (into [] (str/split-lines (slurp "./resources/day3_input.edn"))))

(def test-input (into [] (str/split-lines "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")))

(defn flip-bits
  "Produces the 1's compliment of a string binary representation"
  [n]
  (apply str (mapv (fn [c]
                     (condp = c
                       \0 \1
                       \1 \0)) n)))

(def dict (atom {:1 {:zero-count 0
                     :one-count 0}
                 :2 {:zero-count 0
                     :one-count 0}
                 :3 {:zero-count 0
                     :one-count 0}
                 :4 {:zero-count 0
                     :one-count 0}
                 :5 {:zero-count 0
                     :one-count 0}
                 :6 {:zero-count 0
                     :one-count 0}
                 :7 {:zero-count 0
                     :one-count 0}
                 :8 {:zero-count 0
                     :one-count 0}
                 :9 {:zero-count 0
                     :one-count 0}
                 :10 {:zero-count 0
                      :one-count 0}
                 :11 {:zero-count 0
                      :one-count 0}
                 :12 {:zero-count 0
                      :one-count 0}}))

(defn process-number
  [number repo]
  (doall (map-indexed (fn [idx bit]
                 (let [index (inc idx)
                       k (if (= (str bit) "1") :one-count :zero-count)
                       current-val (k ((keyword (str index)) @repo))]
                   (swap! repo assoc-in
                          [(keyword (str index)) k] (inc current-val)))) number)))

(mapv #(process-number % dict) input)

(defn create-gamma-num
  [repo]
  (for [i (range 1 13)]
    (when (and  (not= 0 (:zero-count ((keyword (str i)) @repo)))
                (not= 0 (:one-count ((keyword (str i)) @repo))))
      (if (> (:zero-count ((keyword (str i)) @repo)) (:one-count ((keyword (str i)) @repo)))
        "0"
        "1"))))

(def gamma (->> (create-gamma-num dict)
                (filter not-empty)
                (apply str)))

;; the epsilon number is just the 1's compliment of the gamma number
(def epsilon (flip-bits gamma))

(defn binary->int [b]
  (Integer/parseInt b 2))

(def fuel-consumption 
  (* (binary->int epsilon) (binary->int gamma)))

gamma ;;"001101001100"
epsilon ;;"110010110011"
fuel-consumption ;;2743844
