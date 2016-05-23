(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [matchbox.core :as m]
   [matchbox.reagent :as r]))


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
  (m/deref root #(reset! app-state %))
  (reload))
