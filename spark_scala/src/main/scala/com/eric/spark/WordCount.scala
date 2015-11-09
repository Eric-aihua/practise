package com.eric.spark

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

object WordCount{
  def main(args: Array[String]) {
    if (args.length == 0) {
      System.err.println("Usage: WordCount1 <file1>")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("WordCount1")
    val sc = new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).take(10).foreach(println)
    sc.stop()
  }
}


