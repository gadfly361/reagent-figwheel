(ns leiningen.new.reagent-figwheel
  (:require
   [leiningen.core.main :as main]
   [leiningen.new.options.base :as base]
   [leiningen.new.options.cider :as cider]
   [leiningen.new.options.devcards :as devcards]
   [leiningen.new.options.devtools :as devtools]
   [leiningen.new.options.gadfly :as gadfly]
   [leiningen.new.options.garden :as garden]
   [leiningen.new.options.firebase :as firebase]
   [leiningen.new.options.keechma :as keechma]
   [leiningen.new.options.less :as less]
   [leiningen.new.options.petrol :as petrol]
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
        firebase? (helpers/option? firebase/option options)
        keechma?  (helpers/option? keechma/option options)
        less?     (helpers/option? less/option options)
        petrol?   (helpers/option? petrol/option options)
        routes?   (helpers/option? routes/option options)
        test?     (helpers/option? test/option options)]

    (if gadfly?
      (gadfly/files data)

      ;; else
      (concat
       (base/files data)

       (cond
         keechma?                (keechma/core-cljs data)
         (and firebase? routes?) (firebase/core-routes-cljs data)
         (and petrol? routes?)   (petrol/core-routes-cljs data)

         firebase? (firebase/core-cljs data)
         petrol?   (petrol/core-cljs data)
         routes?   (routes/core-cljs data)
         :else     (base/core-cljs data))

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
   :firebase? (helpers/invoke-option firebase/option options)
   :keechma?  (helpers/invoke-option keechma/option options)
   :less?     (helpers/invoke-option less/option options)
   :petrol?   (helpers/invoke-option petrol/option options)
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
    firebase/option
    keechma/option
    less/option
    petrol/option
    "+re-frisk"
    routes/option
    test/option})


(defn check-available [options]
  (let [options-set (into #{} options)
        abort?      (not (set/superset? available-set
                                        options-set))]
    (when abort?
      (main/abort "\nError: invalid profile(s)\n"))))


(defn check-firebase [options]
  (let [options-set    (into #{} options)
        abort-petrol?  (set/subset? #{firebase/option
                                      petrol/option}
                                    options-set)
        abort-keechma? (set/subset? #{firebase/option
                                      keechma/option}
                                    options-set)]
    (when abort-petrol?
      (main/abort "\nError: +firebase cannot be used with +petrol\n"))

    (when abort-keechma?
      (main/abort "\nError: +firebase cannot be used with +keechma\n"))))


(defn check-frameworks [options]
  (let [options-set (into #{} options)
        abort?      (set/subset? #{petrol/option
                                   keechma/option}
                                 options-set)]
    (when abort?
      (main/abort "\nError: +petrol cannot be used with +keechma\n"))))


(defn check-re-frisk [options]
  (let [options-set (into #{} options)
        abort?      (set/subset? #{keechma/option
                                   "+re-frisk"}
                                 options-set)]
    (when abort?
      (main/abort "\nError: +re-frisk cannot be used with +keechma\n"))))


(defn check-routes [options]
  (let [options-set (into #{} options)
        abort?      (set/subset? #{keechma/option
                                   routes/option}
                                 options-set)]
    (when abort?
      (main/abort "\nError: +routes cannot be used with +keechma\n"))))


(defn check-options
  "Check the user-provided options"
  [options]
  (doto options
    check-available
    check-firebase
    check-frameworks
    check-re-frisk
    check-routes))



;; Main


(defn reagent-figwheel [name & options]
  (let [data (template-data name options)]
    (check-options options)
    (main/info "\nGenerating reagent-figwheel project.")
    (apply ->files data
           (app-files data options))))
