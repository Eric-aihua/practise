package com.eric.begining.cls.exec



/**
 *
 * Created by Eric on 2015/12/1.
 */
object Exec1 extends App{
  val counter=new Counter
  for(i <- 1 until Int.MaxValue)
    counter.increment()
  println(counter.current)
}


class Counter {
  private var number=0
  def increment(): Unit ={
    number+=1
  }
  def current=number
}


