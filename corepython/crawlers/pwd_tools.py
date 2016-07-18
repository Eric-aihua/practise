#coding:utf-8
__author__ = 'eric.sun'
from Crypto.Cipher import AES
from binascii import b2a_hex, a2b_hex
import base64
BS = AES.block_size
pad = lambda s: s + (BS - len(s) % BS) * chr(BS - len(s) % BS)
unpad = lambda s : s[0:-ord(s[-1])]

class prpcrypt():
    def __init__(self, key):
        self.key = key
        self.mode = AES.MODE_CBC

    #加密函数，如果text不是16的倍数【加密文本text必须为16的倍数！】，那就补足为16的倍数
    def encrypt(self, text):
        cryptor = AES.new(self.key)
        #这里密钥key 长度必须为16（AES-128）、24（AES-192）、或32（AES-256）Bytes 长度.目前AES-128足够用
        self.ciphertext = cryptor.encrypt(pad(text))
        #因为AES加密时候得到的字符串不一定是ascii字符集的，输出到终端或者保存时候可能存在问题
        #所以这里统一把加密后的字符串转化为16进制字符串
        return base64.b64encode(self.ciphertext)

    #解密后，去掉补足的空格用strip() 去掉
    def decrypt(self, text):
        cryptor = AES.new(self.key)
        plain_text = unpad(cryptor.decrypt(base64.b64decode(text)))
        return plain_text

if __name__ == '__main__':
	key='12aadbcde3123sd1'    
	pc = prpcrypt(key)      #初始化密钥
	e = pc.encrypt("pwd")
	d = pc.decrypt(e)
	print '-',e,'-',d,'-'

