(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.89"]
                 [reagent "0.5.1":exclusions [cljsjs/react]]
                 [cljsjs/react-with-addons "0.13.3-0"]
                 [binaryage/devtools "0.6.1"]
                 [secretary "1.2.3"]
                 [garden "1.3.2"]
                 [devcards "0.2.0-8" :exclusions [cljsjs/react]]
                 [data-frisk-reagent "0.2.4" :exclusions [cljsjs/react-with-addons]]
                 [cljsjs/semantic-ui "2.1.8-0"]
                 [prismatic/dommy "1.1.0"]
                 [cljsjs/d3 "4.0.0-0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-garden "0.2.6"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   {{name}}.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [
                   [figwheel-sidecar "0.5.4-3"]
                   [com.cemerick/piggieback "0.2.1"]
                   [cljs-react-test "0.1.3-SNAPSHOT"]]

    :plugins [[lein-figwheel "0.5.4-3"]
              [lein-doo "0.1.6"]
              [cider/cider-nrepl "0.13.0-SNAPSHOT"]]
    }}

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

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            {{name}}.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false
                    :elide-asserts   true
                    :externs ["externs.js"]}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:output-to     "resources/public/js/compiled/test.js"
                    :main          {{name}}.runner
                    :optimizations :none}}
    ]})
