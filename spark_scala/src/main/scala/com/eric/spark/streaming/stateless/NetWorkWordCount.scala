package com.eric.spark.streaming.stateless

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 *该程序为一个无状态的网络streaming处理程序，程序会从RandomNameServer.scala提供的socket程序中读取名字信息，并将名字信息进行统计
 * 执行方式：./spark-submit --master=spark://cloud25:7077 --class com.eric.spark.streaming.stateless.NetWorkWordCount --executor-memory=2g /opt/cloud/spark-1.4.1-bin-hadoop2.6/lib/spark_scala.jar 172.26.5.25 1233 >log1.txt
 * 通过观察log1.txt观察输出
 * Created by Eric on 2015/11/9.
 */
object NetWorkWordCount {
  def main(args: Array[String]) {
    val sparkConf=new SparkConf().setAppName("NetWorkNameCount")
    val sparkStreaming=new StreamingContext(sparkConf,Seconds(5))
    val names=sparkStreaming.socketTextStream(args(0),args(1).toInt,StorageLevel.MEMORY_AND_DISK_SER)
    val result=names.map(x=>(x,1)).reduceByKey(_+_)
    result.print()
    sparkStreaming.start()
    sparkStreaming.awaitTermination()
  }
}
