package com.eric.begining.actor

import scala.actors.Actor

/**
 * Created by Eric on 2016/1/28.
 */
class HiActorReceiver extends Actor{
  override def act(): Unit = {
    val threadName=Thread.currentThread().getId;
    println("线程"+threadName+" 启动成功，等待请求！")
    while(true){
      receive{
        case "hi" => Thread.sleep(3000);println(threadName+" hello world");
        case _ => println(threadName+" 其他消息")
      }
    }
  }
}

