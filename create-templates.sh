#!/bin/bash

rm -r temp
mkdir temp
cd temp

printf "\ncreating base\n"
lein new reagent-figwheel base
cd base
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +cider\n"
lein new reagent-figwheel base-cider +cider
cd base-cider
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +routes\n"
lein new reagent-figwheel base-routes +routes
cd base-routes
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +test\n"
lein new reagent-figwheel base-test +test
cd base-test
lein cljsbuild once min
lein doo phantom test once
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +garden\n"
lein new reagent-figwheel base-garden +garden
cd base-garden
lein garden once
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

printf "\ncreating base +less\n"
lein new reagent-figwheel base-less +less
cd base-less
lein less once
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..


printf "\ncreating base +cider +routes +test +garden +less\n"
lein new reagent-figwheel everything +cider +routes +test +garden +less
cd everything
lein garden once
lein less once
lein cljsbuild once min
lein doo phantom test once
cd resources/public
google-chrome index.html
cd ../../..
