#encoding=utf-8
import tornado

__author__ = 'Eric'

COOKIE_KEY = 'user'


class BaseHandler(tornado.web.RequestHandler):
    def get_current_user(self):
        #获取当前用户
        return self.get_secure_cookie(COOKIE_KEY)


class HomeHandler(BaseHandler):
    def get(self):
        #Cookie中没有用户信息就跳转到login
        if not self.get_current_user():
            self.redirect("/login")
        else:
            self.write("Welcome " + str(self.get_current_user()) + " to MyWeb!")


class LoginHandler(BaseHandler):
    def get(self):
        self.render("login.html",title="LOGIN")

    def post(self):
        #点击按钮后就重定向到/，并更具用户的信息打印出欢迎消息
        self.set_secure_cookie(COOKIE_KEY, self.get_argument("name"))
        self.redirect("/")


