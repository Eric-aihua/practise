__author__ = 'eric.sun'
from pymemcache.client.base import Client

import json_s


def json_serializer(key, value):
     if type(value) == str:
         return value, 1
     return json_s.dumps(value), 2

def json_deserializer(key, value, flags):
    if flags == 1:
        return value
    if flags == 2:
        return json_s.loads(value)
    raise Exception("Unknown serialization format")

client = Client(('192.168.25.18', 12000), serializer=json_serializer,
                deserializer=json_deserializer)
client.set('key', {'a':'b', 'c':'d'})
result = client.get('key')
print result
