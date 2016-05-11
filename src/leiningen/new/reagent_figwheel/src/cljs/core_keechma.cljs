(ns {{ns-name}}.core
  (:require-macros
   [reagent.ratom :refer [reaction]])
  (:require
   [reagent.core :as reagent]
   [keechma.ui-component :as ui]
   [keechma.controller :as controller]
   [keechma.app-state :as app-state]))


;; Controllers

(defrecord Counter []
  controller/IController

  (params [_ _] true)

  (start [_ params app-db]
    (assoc-in app-db [:count] 0))

  (handler [_ app-db-atom in-chan _]
    (controller/dispatcher
     app-db-atom
     in-chan
     {:inc #(swap! app-db-atom update-in [:count] inc)
      :dec #(swap! app-db-atom update-in [:count] dec)})))


;; Subs

(defn counter-value-sub [app-db]
  (reaction
   (get-in @app-db [:count])))


;; Components

(defn counter-render [app-db]
  (let [counter-sub (ui/subscription app-db :counter-value)]
    (fn []
      [:div
       [:p (str "Clicked: " @counter-sub " times")]
       [:button {:on-click #(ui/send-command app-db :inc)} "+"]
       [:button {:on-click #(ui/send-command app-db :dec)} "-"]])))

(def counter-component
  (ui/constructor
   {:renderer          counter-render
    :subscription-deps [:counter-value]}))


;; Initialize App

(def app-definition
  {:components    {:main (assoc counter-component :topic :Counter)}
   :controllers   {:Counter (->Counter)}
   :subscriptions {:counter-value counter-value-sub}
   :html-element  (.getElementById js/document "app")})

(defonce running-app (clojure.core/atom))

(defn start-app! []
  (reset! running-app (app-state/start! app-definition)))

(defn reload []
  (let [current @running-app]
    (if current
      (app-state/stop! current start-app!)
      (start-app!))))

(defn ^:export main []
  (start-app!))
