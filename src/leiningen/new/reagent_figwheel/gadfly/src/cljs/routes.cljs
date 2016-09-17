(ns {{ns-name}}.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]
   [{{ns-name}}.model :as model]
   [{{ns-name}}.shared.actions :as sa]))



(declare hook-browser-navigation!)

(defn app-routes [ratom]
  (secretary/set-config! :prefix "#")
  ;; ---------------------------
  ;; define routes here

  (defroute "/" []
    (sa/set-page! ratom :home))


  (defroute "/about" []
    (sa/set-page! ratom :about))

  (defroute "/*" []
    (sa/set-page! ratom :page-not-found))



  ;;---------------------------
  (hook-browser-navigation!))



(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))
