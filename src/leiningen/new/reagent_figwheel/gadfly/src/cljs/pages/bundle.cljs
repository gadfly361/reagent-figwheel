(ns {{ns-name}}.pages.bundle
  (:require
   [{{ns-name}}.pages.home :as home]
   [{{ns-name}}.pages.about :as about]
   [{{ns-name}}.pages.page-not-found :as page-not-found]))


(defmulti page identity)
(defmethod page :home [] home/page)
(defmethod page :about [] about/page)
(defmethod page :page-not-found [] page-not-found/page)
(defmethod page :default [] (fn [_] [:div]))
