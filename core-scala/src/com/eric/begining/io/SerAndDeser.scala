package com.eric.begining.io

/**
 * 序列化以及反序列化操作
 * Created by Eric on 2016/2/18.
 */


//可序列化的类
@SerialVersionUID(123) class PersonSerializable(name: String) extends Serializable {
  def printName(): Unit = {
    print("PersonSerializable Name is:" + name)
  }
}

//不可序列化的类
class PersonNonSerializable(name: String) {
  def printName(): Unit = {
    println("PersonNonSerializable Name is:" + name)
  }
}

object SerAndDeser extends App {
  val serPerson = new PersonSerializable("Eric");
  val nonSerPerson = new PersonNonSerializable("Simon");

  import java.io._;
  val ops = new ObjectOutputStream(new FileOutputStream("./object1"));
  ops.writeObject(serPerson);
  ops.close()

  val ois = new ObjectInputStream(new FileInputStream("./object1"));
  val fileSerPerson = ois.readObject().asInstanceOf[PersonSerializable]
  ois.close()
  fileSerPerson.printName()

  //对于没有实现序列化接口的对象，不能进行相关操作
  try {
    val ops2 = new ObjectOutputStream(new FileOutputStream("./object2"));
    ops2.writeObject(nonSerPerson);
    ops2.close()

    val ois2 = new ObjectInputStream(new FileInputStream("./object2"));
    val fileNonSerPerson = ois2.readObject().asInstanceOf[PersonNonSerializable]
    ois2.close()
    fileNonSerPerson.printName()
  }
  catch {
    case _: Exception => print("没有实现序列化接口的对象，不能进行序列化操作！")
  }


}
