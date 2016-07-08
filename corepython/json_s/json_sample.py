#--encoding:utf-8--
__author__ = 'eric.sun'

import json

map_data = {
'name' : 'ACME',
'shares' : 100,
'price' : 542.23
}

list_data=[{
'name' : 'ALEX',
'shares' : 101,
'price' : 500
},{
'name' : 'ACME',
'shares' : 100,
'price' : 542.23
}]


#转换成json字符串
json_map_format=json.dumps(map_data)
json_list_format=json.dumps(list_data)

#直接打印以单引号显示
print map_data
print list_data

#json格式的数据
print str(type(json_map_format))+":"+json_map_format;
print str(type(json_list_format))+":"+json_list_format;

#转换成python对象
print type(json.loads(json_list_format))
print type(json.loads(json_map_format))