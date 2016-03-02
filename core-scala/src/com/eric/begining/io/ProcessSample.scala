package com.eric.begining.io

/**
 * 主要演示如何调用系统的命令
 * Created by Eric on 2016/2/18.
 */
object ProcessSample {
  def main(args:Array[String]): Unit ={
    printSysCommandResult()
  }

  //将系统调用的结果打印出来，以下程序可在Linux上正常运行
  def printSysCommandResult(): Unit ={
    import scala.sys.process._
    var result1="ls -la" !
    var result2="ls -la" !!
    //以#|代表管道的方式对结果进行传递
    val result3="ls -la " #| "grep bash"!!
    //对输出内容进行重定向
//    "echo $JAVA_HOME " #> new File("result4.txt")!!

    println(result1)
    println(result2)

  }

}
