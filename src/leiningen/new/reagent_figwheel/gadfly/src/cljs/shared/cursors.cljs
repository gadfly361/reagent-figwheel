(ns {{ns-name}}.shared.cursors
  (:require
   [reagent.core :as reagent]))



(defn app [ratom]
  (reagent/cursor ratom [:app]))
