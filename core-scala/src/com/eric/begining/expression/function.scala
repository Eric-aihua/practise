package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/25.
 */
object function extends App{
  //对于比较简单的函数，可以直接用=来定义函数的内容
  def printArray(count:Int)=for(i <- 1 to count) print(i+",")
  printArray(10)
  println

  //对于递归函数，必须要指定方法的返回类型
  def  fac(num:Int):Int=if(num==1) num else num*fac(num-1)
  println(fac(5))

  //默认参数
  def decorate(name:String,age:Int=15)="Name:"+name+" age:"+age
  println(decorate("Eric"))
  println(decorate("Eric",14))

  //变长参数
  def sum(nums:Int*): Unit ={
    var result=0
    for(num <- nums)
      result+=num
    println(result)

  }
  sum(1,2,3,4,5)
  sum(1,2,3)
}
