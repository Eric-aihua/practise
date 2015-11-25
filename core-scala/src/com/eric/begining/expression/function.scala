package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/25.
 */
object function extends App{
  //对于比较简单的函数，可以直接用=来定义函数的内容
  def printArray(count:Int)=for(i <- 1 to count) print(i+",")
  printArray(10)
}
