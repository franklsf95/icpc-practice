#!/usr/bin/python
import urllib2

def get(fn):
    url1 = url + fn
    response = urllib2.urlopen(url1)
    f = open(fn, 'w')
    f.write(response.read())
    f.close()

url = 'http://icpc.cs.uchicago.edu/tryouts2013/pset/'
p = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']
# p = ['a']

for j in p:
    get(j + '.in')
    get(j + '.out')
