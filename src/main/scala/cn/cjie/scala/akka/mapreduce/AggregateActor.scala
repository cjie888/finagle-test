package cn.cjie.scala.akka.mapreduce

import akka.actor.Actor
import scala.collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
class AggregateActor extends Actor {
  val finalReducedMap = new mutable.HashMap[String, Int]
  def receive: Receive = {
    case ReduceData(reduceDataMap) =>
      aggregateInMemoryReduce(reduceDataMap)
    case Result =>
      sender ! finalReducedMap.toString()
  }
  def aggregateInMemoryReduce(reducedList: Map[String, Int]): Unit = {
      //add the received Map to the state variable finalReduceMap
    for ((key,value) <- reducedList) {
      if (finalReducedMap contains key)
        finalReducedMap(key) = (value + finalReducedMap.get(key).get)
      else
        finalReducedMap += (key -> value)
    }
  }
}
