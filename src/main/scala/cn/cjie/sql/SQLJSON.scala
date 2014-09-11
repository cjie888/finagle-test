package cn.cjie.sql

import org.apache.spark.SparkContext

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-24
 * Time: 下午10:15
 * To change this template use File | Settings | File Templates.
 */
object SQLJSON extends App {
  val sc: SparkContext = new SparkContext("local[2]","graphx")
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  import sqlContext._

  val people = sqlContext.jsonFile("people.json")
  people.registerAsTable("peole")
  sql("select * from people where age > 20")
}
