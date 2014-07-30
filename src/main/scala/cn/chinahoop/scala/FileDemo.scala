package cn.chinahoop.scala

import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-29
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 */
object FileDemo extends App{
  val file = Source.fromFile("D:\\workspace\\spark-test\\README.md")
  for (line<-file.getLines()) {
    println(line)
  }
  val contents  =  Source.fromURL("http://www.baidu.com")
  for (line<-contents.getLines()) {
    println(line)
  }
}
