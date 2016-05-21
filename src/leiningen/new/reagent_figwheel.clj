(ns leiningen.new.reagent-figwheel
  (:require
   [leiningen.core.main :as main]
   [leiningen.new.options.base :as base]
   [leiningen.new.options.devcards :as devcards]
   [leiningen.new.options.garden :as garden]
   [leiningen.new.options.keechma :as keechma]
   [leiningen.new.options.less :as less]
   [leiningen.new.options.petrol :as petrol]
   [leiningen.new.options.routes :as routes]
   [leiningen.new.options.test :as test]
   [leiningen.new.options.helpers :as helpers])
  (:use [leiningen.new.templates :only [name-to-path sanitize-ns ->files]]))

(defn app-files [data options]
  (let [devcards? (helpers/option? devcards/option options)
        garden?   (helpers/option? garden/option options)
        keechma?  (helpers/option? keechma/option options)
        less?     (helpers/option? less/option options)
        petrol?   (helpers/option? petrol/option options)
        routes?   (helpers/option? routes/option options)
        test?     (helpers/option? test/option options)]
    (concat
     (base/files data)

     (cond
       keechma?      (keechma/core-cljs data)
       (and petrol?
            routes?) (petrol/core-routes-cljs data)
       petrol?       (petrol/core-cljs data)
       routes?       (routes/core-cljs data)
       :else         (base/core-cljs data))

     (when devcards? (devcards/files data))
     (when garden? (garden/files data))
     (when less? (less/files data))
     (when test? (test/files data)))))

(defn template-data [name options]
  {:name      name
   :ns-name   (sanitize-ns name)
   :sanitized (name-to-path name)
   :cider?    (helpers/invoke-option "cider" options)
   :devcards? (helpers/invoke-option devcards/option options)
   :garden?   (helpers/invoke-option garden/option options)
   :keechma?  (helpers/invoke-option keechma/option options)
   :less?     (helpers/invoke-option less/option options)
   :petrol?   (helpers/invoke-option petrol/option options)
   :routes?   (helpers/invoke-option routes/option options)
   :test?     (helpers/invoke-option test/option options)})

(defn reagent-figwheel [name & options]
  (let [data (template-data name options)]
    (main/info "Generating reagent-figwheel project.")
    (apply ->files data
           (app-files data options))))
