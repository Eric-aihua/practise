# encoding:utf-8
__author__ = 'eric.sun'

"""本代码主要演示如何通过metaclass来验证class的属性以及方法"""

# 校验所有Polygon子类的sides类属性值必须大于三
class VerifyPolygon(type):
    def __new__(meta, name, bases, class_dict):
        print meta
        print name
        print bases
        print class_dict
        print '\n'
        if bases != (object,):
            if class_dict['sides'] < 3:
                raise AttributeError('Polygon sides must greate 3')
        return type.__new__(meta, name, bases, class_dict)


class Polygon(object):
    __metaclass__ = VerifyPolygon
    sides = 0


class Triangle(Polygon):
    sides = 3

    def __init__(self):
        print 'init Triangle'


class Line(Polygon):
    # 违反了metaclass中对sides的要求，会导致整个文件加载失败
    sides = 1

    def __init__(self):
        print 'init Line'

# 如果定义了__metaclass__,python会在所有代码执行之前，
# 将Polygon的子类信息传给VerifyPolygon.__new__方法，所以__main__中的第一句话也不会执行
if __name__ == '__main__':
    print 'execute main'
    t = Triangle()
    l = Line()
