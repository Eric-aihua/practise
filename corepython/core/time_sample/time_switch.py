# -*- coding:utf-8 -**
__author__ = 'sunaihua'

# coding=utf-8

import datetime
from datetime import timedelta

now = datetime.datetime.now()

# 今天
today = now

# 昨天
yesterday = now - timedelta(days=1)

# 明天
tomorrow = now + timedelta(days=1)   # 当前季度
now_quarter = now.month / 3 if now.month % 3 == 0 else now.month / 3 + 1
# 本周第一天和最后一天
this_week_start = now - timedelta(days=now.weekday())
this_week_end = now + timedelta(days=6 - now.weekday())

# 上周第一天和最后一天
last_week_start = now - timedelta(days=now.weekday() + 7)
last_week_end = now - timedelta(days=now.weekday() + 1)

# 本月第一天和最后一天
this_month_start = datetime.datetime(now.year, now.month, 1)
this_month_end = datetime.datetime(now.year, now.month + 1, 1) - timedelta(days=1)

# 上月第一天和最后一天
last_month_end = this_month_start - timedelta(days=1)
last_month_start = datetime.datetime(last_month_end.year, last_month_end.month, 1)

# 本季第一天和最后一天
month = (now.month - 1) - (now.month - 1) % 3 + 1
this_quarter_start = datetime.datetime(now.year, month, 1)
this_quarter_end = datetime.datetime(now.year, month + 3, 1) - timedelta(days=1)

# 上季第一天和最后一天
last_quarter_end = this_quarter_start - timedelta(days=1)
last_quarter_start = datetime.datetime(last_quarter_end.year, last_quarter_end.month - 2, 1)

# 本年第一天和最后一天
this_year_start = datetime.datetime(now.year, 1, 1)
this_year_end = datetime.datetime(now.year + 1, 1, 1) - timedelta(days=1)

# 去年第一天和最后一天
last_year_end = this_year_start - timedelta(days=1)
last_year_start = datetime.datetime(last_year_end.year, 1, 1)