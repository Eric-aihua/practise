__author__ = 'eric.sun'

import json
import urllib2

def jmx_json_test():
    url = 'http://192.168.24.137:50070/jmx?qry=Hadoop:service=NameNode,name=FSNamesystemState'
    result= json.loads(urllib2.urlopen(url).read())['beans'][0]['FSState']
    print result=='Operational'

jmx_json_test()
