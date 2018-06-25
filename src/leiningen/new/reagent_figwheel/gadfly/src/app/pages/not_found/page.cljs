(ns {{ns-name}}.pages.not-found.page
  (:require
   [{{ns-name}}.pages.not-found.comps.header.comp :as header]))


(defn main [app-state]
  [:div.pa2
   [header/main app-state]
   ])
