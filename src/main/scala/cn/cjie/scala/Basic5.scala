package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-22
 * Time: 下午9:04
 * To change this template use File | Settings | File Templates.
 */
class Basic5 {

}
object Basic5 extends App {
  val value=3
  val result =value match {
    case 1 => "one"
    case 2 => "two"
    case _  => "some other value"
  }
  print("result match is:" +result)
  val result2 = value match {
    case i if i==1 => "one"
    case i if i==2 => "one"
    case _  => "some other value"
  }
  def t(obj:Any) = obj match {
    case x:Int => print("Int")
    case x:String => print("String")
    case _ => print("unknow type")
  }
  t(1)
  t("abc")
  t(1L)
}
