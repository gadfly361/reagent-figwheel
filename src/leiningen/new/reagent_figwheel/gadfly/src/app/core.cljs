(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [{{ns-name}}.env-init :as env-init]
   [{{ns-name}}.app-state :as app-state]
   [{{ns-name}}.current-page :as current-page]
   [{{ns-name}}.router-start :as router-start]
   [{{ns-name}}.models.app.screen.actions :as screen-actions]))


(defn reload []
  (reagent/render [current-page/main app-state/main]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (env-init/main app-state/main)
  (router-start/main app-state/main)  (reload)
  (screen-actions/set-breakpoints! app-state/main))
