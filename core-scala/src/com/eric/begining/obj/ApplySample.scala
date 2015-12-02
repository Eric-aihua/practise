package com.eric.begining.obj

/**
 * apply方法主要是为了在构造对象时，不需要使用new关键字，构造com.eric.begining.obj.Account对象必须要使用new关键字
 * Created by Eric on 2015/12/2.
 */
object ApplySample extends App {
  //构造没有使用apply的Account对象,必须要加上new 关键字
  //val noApplyAccount=Account(1.5)
  val noApplyAccount = new Account(1.5)
  println(noApplyAccount.accNo)

  //构造使用apply的Account对象，不用加上new关键字
  val applyAccount = ApplyAccount(100)
  println(applyAccount.balance)

}

class ApplyAccount(initAmount: Double) {
  var balance = initAmount
}

object ApplyAccount {
  def apply(initAmount: Double):ApplyAccount= {
    new ApplyAccount(initAmount)
  }
}
