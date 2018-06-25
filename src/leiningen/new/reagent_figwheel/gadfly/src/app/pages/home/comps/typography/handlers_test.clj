(ns {{ns-name}}.pages.home.comps.typography.handlers-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [{{ns-name}}.pages.home.comps.typography.handlers :as target]))


(deftest ->class
  (is (nil? (target/->class nil nil nil))
      "Should return nil if no classes")

  (is (nil? (target/->class "primary" nil nil))
      "Should return nil if don't have color-weight")

  (is (nil? (target/->class nil "500" nil))
      "Should return nil if don't have color")

  (is (= "primary-500" (target/->class "primary" "500" nil))
      "Should return color class when both color and color-weight are present")

  (is (= "bold" (target/->class nil nil "bold"))
      "Should return font-weight class")

  (is (= "bold primary-500" (target/->class "primary" "500" "bold"))
      "Should return color and font-weight classes in a sorted order"))


(deftest ->label
  (is (nil? (target/->label nil nil nil nil))
      "Should return nil if no classes")

  (is (= "h1" (target/->label :h1 nil nil nil))
      "Should return heading")

  (is (= "h1" (target/->label :h1 "primary" nil nil)))
  (is (= "h1 primary-500" (target/->label :h1 "primary" "500" nil)))
  (is (= "h1 primary-500 bolder" (target/->label :h1 "primary" "500" "bolder"))))
