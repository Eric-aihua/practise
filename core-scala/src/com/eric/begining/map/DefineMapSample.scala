package com.eric.begining.map

/**
 * Created by Eric on 2015/12/1.
 */
object DefineMapSample extends App{
  //定义一个值不能改变的map
  val map1=Map("Eric"->10,"Alex"->30)

  //定义一个值可变的Map
  val map2=scala.collection.mutable.Map("Eric"->10,"Alex"->30)

  //定义一个空的Map
  val map3=new scala.collection.mutable.HashMap[String,Int]

  //获取map2中Eric的分数
  val ericScore=map2("Eric")
  val simonScore=map2.getOrElse("simon",-1);

  //更改map中的值
  map2("Eric")=99
  println(map2("Eric"))

  


}
