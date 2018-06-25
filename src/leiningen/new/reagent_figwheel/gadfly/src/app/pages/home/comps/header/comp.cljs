(ns {{ns-name}}.pages.home.comps.header.comp
  (:require
   [{{ns-name}}.models.app.il8n.model :as il8n-model]
   [{{ns-name}}.models.app.navigation.actions :as nav-actions]
   ))


(def english {:title "Home Page"})
(def chinese {:title "主页"})

(def texts
  {:en english
   :zh chinese})


(defn title [app-state texts language-code]
  [:h1 (get-in texts [language-code :title])])


(defn link-to-about-page [app-state]
  [:h5
   [:a.primary-500.pointer
    {:on-click #(nav-actions/navigate! app-state {:page-key :about})}
    "go to about page"]])

(defn main [app-state]
  (let [il8n-cursor (il8n-model/cursor app-state)]
    (fn [app-state]
      (let [language-code (il8n-model/get-language-code il8n-cursor)]
        [:div
         [title app-state texts language-code]
         [link-to-about-page app-state]
         ]))))
