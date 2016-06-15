(ns {{ns-name}}.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]
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


;; Routes

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (m/swap! root assoc :page :home))

  (defroute "/about" []
    (m/swap! root assoc :page :about))

  ;; add routes here


  (hook-browser-navigation!))


;; Pages

(defn home [ratom]
  (let [count (:count @ratom)]
    [:div [:h1 "Home Page"]
     [:a {:href "#/about"} "about page"]

     [:p "Count: " count]
     [:div
      [:button
       {:on-click #(m/swap! root update :count dec)}
       "Decrement"]
      [:button
       {:on-click #(m/swap! root update :count inc)}
       "Increment"]]]))

(defn about [ratom]
  [:div [:h1 "About Page"]
   [:a {:href "#/"} "home page"]])


;; Initialize App

(defmulti page identity)
(defmethod page :home [] home)
(defmethod page :about [] about)
(defmethod page :default [] (fn [_] [:div]))

(defn current-page [ratom]
  (let [page-key (:page @ratom)]
    [(page page-key) ratom]))


(defn reload []
  (reagent/render [current-page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (m/deref root #(reset! app-state %))
  (app-routes)
  (reload))
