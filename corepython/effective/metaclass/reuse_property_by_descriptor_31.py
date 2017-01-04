# encoding:utf-8
from weakref import WeakKeyDictionary

__author__ = 'eric.sun'

"""通过描述符复用@property"""


class ExamByProperty(object):
    def __init__(self):
        """
        Exam中包含了多个grade,此时每添加一个新的科目，就需要增加@property 以及@XXX.setter方法，
        但是每添加一个科目的编码过程都是重复的

        """
        self._write_grade = 0
        self._math_grade = 0

    def _check_grade(self, value):
        if value < 0 and value > 100:
            raise ValueError('Grade value is invalid!')

    @property
    def write_grade(self):
        return self._write_grade

    @property
    def math_grade(self):
        return self._math_grade

    @write_grade.setter
    def write_grade(self, value):
        self._check_grade(value)
        self._write_grade = value

    @math_grade.setter
    def math_grade(self, value):
        self._check_grade(value)
        self._math_grade = value

# 描述符类：提供了一种方法将property的逻辑隔离到单独的类中来处理
# 所有ExamByDescriptor的实例都共享相同的Grade
class Grade(object):
    def __init__(self):
        #为了避免内存泄露，使用WeakKeyDictionary
        self._values=WeakKeyDictionary()

    def __set__(self, instance, value):
        print instance
        if value < 0 and value > 100:
            raise ValueError('Grade value is invalid!')
        self._values[instance]=value
    def __get__(self, instance, owner):
        print self,instance,owner
        if instance is None:
            return self
        return self._values.get(instance,0)

#ExamByDescriptor 必须式可Hash的
class ExamByDescriptor(object):
    # 1，只需要添加类属性就可以实现增加科目成绩的目的
    # 2，属性必须要定义为类级别
    math_grade=Grade()
    English_grade=Grade()
    Writing_grade=Grade()
    phy_grade=Grade()


def init_by_descriptor():
    exam = ExamByDescriptor()
    exam.math_grade = 80
    exam.English_grade = 99
    exam.Phy_grade = 96
    print exam.math_grade
    print exam.English_grade
    print exam.Phy_grade

    exam2 = ExamByDescriptor()
    exam2.math_grade = 44
    exam2.English_grade = 66
    exam2.Phy_grade = 77
    print exam2.math_grade
    print exam2.English_grade
    print exam2.Phy_grade

def init_by_property():
    exam = ExamByProperty()
    exam.math_grade = 80
    exam.write_grade = 99
    print exam.math_grade
    print exam.write_grade


if __name__ == '__main__':
    # init_by_property()
    init_by_descriptor()