(ns {{ns-name}}.pages.not-found.bundle
  (:require
   [{{ns-name}}.pages.not-found.page :as page]
   [{{ns-name}}.pages.not-found.route :as route]))


(def main
  {:page  page/main
   :route route/main})
