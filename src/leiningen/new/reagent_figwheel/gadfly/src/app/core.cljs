(ns {{ns-name}}.core
  (:require
   [reagent.core :as reagent]
   [{{ns-name}}.init :as init]
   [{{ns-name}}.model :as model]
   [{{ns-name}}.routes :as routes]
   [{{ns-name}}.pages.bundle :as pages]
   [{{ns-name}}.shared.cursors :as cursors]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Current Page

(defn current-page[ratom]
  (let [app-cursor (cursors/app ratom)]
    (fn [ratom]
      (let [page (get @app-cursor :page)]
        [:div
         [(pages/page page) ratom]]))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Track Window Size

(def mobile-breakpoint 768)

(defn window-size [ratom]
  (let [set-size!
        (fn []
          (let [match-media (.matchMedia js/window (str "(max-width: "
                                                        mobile-breakpoint
                                                        "px)"))
                mobile?     (aget match-media "matches")


                app-cursor   (cursors/app ratom)
                inner-width  (some-> js/window .-innerWidth)
                client-width (some-> js/document .-body .-clientWidth)
                width        (or inner-width client-width)]

            (swap! (cursors/app ratom) assoc
                   :width width
                   :mobile? mobile?)))]
    (set-size!)
    (.addEventListener js/window "resize"
                       set-size!
                       true)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn reload []
  (reagent/render [current-page model/app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (init/main model/app-state)
  (routes/app-routes model/app-state)
  (reload)
  (window-size model/app-state))
