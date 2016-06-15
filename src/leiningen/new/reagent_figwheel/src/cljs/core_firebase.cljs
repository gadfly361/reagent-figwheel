(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [matchbox.core :as m]
   [matchbox.reagent :as r]{{#devtools?}}
   [devtools.core :as devtools]{{/devtools?}}
   ))


(defonce debug?
  ^boolean js/goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "dev mode"){{#devtools?}}
    (devtools/install!){{/devtools?}}
    ))


;; TODO: fix url
(def root (m/connect "https://FIXME.firebaseio.com/"))

(defonce app-state
  (r/sync-rw root))


(defn page [ratom]
  (let [count (:count @ratom)]
    [:div

     [:div
      [:button
       {:on-click #(m/swap! root update :count dec)}
       "Decrement"]
      [:button
       {:on-click #(m/swap! root update :count inc)}
       "Increment"]]

     [:p "Count: " count]
     ]))


(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (m/deref root #(reset! app-state %))
  (reload))
