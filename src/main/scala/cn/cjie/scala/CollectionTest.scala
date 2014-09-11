package cn.cjie.scala

import scala.collection.mutable.ListBuffer

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-31
 * Time: 下午8:56
 * To change this template use File | Settings | File Templates.
 */
object CollectionTest extends App {
  val l = List(1,2,3,4,5)
  println(l.head)   //1
  println(l.tail)        //List(2,3,4,5)

  val l2 = 1::2::3::Nil
  println(l2.head)   //1
  println(l2.tail)   //List(2,3)

  println(4::l2)  // List(4,1,2,3)

  val lb = ListBuffer(1)

  println(lb+=2)
  println(lb)     //List(1,2)
  lb++=List(4,5,6)
  println(lb)
  lb-=2
}
