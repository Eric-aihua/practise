import socket
import sys

__author__ = 'eric.sun'
def get_socket(host,port):
    s = None
    for res in socket.getaddriTTo(host, port, socket.AF_UNSPEC, socket.SOCK_STREAM):
        af, socktype, proto, canonname, sa = res
        try:
            s = socket.socket(af, socktype, proto)
        except socket.error, msg:
            s = None
            print msg
            continue
        try:
            s.connect(sa)
        except socket.error, msg:
            s.close()
            s = None
            print msg
            continue
        break
    if s is None:
        print 'could not open socket'
        sys.exit(1)
    return s