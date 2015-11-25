package com.eric.begining.expression

/**
 * for 循环的高级用法
 * Created by Eric on 2015/11/25.
 */
object AdvanceForExpression extends App{
  //一个for表达式中包含多个变量，效果类似于3层for循环
  for(i <- 1 to 3;j <- 1 to 3;k <- 1 to 3) print(i*j*k+",")
  println
  //循环中的每个变量都可以添加一个状态判断的过滤处理
  for(i <- 1 to 6 if i%2==0;k <- 1 to 6 if k%2!=0) printf("%d * %d= %d ",i,k,i*k)
  println
  //通过yield的方式生成另外一个数组
  val newArray=for(i <- 1 to 5) yield i *2
  println(newArray)
}
