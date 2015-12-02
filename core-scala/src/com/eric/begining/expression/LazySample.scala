package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/30.
 */
object LazySample extends App {
  //定义时读取文件
  val words = scala.io.Source.fromFile("\u202AD:\\tmp\\zookeeper\\version-2").mkString
  //使用时才读取文件
  lazy val words2 = scala.io.Source.fromFile("\u202AD:\\tmp\\zookeeper\\version-2").mkString

  //每次在使用时都读文件
  def words3 = scala.io.Source.fromFile("\u202AD:\\tmp\\zookeeper\\version-2").mkString
}
