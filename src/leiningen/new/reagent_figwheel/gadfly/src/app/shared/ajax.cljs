(ns {{ns-name}}.shared.ajax
  (:require
   [ajax.core :as ajax]
   [{{ns-name}}.shared.cursors :as cursors]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Util

(defn merge-response!
  "res is the response of the request.
   label is a string describing the endpoint being called.
   cks is a vector of [cursor key]"
  [res label & cks]
  (js/console.log "----- Request Ok --------")
  (js/console.log label)

  (doseq [[cursor k] cks]
    (let [response (get res k)]
      (js/console.log k)
      (js/console.log response)
      (reset! cursor response))))


(defn log-error! [ratom res label]
  (let [error-msg (get-in res [:response :err])]
    (js/console.log "----- Request Error ("
                    (get res :status)
                    ") --------")
    (js/console.log label)
    (js/console.log error-msg)
    (js/console.log res)

    (let [bin-cursor (cursors/bin ratom)
          error-msg  (or error-msg
                         "Uh oh, it appears there has been an error.")]
      (swap! bin-cursor assoc-in [:notifications :danger] error-msg))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Requests

(defn GET
  [path opts]
  (let [opts-full (assoc opts
                         :format (ajax/json-request-format)
                         :response-format (ajax/json-response-format {:keywords? true}))]
    (ajax/GET path opts-full)))

(defn POST
  [path opts]
  (let [opts-full (assoc opts
                         :format (ajax/json-request-format)
                         :response-format (ajax/json-response-format {:keywords? true}))]
    (ajax/POST path opts-full)))

(defn DELETE
  [path opts]
  (let [opts-full (assoc opts
                         :format (ajax/json-request-format)
                         :response-format (ajax/json-response-format {:keywords? true}))]
    (ajax/DELETE path opts-full)))
