# -*- coding:utf-8 -**
import sys
sys.path.append("/usr/lib/python2.7/xml/parsers/")
from jinja2 import Template,Environment,PackageLoader

__author__ = 'eric.sun'


"""jinja最简单的例子"""
template=Template('Hello {{ name }}')
print template.render(name='Eric')

"""加载文件，并对文件进行渲染."""
"""通过命令行执行"""
env=Environment(loader=PackageLoader('testapp','template'))
template=env.get_template('first.html')
print template.render(name='Eric',age='20')


