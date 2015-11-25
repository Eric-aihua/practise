package com.eric.begining.expression

/**
 * Created by Eric on 2015/11/25.
 */
object InputAndOutPutSample extends App{
  //C风格的输出
  val name="eric"
  val age=10
  printf("Name is %s Age is %s\n",name,age)
  //通过read*类型的函数对数据进行输入
  val inputName=readLine("Please input your name:")
  print("Please input your age:")
  val inputAge=readInt()
  printf("InputName is %s InputAge is %s",inputName,inputAge)


}
