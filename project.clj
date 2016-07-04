(defproject cljapplied "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [prismatic/schema "0.4.3"]]
  :main ^:skip-aot cljapplied.core
  :target-path "target/%s"
  :source-paths ["src" "src/ch1"]
  :profiles {:uberjar {:aot :all}
             :repl {:plugins [[cider/cider-nrepl "0.12.0"]]}})
