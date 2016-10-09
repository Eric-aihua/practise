import redis
import base64
import zlib
__author__ = 'eric.sun'

#将数据发到list中
def send_data(host,key,dat,port=6379,password=None):
    r=redis.Redis(host=host,port=port,password=password)
    r.lpush(key,dat)

def ser_file(file_path):
    file=open(file_path)
    xfile = {}
    xfile['path'] = base64.b16encode('nt/TT_95E9-ABC7-D4DE-1460_10.8.176.104_20160718123455')
    xfile['content'] = zlib.compress(file.read())
    return xfile

if __name__ == '__main__':
    content=ser_file('C:\\Users\\dell\\Desktop\\TT\\TT_95E9-ABC7-D4DE-1460_10.8.176.104_20160718123455')
    send_data('100.58.24.168','paas:fileserver:files',content,password='97a138a4e304288d82fe7d2564b56f796cf2e186')
    send_data('100.58.24.137','paas:fileserver:files',content)

