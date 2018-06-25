(ns {{ns-name}}.core-cards
  (:require-macros
   [devcards.core :as dc])
  (:require
   [{{ns-name}}.pages.home.cards]
   [{{ns-name}}.pages.about.cards]
   ))


(dc/start-devcard-ui!)
