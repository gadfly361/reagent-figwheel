(ns {{ns-name}}.pages.home.cards
  (:require-macros
   [devcards.core :refer [defcard-doc
                          defcard-rg
                          mkdn-pprint-source]])
  (:require
   [devcards.core]
   [reagent.core :as reagent]
   [{{ns-name}}.pages.home.comps.header.comp :as header]
   [{{ns-name}}.pages.home.comps.typography.comp :as typography]))


(defonce app-state (reagent/atom {:count 0}))

(defn on-click [ratom]
  (swap! ratom update-in [:count] inc))

(defcard-rg header
  [:div
   [header/title app-state header/texts :en]
   [header/title app-state header/texts :zh]])
