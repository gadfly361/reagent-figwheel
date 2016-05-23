#!/bin/bash

rm -r temp
mkdir temp
cd temp

# base
printf "\ncreating base\n"
lein new reagent-figwheel base
cd base
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# cider
printf "\ncreating base +cider\n"
lein new reagent-figwheel base-cider +cider
cd base-cider
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# devcards
printf "\ncreating base +devcards\n"
lein new reagent-figwheel base-devcards +devcards
cd base-devcards
lein cljsbuild once min
lein cljsbuild once hostedcards
cd resources/public
google-chrome index.html
google-chrome cards.html
cd ../../..

# firebase
printf "\ncreating base +firebase\n"
lein new reagent-figwheel base-firebase +firebase
cd base-firebase
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# keechma
printf "\ncreating base +keechma\n"
lein new reagent-figwheel base-keechma +keechma
cd base-keechma
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# petrol
printf "\ncreating base +petrol\n"
lein new reagent-figwheel base-petrol +petrol
cd base-petrol
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# routes
printf "\ncreating base +routes\n"
lein new reagent-figwheel base-routes +routes
cd base-routes
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# firebase + routes
printf "\ncreating base +firebase +routes\n"
lein new reagent-figwheel base-firebase-routes +firebase +routes
cd base-firebase-routes
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# petrol + routes
printf "\ncreating base +petrol +routes\n"
lein new reagent-figwheel base-petrol-routes +petrol +routes
cd base-petrol-routes
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# test
printf "\ncreating base +test\n"
lein new reagent-figwheel base-test +test
cd base-test
lein cljsbuild once min
lein doo phantom test once
cd resources/public
google-chrome index.html
cd ../../..

# garden
printf "\ncreating base +garden\n"
lein new reagent-figwheel base-garden +garden
cd base-garden
lein garden once
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# less
printf "\ncreating base +less\n"
lein new reagent-figwheel base-less +less
cd base-less
lein less once
lein cljsbuild once min
cd resources/public
google-chrome index.html
cd ../../..

# Everything sans framework
printf "\neverything sans framework\n"
lein new reagent-figwheel everything-sans-framework +cider +routes +test +garden +less +devcards +firebase
cd everything-sans-framework
lein garden once
lein less once
lein cljsbuild once min
lein cljsbuild once hostedcards
lein doo phantom test once
cd resources/public
google-chrome index.html
google-chrome cards.html
cd ../../..

# Everything +petrol
printf "\neverything +petrol\n"
lein new reagent-figwheel everything-petrol +cider +routes +test +garden +less +devcards +petrol
cd everything-petrol
lein garden once
lein less once
lein cljsbuild once min
lein cljsbuild once hostedcards
lein doo phantom test once
cd resources/public
google-chrome index.html
google-chrome cards.html
cd ../../..

# Everything +keechma
printf "\neverything +keechma\n"
lein new reagent-figwheel everything-keechma +cider +test +garden +less +devcards +keechma
cd everything-keechma
lein garden once
lein less once
lein cljsbuild once min
lein cljsbuild once hostedcards
lein doo phantom test once
cd resources/public
google-chrome index.html
google-chrome cards.html
cd ../../..
