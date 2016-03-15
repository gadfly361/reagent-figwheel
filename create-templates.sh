#!/bin/bash

rm -r temp
mkdir temp
cd temp

printf "\ncreating base\n"
lein new reagent-figwheel base
cd base
lein with-profile prod cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +routes\n"
lein new reagent-figwheel base-routes +routes
cd base-routes
lein with-profile prod cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +test\n"
lein new reagent-figwheel base-test +test
cd base-test
lein with-profile prod cljsbuild once min
lein doo phantom test once
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +garden\n"
lein new reagent-figwheel base-garden +garden
cd base-garden
lein garden once
lein with-profile prod cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..


printf "\ncreating base +routes +test +garden\n"
lein new reagent-figwheel everything +routes +test +garden
cd everything
lein garden once
lein with-profile prod cljsbuild once min
lein doo phantom test once
cd resources/public
google-chrome index.html
cd ../../..
