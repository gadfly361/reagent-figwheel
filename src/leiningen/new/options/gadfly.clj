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
   ["resources/public/cards.html"
    (helpers/render "gadfly/resources/public/cards.html" data)]


   ;; infra
   ["infra/build.sh" (helpers/render "gadfly/infra/build.sh" data)]
   ["infra/pre-commit.sh" (helpers/render "gadfly/infra/pre-commit.sh" data)]


   ;; clj
   ["src/clj/{{sanitized}}/css.clj"
    (helpers/render "gadfly/src/clj/css.clj" data)]
   ["src/clj/{{sanitized}}/vars.clj"
    (helpers/render "gadfly/src/clj/vars.clj" data)]
   ["src/clj/{{sanitized}}/styles/margins.clj"
    (helpers/render "gadfly/src/clj/styles/margins.clj" data)]


   ;; cljs
   ["src/cljs/{{sanitized}}/core.cljs"
    (helpers/render "gadfly/src/cljs/core.cljs" data)]
   ["src/cljs/{{sanitized}}/model.cljs"
    (helpers/render "gadfly/src/cljs/model.cljs" data)]
   ["src/cljs/{{sanitized}}/routes.cljs"
    (helpers/render "gadfly/src/cljs/routes.cljs" data)]

   ["src/cljs/{{sanitized}}/pages/about.cljs"
    (helpers/render "gadfly/src/cljs/pages/about.cljs" data)]
   ["src/cljs/{{sanitized}}/pages/bundle.cljs"
    (helpers/render "gadfly/src/cljs/pages/bundle.cljs" data)]
   ["src/cljs/{{sanitized}}/pages/home.cljs"
    (helpers/render "gadfly/src/cljs/pages/home.cljs" data)]
   ["src/cljs/{{sanitized}}/pages/page_not_found.cljs"
    (helpers/render "gadfly/src/cljs/pages/page_not_found.cljs" data)]

   ["src/cljs/{{sanitized}}/shared/actions.cljs"
    (helpers/render "gadfly/src/cljs/shared/actions.cljs" data)]


   ;; devcards
   ["src/devcards/{{sanitized}}/core_card.cljs"
    (helpers/render "gadfly/src/devcards/core_card.cljs" data)]
   ["src/devcards/{{sanitized}}/first_card.cljs"
    (helpers/render "gadfly/src/devcards/first_card.cljs" data)]


   ;; test
   ["test/cljs/{{sanitized}}/runner.cljs"
    (helpers/render "gadfly/test/cljs/runner.cljs" data)]
   ["test/cljs/{{sanitized}}/shared/actions_test.cljs"
    (helpers/render "gadfly/test/cljs/shared/actions_test.cljs" data)]
   ])
