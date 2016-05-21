(ns leiningen.new.options.petrol
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "petrol")

(defn core-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_petrol.cljs" data)]])

(defn core-routes-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_petrol_routes.cljs" data)]])
