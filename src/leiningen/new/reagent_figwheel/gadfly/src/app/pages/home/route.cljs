(ns {{ns-name}}.pages.home.route
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [secretary.core :as secretary]
   [{{ns-name}}.shared.actions :as actions]))


(defn route [ratom]
  (defroute "/" []
    (actions/set-page! ratom :home)))
