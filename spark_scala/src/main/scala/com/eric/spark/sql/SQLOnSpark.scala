package com.eric.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Eric on 2015/11/7.
 */
case class Person(name: String, age: Int)

object SQLOnSpark {
  def main(args: Array[String]) {
    val sparkConf=new SparkConf().setAppName("SparkSQLSample");
    val sparkContext=new SparkContext(sparkConf)
    val sqlContext=new SQLContext(sparkContext);
    import sqlContext.implicits._

    val people=sparkContext.textFile("hdfs://cloud25:9000/data/people.txt").map(_.split(",")).map(p=>Person(p(0),p(1).trim.toInt)).toDF()
    people.registerTempTable("people")
    val teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 10 and age <= 19")
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
    sparkContext.stop()
  }
}
