package com.eric.spark.mllib.kmeans

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 该程序主要通过kmeans算法对数据进行分类
 *执行方式：./spark-submit --master=spark://cloud25:7077 --class com.eric.spark.mllib.CholesterolPrecditSample --executor-memory=2g /opt/cloud/spark-1.4.1-bin-hadoop2.6/lib/spark_scala.jar
 * Created by Eric on 2015/11/12.
 */
object CholesterolPrecditSample {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //设置环境
    val sparkConconf=new SparkConf().setAppName("CholesterolSample")
    val sparkContext=new SparkContext(sparkConconf)

    //装载数据
    val fileData=sparkContext.textFile("/data/mllib/Cholesterol.csv",1)
    val parseData=fileData.map(record=>Vectors.dense(record.split(",").map(_.toDouble)))

    //模型训练
    val dataModelNumber=6;
    val dataModelTrainTimes=20
    val model=KMeans.train(parseData,dataModelNumber,dataModelTrainTimes)

    //数据模型的中心点
    println("Cluster centers:")
    for (c <- model.clusterCenters) {
      println("  " + c.toString)
    }

    //交叉评估2，返回数据集和结果
    val result2 = fileData.map {
      line =>
        val linevectore = Vectors.dense(line.split(',').map(_.toDouble))
        val prediction = model.predict(linevectore)
        line + " " + prediction
    }.saveAsTextFile("/data/mllib/result3")

    sparkContext.stop()


  }
}
