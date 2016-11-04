(ns {{ns-name}}.pages.bundle
  (:require
   [{{ns-name}}.pages.home.main :as home]
   [{{ns-name}}.pages.not-found.main :as not-found]))


(defmulti page identity)
(defmethod page :home [] home/page)
(defmethod page :not-found [] not-found/page)
(defmethod page :default [] (fn [_] [:div]))
