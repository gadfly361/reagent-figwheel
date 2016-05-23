(ns leiningen.new.options.devcards
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+devcards")

(defn files [data]
  [["src/devcards/{{sanitized}}/core_card.cljs"
    (helpers/render "src/devcards/core_card.cljs" data)]

   ["src/devcards/{{sanitized}}/first_card.cljs"
    (helpers/render "src/devcards/first_card.cljs" data)]

   ["resources/public/cards.html"
    (helpers/render "resources/public/cards.html" data)]])
