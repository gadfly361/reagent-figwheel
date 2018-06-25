(ns {{ns-name}}.models.app.model
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:app])

(defn app-state->cursor
  "The app cursor should include state that is about the application
  itself and that is meaningful whether or not a user has a session"
  [app-state]
  (reagent/cursor app-state cursor-path))
