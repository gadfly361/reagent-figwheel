(ns {{ns-name}}.core
    (:require [reagent.core :as reagent]))


(defonce app-state
  (reagent/atom {:text "Hello, what is your name? "}))

(defn page [ratom]
  (let [text (:text @ratom)]
    [:p text "FIXME"]))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (reload))
