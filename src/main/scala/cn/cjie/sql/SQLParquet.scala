package cn.cjie.sql

import org.apache.spark.SparkContext

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-24
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
object SQLParquet extends App{
  val sc: SparkContext = new SparkContext("local[2]","graphx")
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  import sqlContext._

  val data = sc.textFile("data/sql/people")

  case class People(name:String, age:Int)

  val people = data.map(_.split(',')).map(p=>People(p(0),p(1).toInt))

  people.saveAsParquetFile("data/test/people/parquet")

  val parquetFile = sqlContext.parquetFile("data/test/people/parquet")

  parquetFile.registerAsTable("parquetFile")

  val rs = sql("select name from parquetFile where age>20")
  rs.map(r=>"name:"+r(0)).collect().foreach(println)
}
