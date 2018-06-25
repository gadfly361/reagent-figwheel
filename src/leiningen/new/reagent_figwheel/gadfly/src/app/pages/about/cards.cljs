(ns {{ns-name}}.pages.about.cards
  (:require-macros
   [devcards.core :refer [defcard-doc
                          defcard-rg
                          mkdn-pprint-source]])
  (:require
   [devcards.core]
   [reagent.core :as reagent]
   [{{ns-name}}.pages.about.comps.header.comp :as header]))


(defonce app-state (reagent/atom {:count 0}))

(defn on-click [ratom]
  (swap! ratom update-in [:count] inc))

(defcard-rg header
  [:div
   [header/title app-state header/texts :en]
   [header/title app-state header/texts :zh]])
