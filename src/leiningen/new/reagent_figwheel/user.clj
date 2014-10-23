(ns user
  (:require [leiningen.core.main :as lein]
            [clojure.java.io :as io]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [compojure.handler :refer [api]]
            [ring.middleware.reload :as reload]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defroutes routes
  (resources "/")
  (GET "/*" req (io/resource "index.html")))

(def http-handler (reload/wrap-reload (api #'routes)))

(defn run [& [port]]
  (defonce ^:private server
    (let [port (Integer. (or port (env :port) 10555))]
      (print "Starting web server on port" port ".\n")
      (run-jetty http-handler {:port port
                               :join? false})))
  server)

(defn start-figwheel []
  (future
    (print "Starting figwheel.\n")
    (lein/-main ["figwheel"])))

(defn -main [& [port]]
  (run port))
