(ns {{ns-name}}.pages.home.page
  (:require
   [{{ns-name}}.pages.home.comps.header.comp :as header]
   [{{ns-name}}.pages.home.comps.typography.comp :as typography]
   ))


(defn main [app-state]
  [:div.pa2
   [:div.b4
    [header/main app-state]]
   [typography/main app-state]
   ])
