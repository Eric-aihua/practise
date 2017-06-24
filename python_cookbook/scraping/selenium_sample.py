# encoding:utf-8
"""如果想要使用关键词从百度搜索东西，需要使用动态网页的抓取技术，抓取动态网页的数据有两种方式 1：通过分析网站的源码，拼装请求  2，使用浏览器渲染引擎，来模拟用户行为，触发对应的网页操作
对于第二种方式，代码比较简单，但是效率上比第一种要低，可以作为短期方案，如果打算长期对某个网页进行动态数据爬去，可以尝试使用第一种方式
，本例使用selenium来抓取动态网页的数据"""

from selenium import webdriver
import os

def get_dynamic_data():
    # 运行前需要安装geckodriver:https://github.com/mozilla/geckodriver/releases,解压后，放到系统的PATH中
    firefoxBin = os.path.abspath(r"/usr/bin/firefox")
    os.environ["webdriver.firefox.bin"] = firefoxBin
    driver = webdriver.Firefox()
    driver.get("http://127.0.0.1:8000/places/default/search")

    #通过id找到查询输入框，并设置该框的值为'.'
    driver.find_element_by_id('search_term').send_keys('.')
    js='document.getElementById("page_size").options[1].text="1000"'
    #通过执行js改变返回数据的数量
    driver.execute_script(js)
    #模拟点击按钮的操作
    driver.find_element_by_id('search').click()
    #对于search的等待时间
    driver.implicitly_wait(30)
    #通过css选择器选择查询结果
    links=driver.find_elements_by_css_selector("#results a")
    countries=[link.text for link in links]
    print countries
    driver.close()

get_dynamic_data()
