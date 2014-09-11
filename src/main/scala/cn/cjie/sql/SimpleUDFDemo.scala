package cn.cjie.sql

import org.apache.hadoop.hive.ql.exec.UDF

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
