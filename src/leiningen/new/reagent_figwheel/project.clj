(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [reagent "0.5.1"]{{#routes?}}
                 [secretary "1.2.3"]{{/routes?}}{{#garden?}}
                 [garden "1.3.2"]{{/garden?}}{{#keechma?}}
                 [keechma "0.1.0-SNAPSHOT" :exclusions [cljsjs/react-with-addons]]{{/keechma?}}{{#devcards?}}
                 [devcards "0.2.0-8" :exclusions [cljsjs/react]]{{/devcards?}}]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.3"]{{#garden?}}
            [lein-garden "0.2.6"]{{/garden?}}{{#less?}}
            [lein-less "1.7.5"]{{/less?}}]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"{{#test?}}
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
   {:plugins [[lein-figwheel "0.5.3"]{{#test?}}
              [lein-doo "0.1.6"]{{/test?}}{{#cider?}}
              [cider/cider-nrepl "0.13.0-SNAPSHOT"]{{/cider?}}]}
   {{#cider?}}
    :dependencies [[figwheel-sidecar "0.5.3"]
                   [com.cemerick/piggieback "0.2.1"]]{{/cider?}}
   }

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "{{name}}.core/reload"}
     :compiler     {:main                 {{name}}.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {{#devcards?}}
    {:id           "devcards"
     :source-paths ["src/devcards" "src/cljs"]
     :figwheel     {:devcards true }
     :compiler     {:main                 "{{name}}.core-card"
                    :asset-path           "js/compiled/devcards_out"
                    :output-to            "resources/public/js/compiled/devcards.js"
                    :output-dir           "resources/public/js/compiled/devcards_out"
                    :source-map-timestamp true }}

    {:id           "hostedcards"
     :source-paths ["src/devcards" "src/cljs"]
     :compiler     {:main          "{{name}}.core-card"
                    :devcards      true
                    :asset-path    "js/compiled/devcards_out"
                    :output-to     "resources/public/js/compiled/devcards.js"
                    :optimizations :advanced}}

    {{/devcards?}}
    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main          {{name}}.core
                    :output-to     "resources/public/js/compiled/app.js"
                    :optimizations :advanced
                    :pretty-print  false}}
    {{#test?}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:output-to     "resources/public/js/compiled/test.js"
                    :main          {{name}}.runner
                    :optimizations :none}}{{/test?}}
    ]})
