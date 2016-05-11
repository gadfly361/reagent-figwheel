(ns leiningen.new.reagent-figwheel
  (:require
   [leiningen.core.main :as main]
   [leiningen.new.options.base :as base]
   [leiningen.new.options.garden :as garden]
   [leiningen.new.options.less :as less]
   [leiningen.new.options.routes :as routes]
   [leiningen.new.options.keechma :as keechma]
   [leiningen.new.options.test :as test]
   [leiningen.new.options.helpers :as helpers])
  (:use [leiningen.new.templates :only [name-to-path sanitize-ns ->files]]))

(defn app-files [data options]
  (let [garden?  (helpers/option? garden/option options)
        keechma? (helpers/option? keechma/option options)
        less?    (helpers/option? less/option options)
        routes?  (helpers/option? routes/option options)
        test?    (helpers/option? test/option options)]
    (concat
     (base/files data)

     (cond
       keechma? (keechma/core-cljs data)
       routes?  (routes/core-cljs data)
       :else    (base/core-cljs data))

     (when garden? (garden/files data))
     (when less? (less/files data))
     (when test? (test/files data)))))

(defn template-data [name options]
  {:name      name
   :ns-name   (sanitize-ns name)
   :sanitized (name-to-path name)
   :cider?    (helpers/invoke-option "cider" options)
   :garden?   (helpers/invoke-option garden/option options)
   :keechma?  (helpers/invoke-option keechma/option options)
   :less?     (helpers/invoke-option less/option options)
   :routes?   (helpers/invoke-option routes/option options)
   :test?     (helpers/invoke-option test/option options)})

(defn reagent-figwheel [name & options]
  (let [data (template-data name options)]
    (main/info "Generating reagent-figwheel project.")
    (apply ->files data
           (app-files data options))))
