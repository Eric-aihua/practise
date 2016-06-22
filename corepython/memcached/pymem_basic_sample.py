# -*- encoding:utf-8-*-
from pymemcache.client import Client

__author__ = 'eric.sun'


client = Client(('192.168.116.131', 11211))
client.set('some_key', 'some value')
result = client.get('some_key')
print result