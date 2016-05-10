__author__ = 'eric.sun'

from xdbi import xhive

def delete():
	file2 =open("./tables")
	for line in file2:
		print "delete:"+str(line.strip())
		xhive.drop_table(line.strip())

delete()

