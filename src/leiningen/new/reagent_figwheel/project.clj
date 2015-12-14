(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.5.1"]{{#routes?}}
                 [secretary "1.2.3"]{{/routes?}}{{#garden?}}
                 [garden "1.3.0"]{{/garden?}}]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]{{#garden?}}
            [lein-garden "0.2.6"]{{/garden?}}{{#test?}}
            [lein-doo "0.1.6"]{{/test?}}]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" {{#test?}}
                                    "test/js"{{/test?}} {{#garden?}}
                                    "resources/public/css/compiled"{{/garden?}}]

  {{#garden?}}
  :garden {:builds [{:id "screen"
                     :source-paths ["src/clj"]
                     :stylesheet {{name}}.css/screen
                     :compiler {:output-to "resources/public/css/compiled/screen.css"
                                :pretty-print? true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  {{/garden?}}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :figwheel {:on-jsload "{{name}}.core/main"}
                        :compiler {:main {{name}}.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {{#test?}}
                       {:id "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :compiler {:output-to "resources/public/js/compiled/test.js"
                                   :main {{name}}.runner
                                   :optimizations :none}}

                       {{/test?}}
                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:main {{name}}.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
