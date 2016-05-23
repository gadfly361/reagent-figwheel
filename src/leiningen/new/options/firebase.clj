(ns leiningen.new.options.firebase
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+firebase")

(defn core-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_firebase.cljs" data)]])

(defn core-routes-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_firebase_routes.cljs" data)]])
