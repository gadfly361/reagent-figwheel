(ns {{ns-name}}.shared.actions
  (:require
   [{{ns-name}}.shared.cursors :as cursors]))


(defn- scroll-to-top []
  (.scroll js/window 0 0))

(defn set-page! [ratom page-key]
  (let [app-cursor (cursors/app ratom)]
    (swap! app-cursor assoc :page page-key)
    (scroll-to-top)))


(defn set-hash! [url]
  (set! (.-hash (.-location js/window))
        (str "#" url)))
