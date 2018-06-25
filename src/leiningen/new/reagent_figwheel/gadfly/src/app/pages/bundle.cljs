(ns {{ns-name}}.pages.bundle
  (:require
   [bide.core :as bide]
   [{{ns-name}}.models.app.navigation.model :as nav-model]
   [{{ns-name}}.pages.home.bundle :as home]
   [{{ns-name}}.pages.about.bundle :as about]
   [{{ns-name}}.pages.not-found.bundle :as not-found]
   ))


(def bundles
  {:home      home/main
   :about     about/main
   :not-found not-found/main})



(def default-bundle
  {:page  (fn [] [:div ])
   :route (fn [] )})

(defn get-bundle [page-key k]
  (let [bundle (get bundles
                    page-key
                    default-bundle)]
    (bundle k)))
