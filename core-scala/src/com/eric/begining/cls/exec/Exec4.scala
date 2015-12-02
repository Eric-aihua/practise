package com.eric.begining.cls.exec

import scala.beans.BeanProperty

/**
 * 观察生成的class对象
 * Created by Eric on 2015/12/1.
 */


//private String name = "";
//private int age = 0;
//public String name()
//public void name_$eq(String x$1)
//public int age()
//public void age_$eq(int x$1)
class Student1 {
  var name: String = ""
  var age: Int = 0
}


//private String name = "";
//private int age = 0;
//public String name()
//public void name_$eq(String x$1)
//public void setName(String x$1)
//public String getName()

//public int age()
//public void age_$eq(int x$1)
//public void setAge(int x$1)
//public int getAge()
class Student2 {
  @BeanProperty var name: String = ""
  @BeanProperty var age: Int = 0
}

//private String name = "";
//private int age = 0;
//private String name()
//private void name_$eq(String x$1)
//private int age()
//private void age_$eq(int x$1)
//public void getDesc()
class Student3 {
  private var name: String = ""
  private var age: Int = 0
  def getDesc(): Unit ={
    "Name:"+name+" Age:"+age
  }
}

