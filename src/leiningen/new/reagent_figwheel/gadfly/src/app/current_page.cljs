(ns {{ns-name}}.current-page
  (:require
   [{{ns-name}}.models.app.navigation.model :as nav-model]
   [{{ns-name}}.pages.bundle :as pages-bundle]
   ))


(defn main [app-state]
  (let [nav-cursor (nav-model/app-state->cursor app-state)]
    (fn [app-state]
      (let [page-key (nav-model/get-page-key nav-cursor)
            page     (pages-bundle/get-bundle page-key :page)]
        [:div
         {:id (some-> page-key name)}
         [page app-state]]))))
