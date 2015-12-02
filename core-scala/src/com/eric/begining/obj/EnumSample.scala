package com.eric.begining.obj

/**
 * Created by Eric on 2015/12/2.
 */
object EnumSample extends App{
  TraficLightRule.process(TrafficLightColor.RED)
  TraficLightRule.process(TrafficLightColor.GREEN)
  TraficLightRule.process(TrafficLightColor.YELLOW)

}


object TraficLightRule{
  def process(trafficLightColor: TrafficLightColor.Value): Unit ={
    if(trafficLightColor==TrafficLightColor.RED){
      println(trafficLightColor.toString+" Light,you must be stop")
    }
    if(trafficLightColor==TrafficLightColor.YELLOW){
      println(trafficLightColor.toString+" Light,you must be slowly")
    }
    if(trafficLightColor==TrafficLightColor.GREEN){
      println(trafficLightColor.toString+" Light,you can pass")
    }
  }
}
//定义一个枚举类
object TrafficLightColor extends Enumeration{
  val RED,GREEN,YELLOW=Value
}
