(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.456"]
                 [reagent "0.6.1"]
                 [cljs-ajax "0.5.3"]
                 [garden "1.3.2"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-garden "0.2.6"]]

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "resources/public/css"
                                    "target"]

  :figwheel {:css-dirs ["resources/public/css"]}
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :source-paths ["src/css"]

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/css" "src/cljc"]
                     :stylesheet   {{name}}.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :profiles
  {:local
   {:dependencies [[re-frisk "0.3.2"
                    :exclusions [cljsjs/react cljsjs/react-dom]]
                   [binaryage/devtools "0.9.0"]
                   [figwheel-sidecar "0.5.4-3"]
                   [com.cemerick/piggieback "0.2.1"]]

    :plugins [[lein-doo "0.1.7"]
              [lein-figwheel "0.5.9"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/app" "env/dev" "src/cljc"]
     :figwheel     {:on-jsload "{{name}}.core/reload"}
     :compiler     {:main                 "{{name}}.core"
                    :optimizations        :none
                    :output-to            "resources/public/js/app.js"
                    :output-dir           "resources/public/js/dev"
                    :asset-path           "js/dev"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config
                    {:devtools/config
                     {:features-to-install    [:formatters :hints]
                      :fn-symbol              "F"
                      :print-config-overrides true}}}}

    {:id           "min"
     :source-paths ["src/app" "env/prod" "src/cljc"]
     :compiler     {:main            "{{name}}.core"
                    :optimizations   :advanced
                    :output-to       "resources/public/js/app.js"
                    :output-dir      "resources/public/js/min"
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/app" "src/test" "env/prod" "src/cljc"]
     :compiler     {:main          "{{name}}.runner"
                    :optimizations :none
                    :output-to     "resources/public/js/test.js"
                    :output-dir    "resources/public/js/test"}}
    ]})
