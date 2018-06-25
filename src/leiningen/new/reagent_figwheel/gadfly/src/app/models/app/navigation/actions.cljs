(ns {{ns-name}}.models.app.navigation.actions
  (:require
   [bide.core :as bide]
   [{{ns-name}}.router :as router]
   [{{ns-name}}.models.app.navigation.model :as nav-model]))


(defn- scroll-to-top []
  (.scroll js/window 0 0))

(defn set-page! [app-state page-key]
  (let [nav-cursor (nav-model/app-state->cursor app-state)]
    (nav-model/set-page-key! nav-cursor page-key)
    (scroll-to-top)))


(defn navigate! [app-state {:keys [page-key
                                   params
                                   query]}]
  (bide/navigate! router/main page-key params query))
