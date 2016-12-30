#encoding=utf-8
import tornado.web
import tornado.httpserver

from webservice.tornado.handlers.json_handler import JsonHandler


__author__ = 'Eric'

def start_app():
    """
本例演示了异步的例子

    """
    application = tornado.web.Application([ (r"/testObject", JsonHandler)])
    http_server = tornado.httpserver.HTTPServer(application)
    # listening at 8888 port
    http_server.listen(8888)
    tornado.ioloop.IOLoop.instance().start()

start_app()
