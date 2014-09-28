package cn.cjie.scala

import scala.io.Source
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-9-28
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */

object Context {  //要放在前面
  implicit def file2RichFile(f:File) = new RichFile(f)


}
object ImplicitDemo extends App{
   import Context._
   println(new File("data/als/test.data").read)

   AAA.print("Jack")("hello")
   implicit val ccc:String ="Hello"
   AAA.print("Jack")
}
class RichFile(val file:File) {
  def read = Source.fromFile(file.getPath).mkString
}
object AAA {
  def print(content: String) (implicit prefix:String) {  //隐式参数
    println(prefix + ":" + content)
  }
}

