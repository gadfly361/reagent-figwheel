(ns {{ns-name}}.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]
   [reagent.core :as reagent]
   [petrol.core :as petrol]{{#re-frisk?}}
   [re-frisk.core :as rf]{{/re-frisk?}}{{#devtools?}}
   [devtools.core :as devtools]{{/devtools?}}
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce debug?
  ^boolean js/goog.DEBUG)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Model

(def initial-state
  {:counter 0})

(defonce app-state
  (reagent/atom initial-state))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Update

(defrecord Decrement []
  petrol/Message
  (process-message [_ app]
    (update app :counter dec)))

(defrecord Increment []
  petrol/Message
  (process-message [_ app]
    (update app :counter inc)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; View

(defn home [ui-channel app]
  [:div
   [:div [:h1 "Home Page"]
    [:a {:href "#/about"} "about page"]]

   [:p "Count: " (:counter app)]
   [:div
    [:button {:on-click (petrol/send! ui-channel (->Decrement))}
     "Decrement"]
    [:button {:on-click (petrol/send! ui-channel (->Increment))}
     "Increment"]]
   ])


(defn about [ui-channel app]
  [:div [:h1 "About Page"]
   [:a {:href "#/"} "home page"]])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defmulti page identity)
(defmethod page :home [] home)
(defmethod page :about [] about)
(defmethod page :default [] (fn [_] [:div]))

(defn current-page [ui-channel app]
  (let [page-key (:page app)]
    [(page page-key) ui-channel app]))

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!){{#re-frisk?}}
    (rf/enable-frisk!)
    (rf/add-data :app-state app-state){{/re-frisk?}}
    (println "dev mode"){{#devtools?}}
    (devtools/install!){{/devtools?}}
    ))

(defn reload []
  (swap! app-state identity))

(defn render-fn [ui-channel app]
  (reagent/render [current-page ui-channel app]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (app-routes)
  (petrol/start-message-loop! app-state render-fn))
