(ns {{ns-name}}.shared.ajax
  (:require
   [ajax.core :as ajax]))


(def default-opts
  {:format          (ajax/json-request-format)
   :response-format (ajax/json-response-format {:keywords? true})})


(defn GET
  [path opts]
  (let [opts-full (merge
                   default-opts
                   opts)]
    (ajax/GET path opts-full)))


(defn POST
  [path opts]
  (let [opts-full (merge
                   default-opts
                   opts)]
    (ajax/POST path opts-full)))


(defn DELETE
  [path opts]
  (let [opts-full (merge
                   default-opts
                   opts)]
    (ajax/DELETE path opts-full)))
