# encoding:utf-8
"""在进行用户注册的时候，往往需要输入验证码，本代码演示如何自动识别验证码"""
import Image
import lxml.html
import os
import urllib2
from io import BytesIO

import mechanize
import pytesseract
import PIL
import random

REGISTER_URL = 'http://127.0.0.1:8000/places/default/user/register'
LOGIN_URL = 'http://127.0.0.1:8000/places/default/user/login'
COUNTRY_URL = 'http://127.0.0.1:8000/places/default/edit/Albania-3'


# pytesseract 的安装
# pip install pytesseract
# sudo apt-get install tesseract-ocr
def get_captchacode():
    opener = urllib2.build_opener()
    html = opener.open(REGISTER_URL).read()
    tree = lxml.html.fromstring(html)
    img_data = tree.cssselect('#recaptcha > img:nth-child(1)')[0].get('src')
    img_data = img_data.partition(',')[-1]
    binary_img_data = img_data.decode('base64')
    file_like = BytesIO(binary_img_data)
    img = Image.open(file_like)
    # 下面的步骤对图片进行处理，只有全黑的像素点才会被保留
    tmp_img = 'check_code.png'
    gray_image = 'check_gray_code.png'
    finally_image = 'finally_result.png'
    img.save(tmp_img)
    gray = img.convert('L')
    gray.save(gray_image)
    bw = gray.point(lambda x: 0 if x < 1 else 255, '1')
    bw.save(finally_image)
    finally_result = pytesseract.image_to_string(bw)

    return finally_result


def register(fname, lname, email, pwd, capchacode):
    browser = mechanize.Browser()
    browser.open(REGISTER_URL)
    browser.select_form(nr=0)
    browser['first_name'] = fname
    browser['last_name'] = lname
    browser['email'] = email
    browser['password'] = pwd
    browser['password_two'] = pwd
    browser['recaptcha_response_field'] = capchacode
    browser.submit()


def login(email, pwd):
    browser = mechanize.Browser()
    browser.open(LOGIN_URL)
    # 选择form
    browser.select_form(nr=0)
    browser['email'] = email
    browser['password'] = pwd
    # 设置登陆参数，并且提交按钮，类似于登陆
    browser.submit()
    browser.open(COUNTRY_URL)
    browser.select_form(nr=0)
    population_count = int(browser['population'])
    print '登陆后获取的人口信息:%s' % (population_count)


if __name__ == '__main__':
    variable = (''.join(map(lambda xx: (hex(ord(xx))[2:]), os.urandom(10))))[0:10]
    fname, lname, pwd = variable, variable, variable
    email = '%s@126.com' % (variable)
    captchacode = get_captchacode()
    # print email, pwd
    # register(fname, lname, email, pwd, captchacode)
    # login(email,pwd)
