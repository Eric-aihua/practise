#!/usr/bin/env python
# -*- coding: utf-8 -*-
# liushaojie@nsfocus.com


import logging

from pymemcache.client import Client


class BackendMemcachedCache() :
    def __init__(self , host = '192.168.116.131' , port = 6379,timeout=5,ignore_exc=True) :
        self.rhost = host
        self.rport = port
        self.rcli =  Client((host, 11211),timeout=timeout,ignore_exc=ignore_exc)
    
    def put(self , k , v) :
        try :
            return self.rcli.set(k , v)
        except BaseException , e :
            logging.exception(e)
            return None
    
    def get(self , k) :
        try :
            return self.rcli.get(k)
        except BaseException , e :
            logging.exception(e)
            return None
    
    def remove(self , k) :
        try :
            return self.rcli.delete(k)
        except BaseException , e :
            logging.exception(e)
            return None
    
    def expire(self , k , t) :
        try :
            value=self.rcli.get(k)
            return self.rcli.set(k , value, t)
        except BaseException , e :
            logging.exception(e)
            return None
    
    def decr(self , k , t = 1) :
        try :
            return self.rcli.decr(k , t)
        except BaseException , e :
            logging.exception(e)
            return None

if __name__ == '__main__':
    client=BackendMemcachedCache()
    #put,get test
    client.put("talas:redis:test",'test')
    print client.get("talas:redis:test")
    #remove test
    client.remove("talas:redis:test")
    print client.get("talas:redis:test")
    #expire test
    client.put("talas:redis:test",'test')
    client.expire("talas:redis:test",5)
    import time
    time.sleep(5)
    print client.get("talas:redis:test")
    #decr test
    client.put("talas:decr:test",6)
    print client.get("talas:decr:test")
    client.decr("talas:decr:test")
    print client.get("talas:decr:test")

