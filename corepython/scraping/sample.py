__author__ = 'Eric'
import requests

def get_message_from_baidu():
    response = requests.get('http://www.baidu.com')

    print(response.content);



get_message_from_baidu()