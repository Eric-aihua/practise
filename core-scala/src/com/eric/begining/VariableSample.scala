package com.eric.begining

/**
 * 主要演示声明与变量的特性
 * Created by Eric on 2015/11/17.
 */
object VariableSample {

  def main(args: Array[String]) {
    //valAndVar();
    operator()
  }


//操作符的演示
  def operator(): Unit ={
    //操作符实际上是方法的调用
    val result1=10+5
    val result2=10.+(5)
    val result3= 1 to 10
    println(result1)
    println(result2)
    println(result3)


  }

  //主要演示val与var的区别
  def valAndVar(): Unit ={
    //在声明常量或是变量时，可以明确的知名类型，或是忽略类型
    val constantValue:String="scala"
    val constantValue2="scala"
    var variableValue="scala"

    //var 类型的变量可以更改值，但是val类似于java中的常量，值不可以改
    variableValue="i love "+variableValue
    //constantValue="i love"+constantValue;

    //将多个值放在一起定义
    val x,y=10
  }


}
