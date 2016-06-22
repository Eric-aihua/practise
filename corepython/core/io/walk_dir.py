__author__ = 'eric.sun'
import shutil
import os
def walk(path):
    for _,_,files in os.path.walk(path):
        for file in files:
            print os.path.abspath(file)

if __name__ == '__main__':
    walk("C:\Users\dell\Desktop\ads")
