package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-14
 * Time: 下午6:40
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.JavaConversions._
import java.util.Collection
import org.apache.spark.SparkContext

object Test extends  App {
  println("Hello")
  val list:Collection[String] = new java.util.ArrayList[String]()
  list.add("test")
  list.add("test2")
  val set = list.toSeq
  val sc = new SparkContext("local[2]","test")
  sc.parallelize(set).collect().foreach(println)
}
