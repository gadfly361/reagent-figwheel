(ns {{ns-name}}.models.app.screen.model
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:app :screen])

(defn app-state->cursor
  "The screen cursor should include state that is about the user's
  screen dimensions"
  [app-state]
  (reagent/cursor app-state cursor-path))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Getters

(defn get-screen-width [cursor]
  (get @cursor :width))

(defn get-screen-height [cursor]
  (get @cursor :height))

(defn get-screen [cursor]
  (get @cursor :screen))

(defn get-mobile? [cursor]
  (get @cursor :mobile? false))

(defn get-tablet? [cursor]
  (get @cursor :tablet? false))

(defn get-small-monitor? [cursor]
  (get @cursor :small-monitor? false))

(defn get-large-monitor? [cursor]
  (get @cursor :large-monitor? false))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setters

(defn set-screen-dimensions! [cursor {:keys [screen-width
                                             screen-height
                                             screen]}]
  (swap! cursor assoc
         :width screen-width
         :height screen-height
         :screen screen
         :mobile? (= screen :mobile)
         :tablet? (= screen :tablet)
         :small-monitor? (= screen :small-monitor)
         :large-monitor? (= screen :large-monitor)))
