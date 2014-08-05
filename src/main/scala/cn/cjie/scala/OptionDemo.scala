package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-30
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */
//Option 包括Some和None
object OptionDemo extends App {
  val m = Map(1->2)
  m.get(1) match {
    case Some(v) => println(v)
    case None => println("no such key")
  }

  val s ="Yes"
  val b = s"$s,I do"
  println(b)
}
