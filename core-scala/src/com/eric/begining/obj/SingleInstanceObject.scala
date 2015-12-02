package com.eric.begining.obj


/**
 * Created by Eric on 2015/12/2.
 */

object SingleInstanceObject extends App{
  println(SingleInstance.sum(3,4))
  for (i <- 1 to 10){
    SingleInstance.getSeqenceNumber()
  }
  println("The Latest SeqNum is:"+SingleInstance.getSeqenceNumber())
}

//使用object声明的对象达到静态方法或是静态字段的目的
object SingleInstance {
  //提供静态变量
  private var SEQ_ID=0
  def getSeqenceNumber():Int={SEQ_ID+=1;SEQ_ID}

  //提供静态方法调用
  def sum(num1:Int,num2:Int):Int={
    num1+num2
  }


}
