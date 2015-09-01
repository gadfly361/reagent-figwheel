(ns leiningen.new.options.base
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files [data]
  [["README.md" (helpers/render "README.md" data)]
   ["project.clj" (helpers/render "project.clj" data)]
   ["resources/public/index.html" (helpers/render "resources/public/index.html" data)]
   ["src/clj/{{path-name}}/core.clj" (helpers/render "src/clj/core.clj" data)] ])

(defn core-cljs [data]
  [["src/cljs/{{path-name}}/core.cljs" (helpers/render "src/cljs/core.cljs" data)]])
