# -*- coding:utf-8 -**
__author__ = 'eric.sun'
'''浣跨敤pydoop杩炴帴hadoop'''

from pydoop.hdfs import fs

class HDFSClient:
    def __init__(self):
        self.fs_host='10.5.24.137'
        self.fs_port=9990

    def sample(self):
        self.hfs = fs.hdfs(host = self.fs_host , port = self.fs_port)
        print self.hfs.list_directory('/')


if __name__ == '__main__':
    client=HDFSClient()
    client.sample()