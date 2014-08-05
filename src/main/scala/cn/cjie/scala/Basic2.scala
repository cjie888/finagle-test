package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-15
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
class Basic2 {

}
class BankCount  {
  var balance: Int = 0

  def deposit(amount:Int) {
    balance+=amount
  }
  def withdraw(amount:Int) {
    balance-=amount
  }
}
//class Person {
//  var name:String = _  //var生成getter和setter方法
//  val age:Int = 10  //val只生成getter方法
//  private[this] val gender = "male" //只能在类内部使用
//}
//主构造器直接跟在类名后边，主构造器的参数会被编译成字段
//主构造器执行时候会执行类中的所有语句
//假设参数声明时不带val和var，那么相当于private[this]
class Person(var name:String, val age:Int) {
  println("This is the primary constructor")
  var gender:String =_
  val school ="BUPT"
  //附属构造器的名称为this
  def this(name:String, age:Int, gender:String) {

    //辅助构造器第一行必须调用主构造器或存在的辅助构造器
    this(name,age)
    this.gender =gender
  }
}
class Student(name:String, age:Int,val major:String) extends Person(name,age) {
  println("this is the subclass of Person, major is:" +major)

  override val school = "ZJU"
  override def toString = "Overrider to String"
}
object Basic2 {
  def main(args:Array[String]){
    //val p = new Person
    //println(p.name)
    //p.name ="tset"
    //println(p.name)
    //println(p.age)
    //p.age = 100
    val p = new Person("Jack",20, "male")
    println(p.name +":"+p.age)
    val s = new Student("Jack",20, "computer")
    print(s.toString)
  }
}
