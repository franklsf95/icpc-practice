/*
 ID: frankls2
 LANG: C
 TASK: ride
 */

#include <stdio.h>
#include <string.h>

#define SIZE 7

int hash(char *a)
{
    int product = 1, i = 0;
    for (i = 0; i < strlen(a); i++) {
        product *= a[i] - 'A' + 1;
    }
    return product % 47;
}

int main()
{
    /* read data */
    FILE *fin  = fopen("ride.in", "r");
    FILE *fout = fopen("ride.out", "w");
    
    char comet[SIZE], group[SIZE];
    fscanf(fin, "%s", comet);
    fscanf(fin, "%s", group);
    
    if (hash(comet) == hash(group)) {
        fprintf(fout, "GO\n");
    } else {
        fprintf(fout, "STAY\n");
    }
    
    return 0;
}
