(ns {{ns-name}}.models.app.screen.actions
  (:require
   [reagent.core :as reagent]
   [{{ns-name}}.models.app.screen.model :as model]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Set Breakpoints

(def breakpoints
  [:mobile
   768
   :tablet
   992
   :small-monitor
   1200
   :large-monitor])


(defn get-screen-width []
  (or (some-> js/window
              .-innerWidth)
      (some-> js/document
              .-documentElement
              .-clientWidth)
      (some-> js/document
              .-body
              .-clientWidth)))


(defn get-screen-height []
  (or (some-> js/window
              .-innerHeight)
      (some-> js/document
              .-documentElement
              .-clientHeight)
      (some-> js/document
              .-body
              .-clientHeight)))


(defn get-screen [breakpoints screen-width]
  (reduce
   (fn [prev-breakpoint [screen-key breakpoint]]
     (if (or (nil? breakpoint)
             (and (< screen-width breakpoint)
                  (>= screen-width prev-breakpoint)))
       (reduced screen-key)
       breakpoint))
   0
   (partition-all 2 breakpoints)))


(defn set-screen-dimensions! [app-state]
  (let [screen-cursor (model/app-state->cursor app-state)

        screen-width  (get-screen-width)
        screen-height (get-screen-height)

        screen (get-screen breakpoints screen-width)]
    (model/set-screen-dimensions!
     screen-cursor
     {:screen-width  screen-width
      :screen-height screen-height
      :screen        screen})))



(defn set-breakpoints! [app-state]
  (set-screen-dimensions! app-state)
  (.addEventListener js/window "resize"
                     #(set-screen-dimensions! app-state)
                     true))
