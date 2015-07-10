(ns leiningen.new.reagent-figwheel
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize sanitize-ns ->files]]))

(def render (renderer "reagent-figwheel"))

(defn reagent-figwheel
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :path-name (name-to-path name)
              :sanitized-name (sanitize name)}]
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["resources/public/index.html" (render "resources/public/index.html" data)]
             ["src/cljs/{{path-name}}/core.cljs" (render "src/cljs/core.cljs" data)]
             ["src/clj/{{path-name}}/core.cljs" (render "src/clj/core.cljs" data)]
             ["README.md" (render "README.md" data)]
             )))
