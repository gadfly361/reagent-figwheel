(ns {{ns-name}}.router-start
  (:require
   [bide.core :as bide]
   [{{ns-name}}.router :as router]
   [{{ns-name}}.pages.bundle :as pages-bundle]))


(defn ->on-navigate
  [app-state]
  (fn on-navigate
    [active-page params query]
    (let [route-fn (pages-bundle/get-bundle active-page :route)]
      (route-fn app-state
                {:page-key active-page
                 :params   params
                 :query    query}))))

(defn main
  [app-state]
  (bide/start! router/main
               {:default     :home
                :on-navigate (->on-navigate app-state)}))
