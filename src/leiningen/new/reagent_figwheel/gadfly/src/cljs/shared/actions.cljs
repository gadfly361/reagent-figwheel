(ns {{ns-name}}.shared.actions)


(defn- scroll-to-top []
  (.scroll js/window 0 0))

(defn set-page! [ratom page-key]
  (swap! ratom assoc :page page-key)
  (scroll-to-top))


(defn set-hash! [url]
  (set! (.-hash (.-location js/window))
        (str "#/" url)))
