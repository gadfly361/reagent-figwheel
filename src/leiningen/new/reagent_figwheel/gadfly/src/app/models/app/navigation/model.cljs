(ns {{ns-name}}.models.app.navigation.model
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:app :navigation])

(defn app-state->cursor [app-state]
  (reagent/cursor app-state cursor-path))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Getters

(defn get-page-key [cursor]
  (get @cursor :page-key))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setters

(defn set-page-key! [cursor page-key]
  (swap! cursor assoc :page-key page-key))
