# encoding:utf-8
""" 使用mechanize 模拟表单的交互,mechanize 封装了cookie的操作细节，而且访问表单输入也比较简单,例如数据的更新操作等"""

EMAIL = 'test@126.com'
PWD = '111111'
LOGIN_URL = 'http://127.0.0.1:8000/places/default/user/login'
COUNTRY_URL = 'http://127.0.0.1:8000/places/default/edit/Albania-3'
import mechanize


def update_population_count():
    browser = mechanize.Browser()
    browser.open(LOGIN_URL)
    # 选择form
    browser.select_form(nr=0)
    browser['email'] = EMAIL
    browser['password'] = PWD
    # 设置登陆参数，并且提交按钮，类似于登陆
    browser.submit()
    browser.open(COUNTRY_URL)
    browser.select_form(nr=0)
    before_update = int(browser['population'])
    after_update = before_update + 1
    browser['population'] = str(after_update)
    browser.submit()
    print 'update before:%s after:%s'%(before_update,after_update)


update_population_count()
