(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]{{#devtools?}}
   [devtools.core :as devtools]{{/devtools?}}
   ))


(defonce debug?
  ^boolean js/goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "dev mode"){{#devtools?}}
    (devtools/install!){{/devtools?}}
    ))


(defonce app-state
  (reagent/atom
   {:text "Hello, what is your name? "}))


(defn page [ratom]
  [:p (:text @ratom) "FIXME"])


(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
