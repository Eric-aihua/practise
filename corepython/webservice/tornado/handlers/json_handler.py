import json
import tornado

__author__ = 'Eric'

class JsonHandler(tornado.web.RequestHandler):
    def get(self):
        test_obj = TestObject("Eric","18")
        obj_str=json.dumps(test_obj,default=convert_to_builtin_type)
        json_value=tornado.escape.json_encode(obj_str)
        self.write(json_value)


def convert_to_builtin_type(obj):
    print( 'default(', repr(obj), ')') # 把MyObj对象转换成dict类型的对象
    d = {  }
    d.update(obj.__dict__)
    return d

class TestObject(object):
    def __init__(self,name,age):
        self.name=name
        self.age=age

    def __repr__(self):
        return '<TestObject(%s,%s)>' % self.name % self.age

    def get_name(self):
        return self.name
