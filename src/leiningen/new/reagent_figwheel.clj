(ns leiningen.new.reagent-figwheel
  (:require [leiningen.new.options.base :as base]
            [leiningen.new.options.garden :as garden]
            [leiningen.new.options.routes :as routes]
            [leiningen.new.options.test :as test]
            [leiningen.new.options.helpers :as helpers])
  (:use [leiningen.new.templates :only [name-to-path sanitize sanitize-ns ->files]]))

(defn app-files [data options]
  (let [garden? (helpers/option? garden/option options)
        ;; routes? (helpers/option? routes/option options)
        test? (helpers/option? test/option options)]

    (concat
     (base/files data)

     ;;(if routes? 
     ;; (routes/core-cljs data)
     (base/core-cljs data)
     ;;)

     (when garden? (garden/files data))
     (when test? (test/files data)) )))

(defn template-data [name options]
  {:name name
   :ns-name (sanitize-ns name)
   :path-name (name-to-path name)
   :sanitized-name (sanitize name)

   :garden? (helpers/invoke-option garden/option options)
   ;; :routes? (helpers/invoke-option routes/option options)
   :test? (helpers/invoke-option test/option options)})

(defn reagent-figwheel [name & options]
  (let [data (template-data name options)]
    (apply ->files data
           (app-files data options))))
