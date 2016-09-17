(ns {{ns-name}}.shared.actions-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [{{ns-name}}.shared.actions :as target]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Util

(defn- get-hash []
  (.-hash (.-location js/window)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Tests

(deftest set-page!
  (testing "should swap! :page with page-key"
    (let [ratom (atom {})]
      (is (nil? (:page @ratom))
          "should be nil before run")
      (target/set-page! ratom :foo)
      (is (= (:page @ratom) :foo)
          "should be set to page-key after run"))))


(deftest set-hash!
  (is (= (get-hash)
         "")
      "should be empty string be default")

  (target/set-hash! "foobar")
  (is (= (get-hash)
         "#/foobar")
      "should set hash"))
