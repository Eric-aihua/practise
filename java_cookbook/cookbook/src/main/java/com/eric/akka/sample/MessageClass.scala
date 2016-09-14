package com.eric.akka.sample

/**
 * Created by Eric on 2015/11/24.
 */
//初始化消息
case class InitSignal()

//请求消息
case class QuoteRequest()


//响应消息
case class QuoteReponse(quoteResponseString:String)
