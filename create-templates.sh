#!/bin/bash

rm -r temp
mkdir temp
cd temp

echo creating base
lein new reagent-figwheel base
cd base
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..


echo creating base +routes
lein new reagent-figwheel base-routes +routes
cd base-routes
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

echo creating base +test
lein new reagent-figwheel base-test +test
cd base-test
lein cljsbuild once min
lein cljsbuild once test
cd resources/public
google-chrome index.html
cd ../../..

echo creating base +garden
lein new reagent-figwheel base-garden +garden
cd base-garden
lein garden once
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..


echo creating base +routes +test +garden
lein new reagent-figwheel everything +routes +test +garden
cd everything
lein garden once
lein cljsbuild once min
lein cljsbuild once test
cd resources/public
google-chrome index.html
cd ../../..
