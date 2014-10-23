(ns leiningen.new.reagent-figwheel
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "reagent-figwheel"))

(defn reagent-figwheel
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (->files data ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["dev/user.cljs" (render "user.cljs" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["resources/index.html" (render "index.html")]
             )))
