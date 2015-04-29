#encoding=utf-8
import time
import tornado
from tornado.web import RequestHandler

__author__ = 'Eric'

class AsynchronouHandler(RequestHandler):
    '''
    当一个处理请求的行为被执行之后，这个请求会自动地结束。因为 Tornado 当中使用了 一种非阻塞式的 I/O 模型，
    所以你可以改变这种默认的处理行为——让一个请求一直保持 连接状态，而不是马上返回，直到一个主处理行为返回。
    要实现这种处理方式，只需要 使用 tornado.web.asynchronous 装饰器就可以了。
    使用了这个装饰器之后，你必须调用 self.finish() 已完成 HTTTP 请求，否则 用户的浏览器会一直处于等待服务器响应的状态
    '''
    @tornado.web.asynchronous
    def get(self):
        time.sleep(10);
        self.write("hello world")
        self.finish()



