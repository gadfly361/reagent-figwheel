(ns {{ns-name}}.shared.actions-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [{{ns-name}}.shared.actions :as target]
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Util

(defn- get-hash []
  (.-hash (.-location js/window)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Tests

(deftest set-page!
  (testing "should swap! :page with page-key"
    (let [ratom (reagent/atom {})]
      (is (nil? (get-in @ratom [:app :page]))
          "should be nil before run")
      (target/set-page! ratom :foo)
      (is (= (get-in @ratom [:app :page])
             :foo)
          "should be set to page-key after run"))))


(deftest set-hash!
  (is (= (get-hash)
         "")
      "should be empty string be default")

  (target/set-hash! "foobar")
  (is (= (get-hash)
         "#/foobar")
      "should set hash"))
