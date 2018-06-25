(ns {{ns-name}}.models.form.model
  (:require
   [reagent.core :as reagent]
   [{{ns-name}}.models.app.navigation.model :as nav-model]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Cursor

(def cursor-path [:form])

(defn forms
  "Store form related data of a given page. Will default to
  storing in the currently active page."
  ([app-state]
   (forms app-state nil))

  ([app-state page-key]
   (let [nav-cursor (nav-model/app-state->cursor app-state)
         page-key   (or page-key
                        (nav-model/get-page-key nav-cursor))]
     (reagent/cursor app-state (conj cursor-path page-key)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Getters

(defn get-values
  "Returns a hash-map of field-keys and form-values"
  [cursor]
  (get @cursor :values))

(defn get-errors
  "Returns a hash-map of field-keys and error-values"
  [cursor]
  (get @cursor :errors))

(defn has-any-error?
  "Returns true if there is any value in the errors map"
  [cursor]
  (let [errors       (get-errors cursor)
        error-values (some-> errors vals)]
    (some->> error-values
             (some identity)
             boolean)))

(defn has-error?
  "Returns true if field-key has an error"
  [cursor field-key]
  (let [errors (get-errors cursor)]
    (some-> errors
            (get field-key false)
            boolean)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setters

(defn set-field-value!
  [cursor field-key field-value]
  (swap! cursor assoc-in [:values field-key] field-value))

(defn set-field-error!
  [cursor field-key field-value]
  (swap! cursor assoc-in [:errors field-key] field-value))
