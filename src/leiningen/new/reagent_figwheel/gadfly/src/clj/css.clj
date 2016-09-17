(ns {{ns-name}}.css
  (:require
   [garden.def :refer [defstyles]]
   [{{ns-name}}.styles.margins :as margins]))


(defstyles screen
  [margins/style])
