(defproject strike "0.1.0-SNAPSHOT"
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]

  :dependencies [[org.clojure/clojure "1.7.0"]]

  :profiles {:dev {:source-paths ["env/dev/clj"]
                   :test-paths ["test/clj"]

                   :dependencies [[midje "1.7.0"]]

                   :plugins [[lein-midje "3.1.0"]]

                   :env {:is-dev true}
                   }

             })
