package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-7-14
 * Time: 下午8:10
 * To change this template use File | Settings | File Templates.
 */
object WeekOne {
  def add(x:Int,y:Int) = {
    x+y
  }
  def say() {
    println("Hello")
  }
  def sayName(name:String = "Jack") {
    println(name)
  }
  def sum(elems:Int *) { //变长参数

  }
  def sign(x:Int) = {
    if (x>0) 1 else -1
  }
  def count(x:String) {
    val contents = x.split(' ')
    var map = scala.collection.mutable.HashMap[String,Int]()
    for (content <- contents)  {
      var num = map.getOrElse(content,0)
      map.put(content,num+1)
    }
    for((k,v) <- map) {
      println(k, v)
    }

  }

  def transfer(x:scala.collection.mutable.ArrayBuffer[Int])  {
    for (i <- Range(0,x.length,2)) {
      //println(i)

      if (i + 1 < x.length)
        print(x(i+1))
        print(',')
      print(x(i))
    }
  }

  //没有返回值返回unit类型
   def main(args:Array[String]){
     print(add(y=3,x=1))
     println(add(2,3))
     println(say)
     sayName()//带默认参数必须加括号
     for (i <- 1 to 10) {
       println(i)
     }
     for (i <- 1 until 10) {
       println(i)
     }
    for (i <- 1 until 20 if i%2 == 0) {
      println(i)
    }
     val x=0
     if (x>0) 1 else -1
     val array_name = new Array[Int](5)  //默认值0
     println(array_name(3))
     //println(array_name(10))  //数组越界

     val a = scala.collection.mutable.ArrayBuffer[Int]()
     a+=(3,4,5,6,7,8,9,10)
     a.insert(0,0)
     a.remove(2)
     a.remove(0, 3)
     a.toArray.sum
     a.trimEnd(2)  //移除最后2个元素
     for (n <- a) {
       println(n)
     }
     for (i <- 0 until a.length) {
       println(i)
     }
     val age=Map("Jack"->20,"Lucy"->18)
     age.getOrElse("Jack",0)
     for ((k,v) <- age) {
       println(k,v)
     }
     val ab= (1,2,3,4,5)
     for (elem <- ab.productIterator) {
       println(elem)
     }
     println(sign(10))
     println(sign(-5))
     for (i <- 1 to 10 reverse)
       println(i)
     var ta = scala.collection.mutable.ArrayBuffer[Int]()
     ta+=(1,2,3,4,5,6,7,8,9,10)
     transfer(ta)
     ta+= 11
     transfer(ta)
     count("a ab a cc dd a ab d")
   }
}
