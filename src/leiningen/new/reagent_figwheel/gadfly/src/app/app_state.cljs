(ns {{ns-name}}.app-state
  (:require
   [reagent.core :as reagent]))



(defonce main
  (reagent/atom
   {:app {:debug? ^boolean js/goog.DEBUG}}))
