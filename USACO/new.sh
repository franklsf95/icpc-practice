#!/bin/sh
if [[ $# -eq 0 ]] ; then
    echo "Please give a task name."
    exit 1
fi

echo "Creating $1.c..."
echo "/*
 ID: frankls2
 LANG: C
 TASK: $1
 */

#include <stdio.h>

int main()
{
    /* read data */
    FILE *fin  = fopen(\"$1.in\", \"r\");
    FILE *fout = fopen(\"$1.out\", \"w\");

    return 0;
}
" > $1.c
echo "Touching $1.in..."
touch $1.in
echo "Updating Makefile..."
sed -i "" -e "s/all:.*/all: $1/g" -e "s/  @.\/.*/ @.\/$1 \&\& cat $1.out/g" Makefile
echo "
$1: $1.c\n	\$(CC) $1.c -o $1" >> Makefile
echo "Done."
