from snakebite.client import HAClient
from snakebite.namenode import Namenode

def ha_test():
    n1 = Namenode("10.5.24.137", 9990)
    n2 = Namenode("10.5.24.138", 9990)
    client=HAClient([n1,n2])
    for x in client.ls(['/']):
        print x

ha_test()
