package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-9-25
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
object Bounds extends App {
  val p = new  Pair("A","B")
  println(p.smaller)
  val p2 = new Pair(1,2)  //inferred type arguments [Int] do not conform to class Pair's type parameter bounds [T <: Comparable[T]]
  //Int没有实现Comparable接口
  println(p2.smaller)
  val s1 = new Student6("A")
  val s2 =new Student6("B")
  val p3 = new Pair2(s1,s2)
  val p4 = new Person6("AA")
  println(p3.replaceFirst(p4))

  new X(s1)
  new X(p4)
}
class Pair[T <% Comparable[T]](val first:T, val second:T) {
  def smaller =  if(first.compareTo(second) < 0) first else second
}
class Pair2[T](val first:T, val second:T) {
  def replaceFirst[R >: T](newFirst:R) = new Pair2(newFirst,second)
}

class Person6(val name:String)
class Student6(name:String) extends  Person6(name)

class X[+T](val x: T){

}
