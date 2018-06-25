(ns {{ns-name}}.pages.about.page
  (:require
   [{{ns-name}}.pages.about.comps.header.comp :as header]
   ))


(defn main [app-state]
  [:div.pa2
   [:div.b4
    [header/main app-state]]
   ])
