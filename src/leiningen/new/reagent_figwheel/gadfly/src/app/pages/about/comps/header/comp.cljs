(ns {{ns-name}}.pages.about.comps.header.comp
  (:require
   [{{ns-name}}.models.app.il8n.model :as il8n-model]
   [{{ns-name}}.models.app.navigation.actions :as nav-actions]
   [{{ns-name}}.shared.noty :as noty]
   ))


(def english {:title "About Page"})
(def chinese {:title "关于页面"})

(def texts
  {:en english
   :zh chinese})


(defn title [app-state texts language-code]
  [:h1 (get-in texts [language-code :title])])


(defn link-to-about-page [app-state]
  [:h5
   [:a.primary-500.pointer
    {:on-click #(nav-actions/navigate! app-state {:page-key :home})}
    "go to home page"]])

(defn btn-notification [app-state]
  [:div
   [:button
    {:on-click #(noty/notification
                 app-state
                 {:text        "Hello, world!"
                  :type        :error
                  :timeout     5000
                  :progressBar true})}
    "Show notification"]])


(defn btn-notification-yes-no [app-state]
  [:div
   [:button
    {:on-click #(noty/notification-yes-no
                 app-state
                 {:text "Do you want to continue?"}
                 {:yes-handler (fn [ref]
                                 (noty/ref->set-text ref "Fantastic")
                                 (noty/ref->set-timeout ref 1000))
                  :no-handler  (fn [ref]
                                 (noty/ref->close ref))})}
    "Show notification with yes / no"]])


(defn main [app-state]
  (let [il8n-cursor (il8n-model/cursor app-state)]
    (fn [app-state]
      (let [language-code (il8n-model/get-language-code il8n-cursor)]
        [:div
         [title app-state texts language-code]
         [link-to-about-page app-state]
         [:div.t4
          [btn-notification app-state]
          [btn-notification-yes-no app-state]]
         ]))))
