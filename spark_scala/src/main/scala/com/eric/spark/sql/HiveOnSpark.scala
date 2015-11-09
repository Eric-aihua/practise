package com.eric.spark.sql

/**
 * Created by Eric on 2015/11/7.
 */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object HiveOnSpark {
  case class Record(key: Int, value: String)

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("HiveFromSpark")
    val sc = new SparkContext(sparkConf)

    val hiveContext = new HiveContext(sc)
    import hiveContext._

    sql("select WEBSESSION,count(WEBSESSION) as cw from SOGOUQ1 group by WEBSESSION order by cw desc limit 10")
      .collect().foreach(println)

    sc.stop()
  }
}