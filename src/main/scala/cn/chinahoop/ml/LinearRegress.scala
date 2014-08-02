package cn.chinahoop.ml

import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.{LinearRegressionWithSGD, LabeledPoint}

/**
 * Created by Administrator on 14-8-2.
 */
object LinearRegress {
  def main(args:Array[String]) {
    val sc=new SparkContext("local[2]","BinaryClassification","spark-0.9.0-incubating-bin-hadoop1")
    val data=sc.textFile("data/ridge-data/lpsa.data")

    val parsedData = data.map { line=>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble,parts(1).split(' ').map(x=> x.toDouble).toArray)
    }
    //构建模型
    val numIterations = 20
    val model = LinearRegressionWithSGD.train(parsedData,numIterations)
    val valuesAndPreds = parsedData.map{ point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val MSE = valuesAndPreds.map {
      case(v,p) => math.pow((v-p),2)
    }.reduce(_+_)/valuesAndPreds.count
    println("training Mean Squared Error=" + MSE)
  }

}
