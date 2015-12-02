package com.eric.begining.map

import java.awt.Font
import java.awt.font.TextAttribute._
import java.util

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.JavaConversions.mapAsJavaMap

/**
 * Created by Eric on 2015/12/1.
 */
object MapOperations extends App{
  //遍历Map
  val map2=scala.collection.mutable.Map("Eric"->10,"Alex"->30)
  for((name,score)<-map2)
    print(name,score)

  //与Java的相互转换
  val scalaMap =new util.HashMap[String,Int]()
  val fontAttr=Map(FAMILY->"Serif")
  val font=new Font(fontAttr)


}
