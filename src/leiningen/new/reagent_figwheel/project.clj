(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.1"]{{#routes?}}
                 [secretary "1.2.3"]{{/routes?}}{{#garden?}}
                 [garden "1.3.2"]{{/garden?}}{{#firebase?}}
                 [matchbox "0.0.10-SNAPSHOT"]{{/firebase?}}{{#keechma?}}
                 [keechma "0.1.0-SNAPSHOT" :exclusions [cljsjs/react-with-addons]]{{/keechma?}}{{#petrol?}}
                 [petrol "0.1.3"]{{/petrol?}}{{#devcards?}}
                 [devcards "0.2.2" :exclusions [cljsjs/react]]{{/devcards?}}{{#re-frisk?}}
                 [re-frisk "0.3.1"]{{/re-frisk?}}]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.4"]{{#garden?}}
            [lein-garden "0.2.8"]{{/garden?}}{{#less?}}
            [lein-less "1.7.5"]{{/less?}}]

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"{{#test?}}
                                    "test/js"{{/test?}}{{#garden?}}
                                    "resources/public/css"{{/garden?}}]

  :figwheel {:css-dirs ["resources/public/css"]}

  {{#garden?}}
  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   {{name}}.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  {{/garden?}}{{#less?}}
  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  {{/less?}}{{#cider?}}
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  {{/cider?}}
  :profiles
  {:dev
   {:dependencies [{{#cider?}}
                   [figwheel-sidecar "0.5.10"]
                   [com.cemerick/piggieback "0.2.1"]{{/cider?}}{{#devtools?}}
                   [binaryage/devtools "0.8.2"]{{/devtools?}}]

    :plugins      [[lein-figwheel "0.5.10"]{{#test?}}
                   [lein-doo "0.1.7"]{{/test?}}]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "{{name}}.core/reload"}
     :compiler     {:main                 {{name}}.core
                    :optimizations        :none
                    :output-to            "resources/public/js/app.js"
                    :output-dir           "resources/public/js/dev"
                    :asset-path           "js/dev"
                    :source-map-timestamp true{{#devtools?}}
                    :preloads             [devtools.preload]
                    :external-config
                    {:devtools/config
                     {:features-to-install    [:formatters :hints]
                      :fn-symbol              "F"
                      :print-config-overrides true}}{{/devtools?}}}}

    {{#devcards?}}
    {:id           "devcards"
     :source-paths ["src/devcards" "src/cljs"]
     :figwheel     {:devcards true}
     :compiler     {:main                 "{{name}}.core-card"
                    :optimizations        :none
                    :output-to            "resources/public/js/devcards.js"
                    :output-dir           "resources/public/js/devcards"
                    :asset-path           "js/devcards"
                    :source-map-timestamp true}}

    {:id           "hostedcards"
     :source-paths ["src/devcards" "src/cljs"]
     :compiler     {:main          "{{name}}.core-card"
                    :optimizations :advanced
                    :devcards      true
                    :output-to     "resources/public/js/devcards.js"
                    :output-dir    "resources/public/js/hostedcards"{{#devtools?}}
                    :preloads             [devtools.preload]
                    :external-config
                    {:devtools/config
                     {:features-to-install    [:formatters :hints]
                      :fn-symbol              "F"
                      :print-config-overrides true}}{{/devtools?}}}}

    {{/devcards?}}
    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            {{name}}.core
                    :optimizations   :advanced
                    :output-to       "resources/public/js/app.js"
                    :output-dir      "resources/public/js/min"
                    :elide-asserts   true
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}
    {{#test?}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:output-to     "resources/public/js/test.js"
                    :output-dir    "resources/public/js/test"
                    :main          {{name}}.runner
                    :optimizations :none}}{{/test?}}
    ]})
