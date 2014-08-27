package cn.cjie.sql

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-27
 * Time: 下午8:34
 * To change this template use File | Settings | File Templates.
 */
class SimpleUDFDemo extends UDF{
  def evaluate(input:String):String = {
    if (input==null) "Null" else "Hello" + input
  }
}
