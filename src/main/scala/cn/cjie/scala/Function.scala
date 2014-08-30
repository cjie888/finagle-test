package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-30
 * Time: 下午9:50
 * To change this template use File | Settings | File Templates.
 */
object Function extends App {

  def add(a:Int, b:Int) = a+b

  var _add = add _       //missing arguments for method add in object Function;
  //follow this method with `_' if you want to treat it as a partially applied function

  println(_add(1,2))

  def add3(x:Int, y:Int, z:Int):Int = {
    def add2(x:Int, y:Int) :Int = {
      x+y
    }    //嵌套函数
    add2(add(x,y),z)
  }
  println(add3(1,2,3))

  val fun = (x:Int) =>x+2 //匿名函数
  println(fun(10))
  val l = List(1,2,3,4)
  val l2=l.map((x:Int)=>x*2)
  println(l2)

  def add4(x:Int, y:Int, z:Int) = x +y+z
  val a = add4(2,3,_:Int)  //partial function
  println(a(100))

  def add5(x:Int, y:Int) = x +y
  def add6(x:Int)(y:Int) = x+y //柯里化
  add6(1)_

  val a2 = Array(1,2,3,4,5,6,7,8)

  a2.map(2*_).foreach(print)
  print(a2.reduce(_+_))

  def test(flag:Boolean) {
    print(flag)
  }
  test(3>1)
  def test2(flag: =>Boolean) {
    print(flag)
  }
  test2(3>1)
}
