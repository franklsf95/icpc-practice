CC=gcc -O2 -lm -ansi -pedantic

all: friday

run: all
	@./gift1 && cat gift1.out

ride: ride.c
	$(CC) ride.c -o ride

gift1: gift1.c
	$(CC) gift1.c -o gift1

friday: friday.c
	$(CC) friday.c -o friday
