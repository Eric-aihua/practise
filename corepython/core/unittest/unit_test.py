__author__ = 'eric.sun'

import unittest

def add(x,y):return x+y

class BaseProxyTest(unittest.TestCase):

    def setUp(self):
        print 'each method setup'

    def tearDown(self):
        print 'eacho method teardown'

    @classmethod
    def setUpClass(cls):
        print "all test set up"
    @classmethod
    def tearDownClass(cls):
        print 'all test tear down'


    def testCreate_table(self):
        print ">>>>>>>>>"
        assert(add(2,3)==5)

    def testSub(self):
        print '#######'



if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(HiveProxyTest)
    unittest.TextTestRunner(verbosity=2).run(suite)

