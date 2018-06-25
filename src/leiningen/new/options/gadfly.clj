(ns leiningen.new.options.gadfly
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+gadfly")

(defn files [data]
  [;; top-level
   [".gitignore" (helpers/render "gadfly/.gitignore" data)]
   ["externs.js" (helpers/render "gadfly/externs.js" data)]
   ["project.clj" (helpers/render "gadfly/project.clj" data)]
   ["README.md" (helpers/render "gadfly/README.md" data)]

   ;; env
   ["env/dev/{{sanitized}}/env_init.cljs"
    (helpers/render "gadfly/env/dev/env_init.cljs" data)]
   ["env/prod/{{sanitized}}/env_init.cljs"
    (helpers/render "gadfly/env/prod/env_init.cljs" data)]


   ;; resources
   ["resources/public/index.html"
    (helpers/render "gadfly/resources/public/index.html" data)]
   ["resources/public/cards.html"
    (helpers/render "gadfly/resources/public/cards.html" data)]
   ["resources/public/vendor/noty/noty.css"
    (helpers/render "gadfly/resources/public/vendor/noty/noty.css" data)]
   ["resources/public/vendor/noty/noty.css.map"
    (helpers/render "gadfly/resources/public/vendor/noty/noty.css.map" data)]
   ["resources/public/vendor/noty/noty_effects.css"
    (helpers/render "gadfly/resources/public/vendor/noty/noty_effects.css" data)]
   ["resources/public/vendor/noty/themes/semanticui.css"
    (helpers/render "gadfly/resources/public/vendor/noty/themes/semanticui.css" data)]


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
   ["src/css/{{sanitized}}/styles/typography.clj"
    (helpers/render "gadfly/src/css/styles/typography.clj" data)]


   ;; top-level
   ["src/app/{{sanitized}}/app_state.cljs"
    (helpers/render "gadfly/src/app/app_state.cljs" data)]
   ["src/app/{{sanitized}}/core_cards.cljs"
    (helpers/render "gadfly/src/app/core_cards.cljs" data)]
   ["src/app/{{sanitized}}/core.cljs"
    (helpers/render "gadfly/src/app/core.cljs" data)]
   ["src/app/{{sanitized}}/current_page.cljs"
    (helpers/render "gadfly/src/app/current_page.cljs" data)]
   ["src/app/{{sanitized}}/router.cljs"
    (helpers/render "gadfly/src/app/router.cljs" data)]
   ["src/app/{{sanitized}}/router_start.cljs"
    (helpers/render "gadfly/src/app/router_start.cljs" data)]

   ;; models > app
   ["src/app/{{sanitized}}/models/app/model.cljs"
    (helpers/render "gadfly/src/app/models/app/model.cljs" data)]
   ;; models > app > il8n
   ["src/app/{{sanitized}}/models/app/il8n/model.cljs"
    (helpers/render "gadfly/src/app/models/app/il8n/model.cljs" data)]
   ;; models > app > navigation
   ["src/app/{{sanitized}}/models/app/navigation/actions.cljs"
    (helpers/render "gadfly/src/app/models/app/navigation/actions.cljs" data)]
   ["src/app/{{sanitized}}/models/app/navigation/model.cljs"
    (helpers/render "gadfly/src/app/models/app/navigation/model.cljs" data)]
   ;; models > app > screen
   ["src/app/{{sanitized}}/models/app/screen/actions.cljs"
    (helpers/render "gadfly/src/app/models/app/screen/actions.cljs" data)]
   ["src/app/{{sanitized}}/models/app/screen/model.cljs"
    (helpers/render "gadfly/src/app/models/app/screen/model.cljs" data)]

   ;; models > bin
   ["src/app/{{sanitized}}/models/bin/model.cljs"
    (helpers/render "gadfly/src/app/models/bin/model.cljs" data)]
   ;; models > bin > persist
   ["src/app/{{sanitized}}/models/bin/persist/model.cljs"
    (helpers/render "gadfly/src/app/models/bin/persist/model.cljs" data)]
   ;; models > bin > temp
   ["src/app/{{sanitized}}/models/bin/temp/model.cljs"
    (helpers/render "gadfly/src/app/models/bin/temp/model.cljs" data)]

   ;; models > form
   ["src/app/{{sanitized}}/models/form/model.cljs"
    (helpers/render "gadfly/src/app/models/form/model.cljs" data)]


   ;; pages
   ["src/app/{{sanitized}}/pages/bundle.cljs"
    (helpers/render "gadfly/src/app/pages/bundle.cljs" data)]

   ;; pages > about
   ["src/app/{{sanitized}}/pages/about/bundle.cljs"
    (helpers/render "gadfly/src/app/pages/about/bundle.cljs" data)]
   ["src/app/{{sanitized}}/pages/about/cards.cljs"
    (helpers/render "gadfly/src/app/pages/about/cards.cljs" data)]
   ["src/app/{{sanitized}}/pages/about/page.cljs"
    (helpers/render "gadfly/src/app/pages/about/page.cljs" data)]
   ["src/app/{{sanitized}}/pages/about/route.cljs"
    (helpers/render "gadfly/src/app/pages/about/route.cljs" data)]
   ;; pages > about > comps > header
   ["src/app/{{sanitized}}/pages/about/comps/header/comp.cljs"
    (helpers/render "gadfly/src/app/pages/about/comps/header/comp.cljs" data)]

   ;; pages > home
   ["src/app/{{sanitized}}/pages/home/bundle.cljs"
    (helpers/render "gadfly/src/app/pages/home/bundle.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/cards.cljs"
    (helpers/render "gadfly/src/app/pages/home/cards.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/page.cljs"
    (helpers/render "gadfly/src/app/pages/home/page.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/route.cljs"
    (helpers/render "gadfly/src/app/pages/home/route.cljs" data)]
   ;; pages > home > comps > header
   ["src/app/{{sanitized}}/pages/home/comps/header/comp.cljs"
    (helpers/render "gadfly/src/app/pages/home/comps/header/comp.cljs" data)]
   ;; pages > home > comps > typography
   ["src/app/{{sanitized}}/pages/home/comps/typography/comp.cljs"
    (helpers/render "gadfly/src/app/pages/home/comps/typography/comp.cljs" data)]
   ["src/app/{{sanitized}}/pages/home/comps/typography/handlers.cljc"
    (helpers/render "gadfly/src/app/pages/home/comps/typography/handlers.cljc" data)]
   ["src/app/{{sanitized}}/pages/home/comps/typography/handlers_test.clj"
    (helpers/render "gadfly/src/app/pages/home/comps/typography/handlers_test.clj" data)]


   ;; pages > not_found
   ["src/app/{{sanitized}}/pages/not_found/bundle.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/bundle.cljs" data)]
   ["src/app/{{sanitized}}/pages/not_found/page.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/page.cljs" data)]
   ["src/app/{{sanitized}}/pages/not_found/route.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/route.cljs" data)]
   ;; pages > not_found > comps > header
   ["src/app/{{sanitized}}/pages/not_found/comps/header/comp.cljs"
    (helpers/render "gadfly/src/app/pages/not_found/comps/header/comp.cljs" data)]


   ;; shared
   ["src/app/{{sanitized}}/shared/ajax.cljs"
    (helpers/render "gadfly/src/app/shared/ajax.cljs" data)]
   ["src/app/{{sanitized}}/shared/css_vars.cljc"
    (helpers/render "gadfly/src/app/shared/css_vars.cljc" data)]
   ["src/app/{{sanitized}}/shared/noty.cljs"
    (helpers/render "gadfly/src/app/shared/noty.cljs" data)]

   ])
