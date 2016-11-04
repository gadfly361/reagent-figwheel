(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [devtools.core :as devtools]
   [datafrisk.core :as d]
   [{{ns-name}}.model :as model]
   [{{ns-name}}.routes :as routes]
   [{{ns-name}}.pages.bundle :as pages]
   [{{ns-name}}.shared.cursors :as cursors]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Current Page

(defn current-page[ratom]
  (let [app-cursor (cursors/app ratom)]
    (fn [ratom]
      (let [r              @ratom
            {:keys [debug?
                    page]} @app-cursor]
        [:div

         (when debug?
           [:div#data-frisk
            [d/DataFriskShell r]])

         [(pages/page page) ratom]]))))



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
