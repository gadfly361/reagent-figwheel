(ns {{ns-name}}.pages.about.route
  (:require
   [{{ns-name}}.models.app.navigation.actions :as nav-actions]))


(defn main
  [app-state {:keys [page-key
                     params
                     query]}]
  (nav-actions/set-page! app-state page-key))
