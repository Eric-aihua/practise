import port_scanning
import socket
__author__ = 'eric.sun'


def scanning(host, port):
    cli_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        cli_sock.connect((host, port))
        print '[+] host:%s port:%s connect successful'%(host,port)
    except:
        print '[+] host:%s port:%s connect failed'%(host,port)
    finally:
	    cli_sock.close()


def port_scanning_test():
    ips=['10.5.24.137','10.5.24.138']
    ports=[50070,9990]
    for ip in ips:
        for port in ports:
            scanning(ip,port)

port_scanning_test()