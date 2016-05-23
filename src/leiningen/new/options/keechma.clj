(ns leiningen.new.options.keechma
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+keechma")

(defn core-cljs [data]
  [["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "src/cljs/core_keechma.cljs" data)]])
