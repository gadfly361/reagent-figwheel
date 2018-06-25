(ns {{ns-name}}.styles.margins
  (:require
   [{{ns-name}}.shared.css-vars :as css-vars]))


(defn create-margin-all [n]
  [(str ".a" n)
   {:margin (str (* n css-vars/spacer-margin)
                 "px !important")}])

(defn create-margin-x [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".x" n)
     {:margin {:left  margin
               :right margin}}]))

(defn create-margin-y [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".y" n)
     {:margin {:top    margin
               :bottom margin}}]))

(defn create-margin-top [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".t" n)
     {:margin-top margin}]))

(defn create-margin-bottom [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".b" n)
     {:margin-bottom margin}]))

(defn create-margin-left [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".l" n)
     {:margin-left margin}]))

(defn create-margin-right [n]
  (let [margin (str (* n css-vars/spacer-margin)
                    "px !important")]
    [(str ".r" n)
     {:margin-right margin}]))



(def margin-all
  (mapv create-margin-all (range 0 17)))

(def margin-x
  (mapv create-margin-x (range 0 17)))

(def margin-y
  (mapv create-margin-y (range 0 17)))

(def margin-top
  (mapv create-margin-top (range 0 17)))

(def margin-bottom
  (mapv create-margin-bottom (range 0 17)))

(def margin-left
  (mapv create-margin-left (range 0 17)))

(def margin-right
  (mapv create-margin-right (range 0 17)))


(def style
  [margin-all
   margin-x
   margin-y
   margin-top
   margin-bottom
   margin-left
   margin-right])
