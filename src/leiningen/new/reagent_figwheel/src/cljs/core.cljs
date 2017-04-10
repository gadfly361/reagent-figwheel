(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]{{#re-frisk?}}
   [re-frisk.core :as rf]{{/re-frisk?}}{{#devtools?}}
   [devtools.core :as devtools]{{/devtools?}}
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce debug?
  ^boolean js/goog.DEBUG)

(defonce app-state
  (reagent/atom
   {:text "Hello, what is your name? "}))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn page [ratom]
  [:p (:text @ratom) "FIXME"])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!){{#re-frisk?}}
    (rf/enable-frisk!)
    (rf/add-data :app-state app-state){{/re-frisk?}}
    (println "dev mode"){{#devtools?}}
    (devtools/install!){{/devtools?}}
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
