package cn.cjie.scala.akka.mapreduce

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import scala.concurrent.Await
import scala.collection.mutable.ArrayBuffer
import java.util.concurrent.TimeUnit
import akka.pattern.ask

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午5:21
 * To change this template use File | Settings | File Templates.
 */

sealed trait MapReduceMessage
case class WordCount(word: String, count: Int) extends
MapReduceMessage
case class MapData(dataList: ArrayBuffer[WordCount]) extends
MapReduceMessage
case class ReduceData(reduceDataMap: Map[String, Int]) extends
MapReduceMessage
case class Result extends MapReduceMessage


object MapReduceApplication  extends  App {

  val _system = ActorSystem("MapReduceApp")
  val master = _system.actorOf(Props[MasterActor], name = "master")
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  master ! "The quick brown fox tried to jump over the lazy dog and fell on the dog"
  master ! "Dog is man's best friend"
  master ! "Dog and Fox belong to the same family"

  Thread.sleep(500)
  val future = (master ? Result).mapTo[String]
  val result = Await.result(future, timeout.duration)
  println(result)
  _system.shutdown

}
