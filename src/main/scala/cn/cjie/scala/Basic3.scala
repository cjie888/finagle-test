package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-15
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
class Basic3 {

}
abstract class Person1 {
  def speak
  val name:String
  var age:Int
}
class Student1 extends Person1 {
  def speak {
    println("Speak!!")
  }
  val name = "AAA"
  var age = 100
}
//trait Logger {
//  def log(msg:String) {
//    println("log:"+msg)
//  }
//}
trait Logger {
  def log(msg:String)
}
trait ConsoleLogger extends Logger {
  def log(msg:String) {
    print("Save money:" +msg)
  }
}
//class Test extends Logger {
//  def test {
//    log("xxx")
//  }
//}
class Test2 extends ConsoleLogger {
  def test {
    log("xxx")
  }
}
trait MessageLogger extends ConsoleLogger {
  override def log(msg:String) {
    print("Save money to bank:" +msg)
  }
}
abstract class Account {
  def save
}
class MyAccount extends Account with ConsoleLogger {
  def save() {
    log("111")
  }
}
object Basic3 extends App {
  val s = new Student1
  s.speak
  print(s.name + ":" + s.age)
  var t =new Test2
  t.test
//  var myAccount = new MyAccount
//  myAccount.save
  var myAccount = new MyAccount  with  MessageLogger
  myAccount.save
}
