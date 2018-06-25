(ns {{ns-name}}.pages.not-found.comps.header.comp
  (:require
   [{{ns-name}}.models.app.il8n.model :as il8n-model]))


(def english {:title "Not Found Page"})
(def chinese {:title "未找到页面"})

(def texts
  {:en english
   :zh chinese})


(defn title [app-state texts language-code]
  [:h1 (get-in texts [language-code :title])])


(defn main [app-state]
  (let [il8n-cursor (il8n-model/cursor app-state)]
    (fn [app-state]
      (let [language-code (il8n-model/get-language-code il8n-cursor)]
        [:div
         [title app-state texts language-code]]))))
