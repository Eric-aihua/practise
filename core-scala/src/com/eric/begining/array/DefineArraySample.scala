package com.eric.begining.array

/**
 * Created by Eric on 2015/11/30.
 */
object DefineArraySample extends App{
  //定义长度为10的数组
  val nullArray=new Array[Int](10)
  //定义并初始化数组
  val initArray=Array("hello","world")
  initArray(0)="hi"


  //定义变长缓冲数组
  val varLenArray=scala.collection.mutable.ArrayBuffer[Int]()
  //添加一个元素
  varLenArray += 1
  //添加一组元素
  varLenArray += (1,2,3,45)
  varLenArray ++= Array(6,7,8)
  //移除最后两个元素
  varLenArray.trimEnd(2)
  //在制定位置之前插入元素
  varLenArray.insert(5,111)
  varLenArray.remove(2)

  //将数组转化为缓冲数组
  val intArray=Array(1,2,3,4,5,6)

  //数组转换
  val intArray2=for(element<-intArray)yield element*2
  println(intArray2.toBuffer)



}
