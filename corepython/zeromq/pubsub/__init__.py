# -*- coding:utf-8 -*-
'''
广播模式　　
1) 服务器端一直不断的广播中，如果中途有 Subscriber 端退出，并不影响他继续的广播，当 Subscriber 再连接上来的时候，收到的就是后来发送的新的信息了。这对比较晚加入的，或者是中途离开的订阅者，必然会丢失掉一部分信息，这是这个模式的一个问题，所谓的 Slow joiner。稍后，会解决这个问题。
2) 但是，如果 Publisher 中途离开，所有的 Subscriber 会 hold 住，等待 Publisher 再上线的时候，会继续接受信息。'''
__author__ = 'eric.sun'
