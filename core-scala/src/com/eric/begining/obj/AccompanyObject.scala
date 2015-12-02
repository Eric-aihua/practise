package com.eric.begining.obj

/**
 * 主要用来演示伴生对象
 * Created by Eric on 2015/12/2.
 */
object AccompanyObject extends App {
  val bankAccount = new Account(1.5)
  bankAccount.deposit(100.3)
  bankAccount.withdraw(22.5)
  bankAccount.deposit(12.5)
  println(bankAccount.account)
  println(bankAccount.accNo)

  val bankAccount2 = new Account(1.5)
  val bankAccount3 = new Account(1.5)
  val bankAccount4 = new Account(1.5)
  //由于AccountNumber是从静态变量中获取的，所以bankAccount5的accNo应该是5
  val bankAccount5 = new Account(1.5)
  bankAccount5.deposit(10.3)
  bankAccount5.withdraw(2.5)
  bankAccount5.deposit(12.5)
  println(bankAccount5.account)
  println(bankAccount5.accNo)

}

class Account(bal:Double) {
  private var balance: Double = 0
  private val acc = Account.getAccountNumber()

//  def this(){
//
//  }

  def deposit(input: Double): Unit = {
    if (input >= 0) {
      balance += input
    } else {
      println("deposit number must greater than 0")
    }
  }

  def withdraw(input: Double): Unit = {
    if (input >= 0) {
      balance -= input
    } else {
      println("withdraw number must greater than 0")
    }
  }

  def account = balance

  def accNo = acc


}

//1，与类有相同的名称  2，与对应类可以互相访问private字段，前提是他们必须在同一个文件中
object Account {
  private var seq = 0

  private def getAccountNumber(): Int = {
    seq += 1; seq
  }
}
