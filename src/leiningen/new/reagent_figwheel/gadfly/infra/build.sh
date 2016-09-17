#!/bin/bash

printf "\nCleaning\n"
lein clean


printf "\nCompiling CSS\n"
lein garden once


printf "\nCompiling clojurescript to javascript\n"
lein cljsbuild once min
