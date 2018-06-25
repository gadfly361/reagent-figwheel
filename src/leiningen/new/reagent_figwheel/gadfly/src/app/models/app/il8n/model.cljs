(ns {{ns-name}}.models.app.il8n.model
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:app :il8n])

(defn cursor [app-state]
  (reagent/cursor app-state cursor-path))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Getters

(defn get-language-code
  "Gets the language code and defaults to :en"
  [cursor]
  (get @cursor :language-code
       :en))
