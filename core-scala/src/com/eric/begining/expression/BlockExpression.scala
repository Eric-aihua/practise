package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/25.
 */
object BlockExpression extends App{
  //{}块中包含一系列的表达式，最后一个表达式就是块的值，如果块的最后一个表达式是赋值类型的，则该块的返回值是Unit
  val blockNotNonResult={val a=3;val b=4;a+b}
  println(blockNotNonResult)
  val blockNonResult={val a=3;val b=4;val c=a+b}
  println(blockNonResult)



}
