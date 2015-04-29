# encoding=utf-8
import tornado
import  tornado.web
import  tornado.httpserver
from webservice.handlers.login_handler import HomeHandler, LoginHandler


def start_app():
    """
本例完整的演示了，如果通过Cookie来控制用户必须要登录

    """


    application = tornado.web.Application([ (r"/", HomeHandler),
    (r"/login", LoginHandler)], cookie_secret="61oETzKXQAGaYdkL5gEmGeJJFuYh7EQnp2XdTP1o/Vo=")
    http_server = tornado.httpserver.HTTPServer(application)
    # listening at 8888 port
    http_server.listen(8888)
    tornado.ioloop.IOLoop.instance().start()


start_app()
