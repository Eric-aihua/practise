package com.eric.begining.exec


import scala.math._
import scala.math.BigInt._
import scala.util.Random

/**
 * Created by Eric on 2015/11/18.
 */
object Exec {
  def main(args: Array[String]) {
    //print(generatePowerNumber())
    //getFirstAndLastChar
    generateRandomPrimeNumber
  }

  def generatePowerNumber():Int={
      pow(2,10).toInt
  }

  //生成随机素数
  def generateRandomPrimeNumber():Unit={
    print(probablePrime(100, Random))
  }

  //获取字符串的第一个以及最后一个字符串
  def getFirstAndLastChar():Unit={
    val str="I Come From china"
    //方法一：通过坐标
    println(str(0))
    println(str(str.length-1))
    //方法二：通过charAt方法
    println(str.charAt(0))
    println(str.charAt(str.length-1))
  }

  //字符串的take,drop,takeRight,dropRight的演示
  def strDemonstrate(): Unit ={
    
  }



}
