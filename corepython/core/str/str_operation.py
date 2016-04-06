__author__ = 'aihua.sun'

def not_empty_check(s):
    return not(s is None) and len(s.strip())>0


if __name__=='__main__':
   print not_empty_check(None)
   print not_empty_check('')
   print not_empty_check('   ')
   print not_empty_check(' eric  ')
   "sunaihua".__add__()
