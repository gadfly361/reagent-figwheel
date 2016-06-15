(ns {{ns-name}}.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]
   [reagent.core :as reagent]{{#devtools?}}
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


(defonce app-state
  (reagent/atom {:text "Hello, what is your name? "
                 :page :nil}))


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
    (swap! app-state assoc :page :home))

  (defroute "/about" []
    (swap! app-state assoc :page :about))

  ;; add routes here


  (hook-browser-navigation!))


;; Pages

(defn home [ratom]
  (let [text (:text @ratom)]
    [:div [:h1 "Home Page"]
     [:p text "FIXME"]
     [:a {:href "#/about"} "about page"]]))

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
  (app-routes)
  (reload))
