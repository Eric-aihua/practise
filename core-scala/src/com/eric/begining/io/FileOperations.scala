package com.eric.begining.io

import scala.io.Source

/**
 * this class mainly used to demonstrate file operations
 * Created by Eric on 2016/1/27.
 */
object FileOperations {
  val fileName = "D:\\work\\svn\\product_new\\04代码区\\02源代码v1.1\\TrueCloud-Rest-Service\\pom.xml"

  def main(args: Array[String]) {
    makeFileAsString()
  }

  //读取文件的行
  def readLines(): Unit = {
    val source = Source.fromFile(fileName, "UTF-8");
    val fileLineIterator = source.getLines()
    println("FILE NAME:" + fileName)
    var lineNumber = 1
    for (line <- fileLineIterator) {
      print(lineNumber + ":" + line + "\n")
      lineNumber += 1;
    }
    //关闭source
    source.close()

  }

  //将真个文件转化为字符串
  def makeFileAsString(): Unit = {
    val source = Source.fromFile(fileName, "UTF-8");
    val fileString = source.mkString
    println(fileString.getClass)
    println(fileString)
    source.close()
  }
}


