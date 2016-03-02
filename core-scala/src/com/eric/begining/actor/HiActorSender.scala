package com.eric.begining.actor

/**
 * Created by Eric on 2016/1/28.
 */
object HiActorSender extends App {
  val receiver = new HiActorReceiver();
  receiver.start()
  receiver ! "work";
  receiver ! "hi";
  println("消息发送成功！")

}
