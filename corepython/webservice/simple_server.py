# encoding=utf-8

__author__ = 'aihua.sun'
import tornado.httpserver
import tornado.ioloop
import tornado.web
from webservice.handlers.override_method_handler import ProfileHandler
from webservice.handlers.sum_handler import SumHandler


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
        (r"/profile", ProfileHandler, dict(name="eric"))
    ])
    return application


if __name__ == "__main__":
    init_application
    http_server = tornado.httpserver.HTTPServer(init_application())
    # listening at 8888 port
    http_server.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
