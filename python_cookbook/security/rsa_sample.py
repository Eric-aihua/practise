# encoding:utf-8

__author__ = 'eric.sun'

import rsa

# 先生成一对密钥，然后保存.pem格式文件，当然也可以直接使用
(pubkey, privkey) = rsa.newkeys(1024)

public_file='public.pem'
private_file='private.pem'

pub = pubkey.save_pkcs1()
pubfile = open(public_file,'w+')
pubfile.write(pub)
pubfile.close()

pri = privkey.save_pkcs1()
prifile = open(private_file,'w+')
prifile.write(pri)
prifile.close()

# load公钥和密钥
message = 'lovesoo.org'
with open(public_file) as publickfile:
    p = publickfile.read()
    pubkey = rsa.PublicKey.load_pkcs1(p)

with open(private_file) as privatefile:
    p = privatefile.read()
    privkey = rsa.PrivateKey.load_pkcs1(p)

# 用公钥加密、再用私钥解密
crypto = rsa.encrypt(message, pubkey)
message = rsa.decrypt(crypto, privkey)
print 'Encrypted Message:%s' %crypto
print 'Decrypted Message:%s' %message

# sign 用私钥签名认证、再用公钥验证签名
signature = rsa.sign(message, privkey, 'SHA-1')
verify_result=rsa.verify('lovesoo.org', signature, pubkey)

print 'Signature Message:%s' %signature
print 'Unsignature Message:%s' %verify_result