# encoding:utf-8
from decimal import *

'''在对浮点数精度要求较高的时候，使用decimal库'''


def process_float_by_decimal():
    # rate:每分钟费用(分钟 )
    # seconds:通话时长(秒数)
    # cost:最终产生的费用
    rate=0.05
    seconds=5
    cost=rate*seconds/60
    # 费用少于１分钱，可能会被四舍五入省略掉
    print cost
    
    # 使用decimal的库进行计算
    rate=Decimal('0.05')
    seconds=Decimal('5')
    cost= rate*seconds/60
    # 通过decimal库对精度以及四舍五入的方式进行控制,下面的参数表示保留2位精度，四舍五入的方式采取向上取值
    print cost.quantize(Decimal('0.01'),rounding=ROUND_UP)

if __name__ =='__main__':
    process_float_by_decimal() 

