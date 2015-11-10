package com.eric.spark.streaming.window

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Window类型的程序主要用于统计最近某段时间内的数据统计，例如 最近一小时的链接查询排名等
 * 该程序为一个以窗口方式处理网络streaming的处理程序，程序会从RandomNameServer.scala提供的socket程序中读取名字信息，并将名字信息进行统计，并和之前的数据做汇总
 * 执行方式：./spark-submit --master=spark://cloud25:7077 --class com.eric.spark.streaming.window.WindowWorkWordCount --executor-memory=2g /opt/cloud/spark-1.4.1-bin-hadoop2.6/lib/spark_scala.jar 172.26.5.25 1233 >log1.txt
 * 通过观察log1.txt观察输出
 * Created by Eric on 2015/11/9.
 */
object WindowWorkWordCount {
  def main(args: Array[String]) {
    val dStreamPeriod=2;
    val sparkConf=new SparkConf().setAppName("WindowWordCount")
    val sparkStreaming=new StreamingContext(sparkConf,Seconds(dStreamPeriod))
    sparkStreaming.checkpoint("/tmp/spark/")
    val names=sparkStreaming.socketTextStream(args(0),args(1).toInt,StorageLevel.MEMORY_AND_DISK_SER)
    val namesMap=names.map(x=>(x,1))
    //必须是dStreamPeriod的整数倍
    val slideDuration=5*dStreamPeriod
    //必须是dStreamPeriod的整数倍
    val windowPeriod=1*dStreamPeriod

    //设置window的属性:表示每两秒钟处理最近10秒钟的数据一次
    val windowStream=namesMap.reduceByKeyAndWindow((a:Int,b:Int) => (a + b),Seconds(slideDuration),Seconds(windowPeriod))
    windowStream.print()
    sparkStreaming.start()
    sparkStreaming.awaitTermination()
  }
}
