#encoding=utf-8
import logging
import tornado

__author__ = 'aihua.sun'

log = logging.getLogger('override_method_handler')


class ProfileHandler(tornado.web.RequestHandler):
    # 覆盖父类的initialize方法
    def initialize(self, name):
        self.name = name;
        log.debug("init parameter is:" + self.name)


    def prepare(self):
        log.debug("execute prepare method")

    def get(self):
        param = self.request.arguments["param"]
        log.debug("get method param is:" + str(param))
        self.write("init name:"+self.name+" user param:"+str(param))


