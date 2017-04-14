(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [matchbox.core :as m]
   [matchbox.reagent :as r]{{#re-frisk?}}
   [re-frisk.core :as rf]{{/re-frisk?}}{{#devtools?}}
   [devtools.core :as devtools]{{/devtools?}}
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce debug?
  ^boolean js/goog.DEBUG)

;; TODO: fix url
(def root (m/connect "https://FIXME.firebaseio.com/"))

(defonce app-state
  (r/sync-rw root))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

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



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!){{#re-frisk?}}
    (rf/enable-frisk!)
    (rf/add-data :app-state app-state){{/re-frisk?}}
    (println "dev mode"){{#devtools?}}
    (devtools/install!){{/devtools?}}
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (m/deref root #(reset! app-state %))
  (reload))
