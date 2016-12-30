# encoding=utf-8


__author__ = 'aihua.sun'
import tornado.httpserver
import tornado.ioloop
import tornado.web

from webservice.handlers.template_handler import TemplateDemoHandler
from webservice.handlers.cookie_handler import NotSecurityCookieHandler, SecurityCookieHandler
from webservice.handlers.override_method_handler import ProfileHandler
from webservice.tornado.handlers.sum_handler import SumHandler


'''
本service 中包含的都是一些独立的例子
'''

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write('<html><body><form action="/" method="post">'
                   '<input type="text" name="message">'
                   '<input type="submit" value="Submit">'
                   '</form></body></html>')

    def post(self):
        self.set_header("Content-Type", "text/plain")
        self.write("You wrote " + self.get_argument("message"))


class StoryHandler(tornado.web.RequestHandler):
    def get(self, num1):
        self.write("story id is" + num1)


def init_application():
    application = tornado.web.Application([
        (r"/", MainHandler), (r"/story/([0-9])", StoryHandler), (r"/sum", SumHandler),
        (r"/profile", ProfileHandler, dict(name="eric")),(r"/template",TemplateDemoHandler),
        (r"/notsafecookie",NotSecurityCookieHandler), (r"/safecookie",SecurityCookieHandler)
    ], cookie_secret="61oETzKXQAGaYdkL5gEmGeJJFuYh7EQnp2XdTP1o/Vo=")
    return application


if __name__ == "__main__":
    init_application
    http_server = tornado.httpserver.HTTPServer(init_application())
    # listening at 8888 port
    http_server.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
