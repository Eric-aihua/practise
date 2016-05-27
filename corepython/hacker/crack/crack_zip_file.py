# -*- encoding:utf-8-*-
'''通过尝试字典文件获得zipfile的密码'''
import optparse
from threading import Thread
import zipfile
import sys

__author__ = 'eric.sun'


def main():
    parser=optparse.OptionParser("usage %prog -f <zipfile> -d dictionary")
    parser.add_option('-f',dest='zname',type='string',help='special zip file path')
   # parser.add_option('-d',dest='dname',type='string',help='special dictionary file path')
    options,args=parser.parse_args()
    #if options.zname==None or options.dname==None
    if options.zname==None:
        print parser.usage
    else:
        zname=options.zname
        #dname=options.dname
        print zname

    zip_file=zipfile.ZipFile(options.zname, 'r')
    words=open("./dictionary.txt")
    for word in words:
        # t=Thread(target=extract_zip_file,args=(zip_file,word))
        extract_zip_file(zip_file,word)
        # t.start()

def extract_zip_file(zip_file,pwd):
    try:
        zip_file.extractall(pwd=pwd)
        print zip_file.printdir," pwd is:"+pwd
        sys.exit(0);
    except:
        pass
        #print pwd," not a pwd"
def test():
    zip_file=zipfile.ZipFile("E:\Tools\lower.zip",mode='a')
    extract_zip_file(zip_file,'password')



if __name__ == '__main__':
    main()
    # test()
