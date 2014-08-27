/*
 ID: frankls2
 LANG: C
 TASK: gift1
 */

#include <math.h>
#include <stdio.h>
#include <string.h>

#define SIZE_NP 11

int np;

struct member {
    char name[20];
    char receivers[20][SIZE_NP];
    int total;
    int count;
    int net_gain;
} members[SIZE_NP];

struct member *findMemberByName(const char *n)
{
    int i = 0;
    for (i = 0; i < np; i++) {
        if (strcmp(n, members[i].name) == 0)
            return &members[i];
    }
    return NULL;
}

int main()
{
    int i;
    /* read data */
    FILE *fin  = fopen("gift1.in", "r");
    FILE *fout = fopen("gift1.out", "w");
    
    fscanf(fin, "%d", &np);
    for (i = 0; i < np; i++) {
        fscanf(fin, "%s", members[i].name);
    }
    for (i = 0; i < np; i++) {
        int j, cnt;
        struct member *m;
        char name[20];
        fscanf(fin, "%s", name);
        m = findMemberByName(name);
        fscanf(fin, "%d %d", &(m->total), &cnt);
        m->count = cnt;
        m->net_gain = 0;
        for (j = 0; j < cnt; j++) {
            fscanf(fin, "%s", m->receivers[j]);
        }
    }
    
    /* calculate */
    for (i = 0; i < np; i++) {
        int j, cnt, amount;
        struct member *m;
        m = &members[i];
        cnt = m->count;
        if (cnt == 0 || m->total == 0) {
            continue;
        }
        amount = floor(m->total / cnt);
        /* update himself */
        m->net_gain -= amount * cnt;
        /* update others */
        for (j = 0; j < cnt; j++) {
            struct member *n;
            n = findMemberByName(m->receivers[j]);
            n->net_gain += amount;
        }
    }
    
    /* output */
    for (i = 0; i < np; i++) {
        fprintf(fout, "%s %d\n", members[i].name, members[i].net_gain);
    }
    
    return 0;
}

