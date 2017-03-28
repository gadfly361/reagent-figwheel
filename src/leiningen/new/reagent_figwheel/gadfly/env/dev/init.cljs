(ns {{ns-name}}.init
  (:require
   [cljs.spec :as spec]
   [re-frisk.core :as rf]))


(defn enable-print []
  (enable-console-print!)
  (println "dev mode"))


(defn enable-re-frisk [ratom]
  (rf/enable-frisk! {:width  "800px"
                     :height "500px"
                     :x      256
                     :y      180})
  (rf/add-data :app-state ratom))


(defn main [ratom]
  (spec/check-asserts true)
  (enable-print)
  (enable-re-frisk ratom))
