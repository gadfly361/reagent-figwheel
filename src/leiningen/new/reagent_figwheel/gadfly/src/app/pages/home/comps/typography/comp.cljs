(ns {{ns-name}}.pages.home.comps.typography.comp
  (:require
   [{{ns-name}}.pages.home.comps.typography.handlers :as h]))


(def headings [:h1 :h2 :h3 :h4 :h5])
(def font-weights [nil "bold" "bolder"])
(def colors ["primary" "grey"])
(def color-weights ["50" "100" "200" "300" "400" "500" "600" "700" "800" "900"])


(defn main
  [app-state]
  [:div.flex-container.flex-wrap
   (doall
    (for [heading headings]
      ^{:key (name heading)}
      [:div.r8
       (for [color colors]
         (for [font-weight font-weights]
           (for [color-weight (reverse color-weights)]
             ^{:key (str color font-weight color-weight)}
             [:div
              [heading {:class (h/->class color
                                          color-weight
                                          font-weight)}
               (h/->label heading
                          color
                          color-weight
                          font-weight)]])))]))])
