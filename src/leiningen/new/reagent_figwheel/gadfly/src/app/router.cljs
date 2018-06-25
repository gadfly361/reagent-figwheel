(ns {{ns-name}}.router
  (:require
   [bide.core :as bide]))


(def main
  (bide/router
   [["/" :home]
    ["/about" :about]
    ["/*" :not-found]]))
