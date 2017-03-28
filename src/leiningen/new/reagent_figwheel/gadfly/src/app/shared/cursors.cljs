(ns {{ns-name}}.shared.cursors
  (:require
   [reagent.core :as reagent]))


(defn app [ratom]
  (reagent/cursor ratom [:app]))


;; util fn
(defn get-page [ratom]
  (let [app-cursor (app ratom)]
    (get @app-cursor :page)))


(defn bin
  "Store non-form data"
  ([ratom]
   (bin ratom nil))

  ([ratom page-key]
   (let [page (or page-key
                  (get-page ratom))]
     (reagent/cursor ratom [:bin page]))))


(defn forms
  "Store form data"
  ([ratom]
   (forms ratom nil))

  ([ratom page-key]
   (let [page (or page-key
                  (get-page ratom))]
     (reagent/cursor ratom [:forms page]))))
