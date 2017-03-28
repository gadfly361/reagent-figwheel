(ns leiningen.new.options.gadfly
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+gadfly")

(defn files [data]
  [;; top-level
   ["README.md" (helpers/render "gadfly/README.md" data)]
   ["project.clj" (helpers/render "gadfly/project.clj" data)]
   [".gitignore" (helpers/render "gadfly/.gitignore" data)]


   ;; resources
   ["resources/public/index.html"
    (helpers/render "gadfly/resources/public/index.html" data)]

   ;; env
   ["env/dev/{{sanitized}}/init.cljs"
    (helpers/render "gadfly/env/dev/init.cljs" data)]
   ["env/prod/{{sanitized}}/init.cljs"
    (helpers/render "gadfly/env/prod/init.cljs" data)]


   ;; css
   ["src/css/{{sanitized}}/css.clj"
    (helpers/render "gadfly/src/css/css.clj" data)]

   ["src/css/{{sanitized}}/styles/base.clj"
    (helpers/render "gadfly/src/css/styles/base.clj" data)]
   ["src/css/{{sanitized}}/styles/flex.clj"
    (helpers/render "gadfly/src/css/styles/flex.clj" data)]
   ["src/css/{{sanitized}}/styles/margins.clj"
    (helpers/render "gadfly/src/css/styles/margins.clj" data)]
   ["src/css/{{sanitized}}/styles/paddings.clj"
    (helpers/render "gadfly/src/css/styles/paddings.clj" data)]

   ;; cljc
   ["src/cljc/{{sanitized}}/vars.cljc"
    (helpers/render "gadfly/src/cljc/vars.cljc" data)]


   ;; top-level
   ["src/app/{{sanitized}}/core.cljs"
    (helpers/render "gadfly/src/app/core.cljs" data)]
   ["src/app/{{sanitized}}/model.cljs"
    (helpers/render "gadfly/src/app/model.cljs" data)]

   ;; pages
   ["src/app/{{sanitized}}/pages/bundle.cljs"
    (helpers/render "gadfly/src/app/pages/bundle.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/main.cljs"
    (helpers/render "gadfly/src/app/pages/home/main.cljs" data)]
   ["src/app/{{sanitized}}/pages/not_found/main.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/main.cljs" data)]

   ;; routes
   ["src/app/{{sanitized}}/routes.cljs"
    (helpers/render "gadfly/src/app/routes.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/route.cljs"
    (helpers/render "gadfly/src/app/pages/home/route.cljs" data)]
   ["src/app/{{sanitized}}/pages/not_found/route.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/route.cljs" data)]

   ;; shared
   ["src/app/{{sanitized}}/shared/actions.cljs"
    (helpers/render "gadfly/src/app/shared/actions.cljs" data)]
   ["src/app/{{sanitized}}/shared/ajax.cljs"
    (helpers/render "gadfly/src/app/shared/ajax.cljs" data)]
   ["src/app/{{sanitized}}/shared/cursors.cljs"
    (helpers/render "gadfly/src/app/shared/cursors.cljs" data)]


   ;; test
   ["src/test/{{sanitized}}/runner.cljs"
    (helpers/render "gadfly/src/test/runner.cljs" data)]
   ["src/test/{{sanitized}}/shared/actions_test.cljs"
    (helpers/render "gadfly/src/test/shared/actions_test.cljs" data)]
   ])
