(ns {{ns-name}}.runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [{{ns-name}}.shared.actions-test]))



(doo-tests
 '{{ns-name}}.shared.actions-test)
