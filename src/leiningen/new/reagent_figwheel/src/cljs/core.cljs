(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]{{#re-frisk?}}
   [re-frisk.core :as rf]{{/re-frisk?}}
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn page [ratom]
  [:div
   "Welcome to reagent-figwheel."])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode"){{#re-frisk?}}
    (rf/enable-frisk!)
    (rf/add-data :app-state app-state){{/re-frisk?}}
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
