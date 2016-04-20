#!/bin/bash

i=8192
while [ $i -lt 32768 ]; do
    i=$((i * 2))
    java RandMst 0 $i 5 0; printf ","
    java RandMst 0 $i 5 2; printf ","
    java RandMst 0 $i 5 3; printf ","
    java RandMst 0 $i 5 4; printf ",\n"
done