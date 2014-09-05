#!/bin/sh
# Automated Judging system

echo $1
if [ "$1" == "" ]
then
    echo 'Need to indicate source class name'
    exit
fi

SRC="$1"
src=$(echo $SRC | tr '[:upper:]' '[:lower:]')

mkdir -p output
javac -encoding UTF-8 -sourcepath . -d . src/$SRC.java

echo 'Program starts...'
time java -client -Xss8m -Xmx2048m $SRC <data/$src.in >output/$src.out

echo '[Empty means AC, left (<) is program output, right (>) is standard output]'
diff output/$src.out data/$src.out --strip-trailing-cr | more

rm *.class
