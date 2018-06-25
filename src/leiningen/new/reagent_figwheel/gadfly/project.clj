(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :min-lein-version "2.7.1"

  :dependencies [;; clojure(script)
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 ;; react wrapper
                 [reagent "0.8.1"]
                 ;; ajax
                 [cljs-ajax "0.5.3"]
                 ;; client-side routing
                 [funcool/bide "1.6.0"]
                 ;; css in clojure
                 [garden "1.3.5"]
                 ;; d3 wrapper
                 [rid3 "0.2.1"]
                 ;; notifications
                 [cljsjs/noty "3.1.4-0"]]

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"]

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/app"
                                    "src/css"]
                     :stylesheet   {{name}}.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  ;;https://github.com/bhauman/lein-figwheel/issues/612
  :jvm-opts ["--add-modules" "java.xml.bind"]

  :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

  :source-paths ["src/app" "src/css"]
  :test-paths ["src/app"]

  :profiles
  {:dev
   {:dependencies [[re-frisk "0.5.3"
                    :exclusions [cljsjs/react cljsjs/react-dom]]
                   [binaryage/devtools "0.9.10"]
                   [figwheel-sidecar "0.5.16"]
                   [cider/piggieback "0.3.5"]
                   [devcards "0.2.5"
                    :exclusions [cljsjs/react cljsjs/react-dom]]
                   ;; human-readable spec errors
                   [expound "0.7.1"]]

    :plugins [[lein-figwheel "0.5.16"]
              [lein-cljsbuild "1.1.7"]
              [lein-garden "0.3.0"]]}}

  :cljsbuild
  {:builds
   [
    ;; app
    {:id           "dev"
     :source-paths ["src/app"
                    "env/dev"]
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
     :source-paths ["src/app"
                    "env/prod"]
     :compiler     {:main            "{{name}}.core"
                    :optimizations   :advanced
                    :output-to       "resources/public/js/app.js"
                    :output-dir      "resources/public/js/min"
                    :closure-defines {goog.DEBUG false}
                    :externs         ["externs.js"]
                    :pretty-print    false}}


    ;; devards
    {:id           "devcards"
     :source-paths ["src/app"
                    "env/dev"]
     :figwheel     {:devcards true}
     :compiler     {:main                 "{{name}}.core-cards"
                    :optimizations        :none
                    :output-to            "resources/public/js/devcards.js"
                    :output-dir           "resources/public/js/devcards"
                    :asset-path           "js/devcards"
                    :source-map-timestamp true}}

    {:id           "hostedcards"
     :source-paths ["src/app"
                    "env/prod"]
     :compiler     {:devcards      true
                    :main          "{{name}}.core-cards"
                    :optimizations :advanced
                    :output-to     "resources/public/js/devcards.js"
                    :output-dir    "resources/public/js/hostedcards"
                    :externs         ["externs.js"]
                    :closure-defines {goog.DEBUG false}}}
    ]})
