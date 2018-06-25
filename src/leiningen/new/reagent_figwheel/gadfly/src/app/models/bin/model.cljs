(ns {{ns-name}}.models.bin.model
  (:require
   [reagent.core :as reagent]
   [{{ns-name}}.models.app.navigation.model :as nav-model]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:bin])

(defn app-state->cursor
  "Store data related to a given page in the bin. Will default to
  storing in the currently active page."
  ([app-state]
   (app-state->cursor app-state nil))

  ([app-state page-key]
   (let [nav-cursor (nav-model/app-state->cursor app-state)
         page-key   (or page-key
                        (nav-model/get-page-key nav-cursor))]
     (reagent/cursor app-state (conj cursor-path page-key)))))
