#encoding=utf-8
import tornado

__author__ = 'aihua.sun'

class SumHandler(tornado.web.RequestHandler):
    # 用来处理加法请求，请求路径为http://localhost:8888/sum?num1=5&num2=6
    def get(self):
        num1 = self.request.arguments["num1"]
        num2 = self.request.arguments["num2"]
        sum_result = int(num1[0]) + int(num2[0])
        self.write("Sum result is:" + str(sum_result))