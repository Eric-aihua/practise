# encoding: utf-8
import urllib2

import mechanize

__author__ = 'eric.sun'
import urllib
"""用于找回cet-4成绩，CET-4的准考证号规则如下
准考证号共15位.
　　A.前6位是地区号.(可以问与你同一城市报名的任何一人)
　　B.然后是062 (表示06年的第二次,即06年12月份的)
　　C.然后是1或2 (1代表四级,2代表6级)
　　D.然后的三位是你的考场号,多为0**或1** (自己应该有印象吧)
　　E.最后两位是你的座位号, 也应该能记住吧!
"""

# info_size = 'http://www.chsi.com.cn/cet/query?'
info_size = 'http://www.chsi.com.cn/cet'
school_code = '321640'
exam_years = ['082', '091']
exam_code = '1'
exam_rooms = range(1)
sit_nums = range(1)
student_name = '孙爱华'
DEFAULT_AGENT = 'Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0'


def guess_grade():
    for exam_year in exam_years:
        for exam_room in exam_rooms:
            final_exam_room='0'*(3-len(str(exam_room)))+str(exam_room)
            for sit_num in sit_nums:
                final_sit_num = '0' * (2-len(str(sit_num))) + str(sit_num)
                exam_num = '%s%s%s%s%s' % (school_code, exam_year, exam_code,final_exam_room, final_sit_num)
                request_params={'zkzh':exam_num,'xm':student_name}
                request_url='%s%s' %(info_size,urllib.urlencode(request_params))
                print '#####',request_url
                # headers = {'User-agent': DEFAULT_AGENT}
                # requests = urllib2.Request(request_url, headers=headers)
                # opener = urllib2.build_opener()
                # html = opener.open(requests).read()
                # print html

                browser = mechanize.Browser()
                browser.open(info_size)
                browser['zkzh'] = exam_num
                browser['xm'] = student_name
                browser.submit()
                print browser.viewing_html





if __name__ == '__main__':
    guess_grade()
