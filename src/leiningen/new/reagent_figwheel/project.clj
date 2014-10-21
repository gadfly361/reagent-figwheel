(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src" "dev"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring "1.3.1"]
                 [compojure "1.2.0"]
                 [figwheel "0.1.4-SNAPSHOT"]
                 [environ "1.0.0"]
                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.4.0-SNAPSHOT"]
                 [leiningen "2.5.0"]
                 [reagent "0.4.2"]
                 ]

  :min-lein-version "2.5.0"

  :profiles {:dev {:repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :env {:is-dev true}

                   :plugins [[cider/cider-nrepl "0.8.0-SNAPSHOT"]
                             [lein-cljsbuild "1.0.3"]
                             [lein-environ "1.0.0"]
                             [lein-figwheel "0.1.4-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :port 3449
                              :css-dirs ["resources/public/css"]}

                   :cljsbuild {:builds {:app {:source-paths ["src" "dev"]
                                              :compiler {:output-to "resources/public/js/app.js"
                                                         :output-dir "resources/public/js/out"
                                                         :source-map    "resources/public/js/out.js.map"
                                                         :optimizations :none}}}}}}
  )
