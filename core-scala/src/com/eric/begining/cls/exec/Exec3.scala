package com.eric.begining.cls.exec

/**
 * Created by Eric on 2015/12/1.
 */
object Exec3 extends App {
  val time1=new Time(23,20)
  val time2=new Time(10,20)
  val time3=new Time(23,40)
  println(time1.before(time2))
  println(time1.before(time3))

}

class Time {
  private var hours: Int = 0
  private var minutes: Int = 0

  def this(hours: Int, minutes: Int) {
    this()
    if (hours >= 24 && hours<0) print("Hours must be less than 24 and greater than 0") else this.hours = hours
    if (minutes >= 60 && hours<0) print("minutes must be less than 60 and greater than 0") else this.minutes = minutes
  }

  def before(other:Time):Boolean={
    if (other.hours!=this.hours){
      other.hours<this.hours
    }else{
      other.minutes<this.minutes
    }
  }

}
