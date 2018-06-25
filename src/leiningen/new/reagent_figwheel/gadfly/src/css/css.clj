(ns {{ns-name}}.css
  (:require
   [garden.def :refer [defstyles]]
   [{{ns-name}}.styles.base :as base]
   [{{ns-name}}.styles.flex :as flex]
   [{{ns-name}}.styles.margins :as margins]
   [{{ns-name}}.styles.paddings :as paddings]
   [{{ns-name}}.styles.typography :as typography]))


(defstyles screen
  [[base/style]
   [flex/style]
   [margins/style]
   [paddings/style]
   [typography/style]
   ])
