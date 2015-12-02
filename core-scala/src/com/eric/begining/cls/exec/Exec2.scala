package com.eric.begining.cls.exec

/**
 * 编写一个BankAccount的账号，有deposit以及withdraw方法，balance是只读的
 * Created by Eric on 2015/12/1.
 */
object Exec2 extends App {
  val bankAccount = new BankAccount()
  bankAccount.deposit(100.3)
  bankAccount.withdraw(22.5)
  bankAccount.deposit(12.5)
  print(bankAccount.account)
}

class BankAccount {
  private var balance:Double = 0

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
  def account=balance
}


