package cn.chinahoop.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-22
 * Time: 下午8:41
 * To change this template use File | Settings | File Templates.
 */
class Basic4 {

}
class ApplyTest {
  def apply() = "APPLY"
  def test{
    print("test")
  }
}
object ApplyTest {
  def apply() = new ApplyTest
}

object Basic4 extends App {
  var a = ApplyTest() //调用object的apply方法
  a.test
  var b = new ApplyTest
  print(b())   //调用class的apply方法

}
