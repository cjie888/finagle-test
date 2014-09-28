package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-9-28
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
class Pair3[T:Ordering] (val first:T, val second : T) {
  def smaller2(implicit ord:Ordering[T]) = {
    if(ord.compare(first,second) < 0) first else second
  }
  //implicitly
}
trait LineOrdering extends Ordering[Line] {
  override  def compare(x:Line, y:Line) = {
    if(x.len<y.len) -1
    else if (x.len==y.len) 0
    else 1
  }
}
class Line(val len:Int) {
  override def toString() = "Length of line:" + len
}

object ImplicitDemo2 extends App {
  def smaller[T](a:T,b:T)(implicit order:T=> Ordered[T]) = if (a<b) a else b
  implicit class HH(x:Int) {   //要转的类型
      def add2(y:Int) = y+2
  }
  println(smaller(2,3))
  println(smaller("AAA","BBB"))
  println(1.add2(2))

  val p1 = new Pair3(1,2)
  val p2 = new Pair3('A','B')
  implicit object Line extends LineOrdering
  val l1 = new Line(1)
  val l2 = new Line(2)

  val p3 = new Pair3[Line](l1,l2)
  println(p1.smaller2)
  println(p2.smaller2)
  println(p3.smaller2)
}

