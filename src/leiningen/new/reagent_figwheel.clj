(ns leiningen.new.reagent-figwheel
  (:require
   [leiningen.core.main :as main]
   [leiningen.new.options.base :as base]
   [leiningen.new.options.cider :as cider]
   [leiningen.new.options.devcards :as devcards]
   [leiningen.new.options.devtools :as devtools]
   [leiningen.new.options.gadfly :as gadfly]
   [leiningen.new.options.garden :as garden]
   [leiningen.new.options.less :as less]
   [leiningen.new.options.routes :as routes]
   [leiningen.new.options.test :as test]
   [leiningen.new.options.helpers :as helpers]
   [clojure.set :as set])
  (:use [leiningen.new.templates :only [name-to-path sanitize-ns ->files]]))



;; Files & Data for Template


(defn app-files
  "Create a list of application files based on the user-provided options"
  [data options]
  (let [devcards? (helpers/option? devcards/option options)
        gadfly?   (helpers/option? gadfly/option options)
        garden?   (helpers/option? garden/option options)
        less?     (helpers/option? less/option options)
        routes?   (helpers/option? routes/option options)
        test?     (helpers/option? test/option options)]

    (if gadfly?
      (gadfly/files data)

      ;; else
      (concat
       (base/files data)

       (cond
         routes? (routes/core-cljs data)
         :else   (base/core-cljs data))

       (when devcards? (devcards/files data))
       (when garden? (garden/files data))
       (when less? (less/files data))
       (when test? (test/files data))))))


(defn template-data
  [name options]
  {:name      name
   :ns-name   (sanitize-ns name)
   :sanitized (name-to-path name)
   :cider?    (helpers/invoke-option cider/option options)
   :devcards? (helpers/invoke-option devcards/option options)
   :devtools? (helpers/invoke-option devtools/option options)
   :garden?   (helpers/invoke-option garden/option options)
   :less?     (helpers/invoke-option less/option options)
   :re-frisk? (helpers/invoke-option "+re-frisk" options)
   :routes?   (helpers/invoke-option routes/option options)
   :test?     (helpers/invoke-option test/option options)})



;; Check Options


(def available-set
  #{cider/option
    devcards/option
    devtools/option
    gadfly/option
    garden/option
    less/option
    "+re-frisk"
    routes/option
    test/option})


(defn check-available [options]
  (let [options-set (into #{} options)
        abort?      (not (set/superset? available-set
                                        options-set))]
    (when abort?
      (main/abort "\nError: invalid profile(s)\n"))))



;; Main


(defn reagent-figwheel [name & options]
  (let [data (template-data name options)]
    (check-available options)
    (main/info "\nGenerating reagent-figwheel project.")
    (apply ->files data
           (app-files data options))))
