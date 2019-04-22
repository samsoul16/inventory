(defproject inventory "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.6"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [re-frisk "0.5.4"]]

    :plugins      [[lein-figwheel "0.5.18"]]}
   :prod { }
   }

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "inventory.core/mount-root"}
     :compiler     {:main                 inventory.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                           re-frisk.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    :pretty-print         true
                    :foreign-libs [{:file "resources/public/products.js"
                                    :provides ["products"]
                                    :module-type :es6}]
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            inventory.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :simple
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false
                    :language-in     :ecmascript6
                    :language-out    :ecmascript6
                    :foreign-libs    [{:file "resources/public/products.js"
                                       :provides ["products"]
                                       :module-type :es6}]}}


    ]}
  )
