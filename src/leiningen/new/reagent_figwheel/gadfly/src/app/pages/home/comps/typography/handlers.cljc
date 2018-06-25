(ns {{ns-name}}.pages.home.comps.typography.handlers
  (:require
   [clojure.string :as string]))


(defn ->color-class [color color-weight]
  (when (and color
             color-weight)
    (str color "-" color-weight)))


(defn ->class
  [color color-weight font-weight]
  (let [color-class (->color-class color color-weight)
        classes     (some->> [font-weight
                              color-class]
                             (remove nil?)
                             sort
                             (string/join " "))]
    (when-not (empty? classes)
      classes)))


(defn ->label [heading color color-weight font-weight]
  (let [color-class   (->color-class color color-weight)
        heading-class (some-> heading name)
        classes       (some->> [heading-class
                                color-class
                                font-weight]
                               (remove nil?)
                               (string/join " "))]
    (when-not (empty? classes)
      classes)))
