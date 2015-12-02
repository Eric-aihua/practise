package com.eric.begining.cls.exec

/**
 * Created by Eric on 2015/12/1.
 */
object Exec8 extends App {
  //使用最少参数，其他使用默认值
  val car1=new Car("BMW","MINI")
  println(car1.manufactor,car1.modelName,car1.modelYear,car1.number)

  //使用3个参数，number单独设置
  val car2=new Car("Benze","s600",2010)
  car2.number="A88888"
  println(car2.manufactor,car2.modelName,car2.modelYear,car2.number)
}

//scala版本的Car,制造商，型号名称为必填
//构造函数的参数为（制造商，型号名称，型号年份，车牌）
class Car(private var ins: String, private val typeName: String, private val typeYear: Int = -1, var number: String = "#") {
  def manufactor=ins
  def modelName=typeName
  def modelYear=typeYear
}
