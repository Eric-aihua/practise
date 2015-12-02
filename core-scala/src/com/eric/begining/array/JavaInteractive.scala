package com.eric.begining.array

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.bufferAsJavaList

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer

/**
 * Created by Eric on 2015/11/30.
 */
object JavaInteractive extends App{
  //将ArrayBuffer转换成ArrayList
  val commands=ArrayBuffer("ls","mkdir");
  val pb=new ProcessBuilder(commands)

  //将List转换成ArrayBuffer
  val pbCommands:Buffer[String]=pb.command()
}
