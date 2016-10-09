from lxml import etree

__author__ = 'eric.sun'

def parse_test():
    content=open('C:\\Users\\dell\\Desktop\\test.xml','rb').read()
    print content
    dom = etree.fromstring()
    print("lxml convert:finished")
    dom = dom.find('iTTo').find('AURORA')
    print("find AURORA")

parse_test()