package cn.chinahoop.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-29
 * Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 */
abstract class Person5 {

}
case class Teacher5(name:String) extends Person5
case class Student5(name:String) extends Person5
object CaseClass extends App {
   def m(p:Person5) {
     p match {
       //case Teacher5("ab") =>println("This is a teacher")
       case Teacher5(_) =>println("This is a teacher")
       case Student5(_) =>println("This is a student")
       case _ =>println("This is unkown")
     }
   }
  m(Teacher5("a"))
}
