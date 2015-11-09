package com.eric.spark.streaming.stateless

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 该种操作数据“无状态”的streaming
 * 主要通过Spark Streaming监听某个本地的文件夹，并且实时的统计出该文件夹下新增文件的单词数
 *
 * Created by Eric on 2015/11/9.
 */
object HDFSFileWordCountMonitor {
  def main(args: Array[String]) {
    print("please input monitor file dir:")
    val sc=new SparkConf().setAppName("Monitor Local Directory");
    //每隔10秒轮询一次
    val sparkStreamingContext=new StreamingContext(sc,Seconds(10));
    //输入的路劲为HSDF的路劲
    val lines=sparkStreamingContext.textFileStream(args(0))
    val words=lines.flatMap(_.split(" "))
    val wordsCount=words.map(x=>(x,1)).reduceByKey(_ + _);
    wordsCount.print()
    sparkStreamingContext.start()
    sparkStreamingContext.awaitTermination()
  }
}
