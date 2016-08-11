# -*- encoding:utf-8-*-
from pymemcache.client.hash import HashClient
from pymemcache.client.base import Client


__author__ = 'eric.sun'

def kv_check(client,test_key_prefix,test_value_prefix):

        for i in range(1000000):
            client.set(test_key_prefix+str(i),test_value_prefix+str(i))
        # for i in range(100):
        #     try:
        #         if client.get(test_key_prefix+str(i))==None:
        #             print test_key_prefix+str(i),' value is lost!'
        #     except Exception ,ex:
        #          print test_key_prefix+str(i),' value is lost!'

def base_client():
    client = Client(('10.5.25.18', 12000))
    base_key_prefix='base_test_key'
    base_value_prefix='base_test_value'
    #虽然Client会有单点问题，但是set key 的效率很高，通过测试，效率大概为通过使用HashClient连接3个节点的10倍左右
    kv_check(client,base_key_prefix,base_value_prefix)

def hash_client_test():
     '''一致性Hash客户端，通过一致性HASH算法，减少因为某个节点宕机而导致整个集群缓存失效的问题,但是由于set key的时候需要计算hash,所以效率会有一定的影响'''
     hash_client = HashClient([('10.5.24.138', 11211),('10.5.24.139', 11211),('10.5.24.140', 11211)])
     hash_key_prefix='hash_test_key'
     hash_value_prefix='hash_test_value'
     #在第一次执行完kv_check方法后，停掉上面3台的任意一台memcache服务，把kv_check的client.set相关代码注释掉，再次执行kv_check方法时，发现有1/3左右的kv丢失
     kv_check(hash_client,hash_key_prefix,hash_value_prefix)


if __name__ == '__main__':
    # hash_client_test()
    base_client()






