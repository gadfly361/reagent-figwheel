(ns leiningen.new.options.routes
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "routes")

(defn core-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_routes.cljs" data)]])
