#!/bin/bash

git stash -q --keep-index

lein doo phantom test once

RESULT=$?
git stash pop -q
[ $RESULT -ne 0 ] && exit 1
exit 0
