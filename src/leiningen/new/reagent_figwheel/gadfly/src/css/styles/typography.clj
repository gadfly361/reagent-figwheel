(ns {{ns-name}}.styles.typography
  (:require
   [{{ns-name}}.shared.css-vars :as css-vars]))

(def colors
  [;; default color
   {:color css-vars/grey-900}

   [:&.white
    {:color "white"}]

   [:&.grey-50
    {:color css-vars/grey-50}]
   [:&.grey-100
    {:color css-vars/grey-100}]
   [:&.grey-200
    {:color css-vars/grey-200}]
   [:&.grey-300
    {:color css-vars/grey-300}]
   [:&.grey-400
    {:color css-vars/grey-400}]
   [:&.grey-500
    {:color css-vars/grey-500}]
   [:&.grey-600
    {:color css-vars/grey-600}]
   [:&.grey-700
    {:color css-vars/grey-700}]
   [:&.grey-800
    {:color css-vars/grey-800}]
   [:&.grey-900
    {:color css-vars/grey-900}]

   [:&.primary-50
    {:color css-vars/primary-50}]
   [:&.primary-100
    {:color css-vars/primary-100}]
   [:&.primary-200
    {:color css-vars/primary-200}]
   [:&.primary-300
    {:color css-vars/primary-300}]
   [:&.primary-400
    {:color css-vars/primary-400}]
   [:&.primary-500
    {:color css-vars/primary-500}]
   [:&.primary-600
    {:color css-vars/primary-600}]
   [:&.primary-700
    {:color css-vars/primary-700}]
   [:&.primary-800
    {:color css-vars/primary-800}]
   [:&.primary-900
    {:color css-vars/primary-900}]
   ])

(def weights
  [[:&.bold
    {:font-weight 400}]
   [:&.bolder
    {:font-weight 500}]])

(def util
  [[:&.pointer
    {:cursor "pointer"}]])

(defn heading [v]
  (into v (concat colors weights util)))


(def span
  (heading
   [:span]))

(def a
  (heading
   [:a]))


(def p
  (heading
   [:p]))


(def h1
  (heading
   [:h1
    {:font-size   "36px"
     :line-height "1.25em"
     :margin 0}]))


(def h2
  (heading
   [:h2
    {:font-size   "24px"
     :line-height "1.25em"
     :margin 0}]))

(def h3
  (heading
   [:h3
    {:font-size   "18px"
     :line-height "1.25em"
     :margin 0}]))

(def h4
  (heading
   [:h4
    {:font-size   "14px"
     :line-height "1.22em"
     :margin 0}]))

(def h5
  (heading
   [:h5
    {:font-size   "12px"
     :line-height "1.20em"
     :margin 0}]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Style

(def style
  [span
   a
   p

   h1
   h2
   h3
   h4
   h5

   ])
