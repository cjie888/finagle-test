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
  val list:Collection[Float] = new java.util.ArrayList[Float]()
  list.add(0.000973141302f)
  list.add(0.00093202f)
  val set = list.toSeq

  val sc = new SparkContext("local[2]","test")
  sc.parallelize(set).map(x=>("%.8f" format x)).coalesce(1).saveAsTextFile("/test.txt")

  val sf = 0.000973141302f
  println(sf)
  printf("%.8f\n",sf)
  println("%.8f" format sf)

}
