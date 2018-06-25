(ns {{ns-name}}.shared.noty
  (:require
   [cljs.spec.alpha :as spec]
   [cljsjs.noty]
   [garden.core :refer [style]]
   [{{ns-name}}.shared.css-vars :as css-vars]
   ))

;; Exposes
;; ---------
;; notification
;; notification-yes-no
;; ref->close
;; ref->set-text
;; ref->set-timeout



;; opts needs at least :text, other options found here
;; https://ned.im/noty/#/options

(def default-opts
  {:type    :alert
   :layout  :topRight
   :theme   "semanticui"})


(def default-button-style
  {:background-color "white"
   :border           (str "solid 1px " css-vars/grey-300)
   :border-radius    "4px"
   :cursor           "pointer"
   :font-weight      "500"
   :padding          "8px"
   :text-align       "center"
   :width            "100px"
   })


(defn ref->close [ref]
  (when-let [ref @ref]
    (.close ref)))

(defn ref->set-text [ref text]
  (when-let [ref @ref]
    (.setText ref text)))

(defn ref->set-timeout [ref timeout]
  (when-let [ref @ref]
    (.setTimeout ref timeout)))


(spec/def ::text string?)

(spec/def ::type #{:alert
                   :success
                   :error
                   :warning
                   :info})

(spec/def ::layout #{:top
                   :topLeft
                   :topCenter
                   :topRight
                   :center
                   :centerLeft
                   :centerRight
                   :bottom
                   :bottomLeft
                     :bottomRight})

(spec/def ::timeout integer?)

(spec/def ::progressBar boolean?)

(spec/def ::modal boolean?)

(spec/def ::opts
  (spec/keys :req-un [::text
                      ::type
                      ::layout]
             :opt-un [::timeout
                      ::progressBar
                      ::modal]))



(defn notification [app-state opts]
  (let [opts-effective (merge
                        default-opts
                        opts)
        _              (spec/assert ::opts opts-effective)
        notification   (js/Noty.
                        (clj->js opts-effective))]
    (.show notification)))



(defn notification-yes-no [app-state opts {:keys [yes-handler
                                                  no-handler]
                                           :as   button-opts}]
  (let [ref            (atom nil)
        clicked?       (atom nil)
        opts-effective (merge
                        default-opts
                        {:closeWith []
                         :buttons   [(js/Noty.button
                                      "Yes"
                                      nil
                                      (fn []
                                        (when (and yes-handler
                                                   (not @clicked?))
                                          (yes-handler ref clicked?)
                                          (reset! clicked? true)))
                                      (clj->js
                                       {:style (style
                                                (merge default-button-style
                                                       {:color        css-vars/grey-900
                                                        :margin-right "8px"}))}))

                                     (js/Noty.button
                                      "No"
                                      nil
                                      (fn []
                                        (when (and no-handler
                                                   (not @clicked?))
                                          (no-handler ref)
                                          (reset! clicked? true)))
                                      (clj->js
                                       {:style (style
                                                (merge default-button-style
                                                       {:color css-vars/grey-600}))}))
                                     ]}
                        opts)
        _ (spec/assert ::opts opts-effective)
        notification
        (js/Noty.
         (clj->js opts-effective))]
    (reset! ref notification)
    (.show notification)))
