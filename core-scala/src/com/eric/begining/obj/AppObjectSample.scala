package com.eric.begining.obj

/**
 * 演示App的特性
 * 如果加上scala.time参数则会打印出执行时间
 * Created by Eric on 2015/12/2.
 */
object AppObjectSample extends App{
  //包含args
  if(args.length==0) print("hello world") else print("hello"+args(0))


}
