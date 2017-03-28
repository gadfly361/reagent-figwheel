(ns {{ns-name}}.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]

   [{{ns-name}}.pages.home.route :as home]
   [{{ns-name}}.pages.not-found.route :as not-found]
   ))


(declare hook-browser-navigation!)

(defn app-routes [ratom]
  (secretary/set-config! :prefix "#")
  ;; ---------------------------
  ;; define routes here

  (home/route ratom)


  (not-found/route ratom)
  ;;---------------------------
  (hook-browser-navigation!))



(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))
