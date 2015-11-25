package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/25.
 */
object IfSample extends App{
  val a=6
  val b=5

  //将if表达式的结果赋值给常量
  val ifResult=if (a>b) 1 else -1
  //println(ifResult)

  //混合类型的if表达式
  val mixResult=if(a>b) "greager" else -1
//  println(mixResult)

  //确实else的情况,用()代表返回值,下面的语句会输出()
  val missElseExpression=if(a<b) "greater"
  //  println(missElseExpression)

  //scala编译器会一行一行的识别代码，在命令行中，当else表达式和if表达式不在同一行时，if的表达式需要用{}表示范围，否为else部分的表达式就会被忽略
  //如果在命令行中下面的情况会输出（）
  if(a>a) 1
  //如果在命令行中下面的情况会输出正确的结果
  if(a>a) {
    1
  }else if(a==a) 0





}
