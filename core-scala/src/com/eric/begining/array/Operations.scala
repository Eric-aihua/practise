package com.eric.begining.array

/**
 * Created by Eric on 2015/11/30.
 */
object Operations extends App{
  val intArray=scala.collection.mutable.ArrayBuffer[Int](9,2,3,1,5,6)
  println(intArray.sum)
  println(intArray.max)
  println(intArray.sorted)
}
