#encoding=utf-8
import tornado

__author__ = 'aihua.sun'


class TemplateDemoHandler(tornado.web.RequestHandler):
    def get(self):
        items = []
        #生成1000个元素放到items中,交给前台渲染
        for i in [1000]:
            items.append("item" + str(i))
        self.render("template.html", title="My title", items=items)
