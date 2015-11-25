package com.eric.begining.expression

import scala.util.control.Breaks

/**
 * Created by Eric on 2015/11/25.
 */
object LoopSample extends App{
  //通过for(i <- 表达式) 的方式遍历表达式的结果的元素
  var sum=0
  for(i <- 1 to 10)
    sum+=i
//  println(sum)

 //until 表示是否包含最后一个元素
  var sum2=0
  for(i <- 1 until 10)
    sum2+=i
//  println(sum2)

  //使用map的方法对结果进行处理
  var sum3=0
  (1 to 10).map(sum3+=_)
//  println(sum3)

  //遍历字符串的字符
  val str="ab"
  var charSum=0
  for(ch <-str) charSum +=ch
//  println(charSum)


  //使用Breaks.break从循环中跳出,Breaks的底层是利用抛出异常的方式跳出循环，一般不建议使用
  for(i <- 1 to 10){
    if(i==5) Breaks.break else println(i)
  }
  println("跳出循环")



}
