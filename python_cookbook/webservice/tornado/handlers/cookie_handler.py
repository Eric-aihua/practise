#encoding=utf-8
import tornado

__author__ = 'Eric'

class NotSecurityCookieHandler(tornado.web.RequestHandler):
    def get(self):
        """
Cookie 很容易被恶意的客户端伪造。加入你想在 cookie 中保存当前登陆用户的 id 之类的信息，
你需要对 cookie 作签名以防止伪造。Tornado 通过 set_secure_cookie 和 get_secure_cookie 方法直接支持了这种功能。
 要使用这些方法，你需要在创建应用时提供一个密钥，名字为 cookie_secret

        """
        cookie_key="cookie_key"
        if not self.get_cookie(cookie_key):
            self.set_cookie(cookie_key,"cookie_value")
            self.write("Cookie was Saved!")
        else:
            self.write("Your Cookie already be Saved");


class SecurityCookieHandler(tornado.web.RequestHandler):
    def get(self):
        cookie_key="cookie_key"
        if not self.get_secure_cookie(cookie_key):
            self.set_secure_cookie(cookie_key,"cookie_value")
            self.write("Cookie was Saved!")
        else:
            self.write("Your Cookie already be Saved");

