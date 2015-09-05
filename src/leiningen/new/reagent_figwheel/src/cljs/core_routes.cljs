(ns {{ns-name}}.core
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [reagent.core :as reagent]))

(defonce app-state (reagent/atom {:text "Hello, what is your name? "
                                  :page :nil}))

;; ------------------------------
;; routing
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
    (swap! app-state assoc :page :home))

  (defroute "/about" []
    (swap! app-state assoc :page :about))
  
  (hook-browser-navigation!))

;; ------------------------------
;; views
(defn home []
  [:div [:h1 "Home Page"]
   [:div (@app-state :text) "FIXME"]
   [:a {:href "#/about"} "about page"]])

(defn about []
  [:div [:h1 "About Page"]
   [:a {:href "#/"} "home page"]])

(defmulti page #(@app-state :page))
(defmethod page :home [] [home])
(defmethod page :about [] [about])
(defmethod page :default [] [:div ])

;; ------------------------------
;; initialize app
(defn ^:export main []
  (app-routes)
  (hook-browser-navigation!)
  (reagent/render [page]
                  (.getElementById js/document "app")))
