(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [devtools.core :as devtools]
   [datafrisk.core :as d]
   [{{ns-name}}.model :as model]
   [{{ns-name}}.routes :as routes]
   [{{ns-name}}.pages.bundle :as pages]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Current Page

(defn current-page[ratom]
  (let [r        @ratom
        page-key (:page r)
        debug?   (:debug? r)]
    [:div

     (when debug?
       [:div#data-frisk
        [d/DataFriskShell r]])

     [(pages/page page-key) ratom]]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when model/debug?
    (enable-console-print!)
    (println "dev mode")
    (devtools/install!)))

(defn reload []
  (reagent/render [current-page model/app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (routes/app-routes model/app-state)
  (reload))
