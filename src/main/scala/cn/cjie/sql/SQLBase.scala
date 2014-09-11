package cn.cjie.sql

import org.apache.spark.SparkContext

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-24
 * Time: 下午9:50
 * To change this template use File | Settings | File Templates.
 */
object SQLBase extends App {
  val sc: SparkContext = new SparkContext("local[2]","graphx")
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  import sqlContext._

  case class Word(wid:Int, aid:Int, times:Int)

  val word = sc.textFile("sql/docword.nytimes.txt").map(_.split(' ')).filter(_.length==3).map(w=>Word(w(0).toInt,w(1).toInt,w(2).toInt))
  word.registerAsTable("word")

  sql("select * from word limit 10").collect()


}
