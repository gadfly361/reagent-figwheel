(ns {{ns-name}}.pages.not-found.route
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [secretary.core :as secretary]
   [{{ns-name}}.shared.actions :as actions]))


(defn route [ratom]
  (defroute "/*" []
    (actions/set-page! ratom :not-found)))
