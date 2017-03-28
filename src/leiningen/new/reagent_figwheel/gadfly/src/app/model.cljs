(ns {{ns-name}}.model
  (:require
   [reagent.core :as reagent]))


(defonce debug?
  ^boolean js/goog.DEBUG)

(defonce app-state
  (reagent/atom
   {:app {:debug? debug?
          :page   nil}}))
