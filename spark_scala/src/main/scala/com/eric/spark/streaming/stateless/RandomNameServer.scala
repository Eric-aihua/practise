package com.eric.spark.streaming.stateless

import java.io.PrintWriter
import java.net.ServerSocket
import java.util.Random

/**
 * 该程序主要为服务器端，当客户端连接到该程序时，将定时随机的从名称池中返回数据给客户端
 * 执行方式：java -cp spark_scala.jar com.eric.spark.streaming.stateless.RandomNameServer 1233
 * Created by Eric on 2015/11/9.
 */
object RandomNameServer {
  def main(args: Array[String]) {
    //没隔generateDuration描述随机生成一个名字
    val generateDuration = 10
    if (args.length== 1) {
      val serverPort = args(0)
      val server = new ServerSocket(serverPort.toInt)
      while (true) {
        val socket = server.accept()
        new Thread() {
          print("Connected from client:" + socket.getInetAddress)
          val outPutWriter = new PrintWriter(socket.getOutputStream(), true)
          while (true) {
            Thread.sleep(generateDuration)
            val randomName = generateRandomName();
            val writeContext = randomName + "\n"
            print(writeContext)
            outPutWriter.write(writeContext)
            outPutWriter.flush()
          }
          socket.close()
        }
      }
    }
//    print(generateRandomName())
  }

  def generateRandomName() :String={
    val namePool:Array[String] = new Array[String](6);
    namePool(0)="Eric"
    namePool(1)="Gray"
    namePool(2)="Phip"
    namePool(3)="Slay"
    namePool(4)="Andy"
    namePool(5)="Simon"
    val randomIndex = new Random().nextInt(namePool.length)
    namePool(randomIndex)
  }
}
