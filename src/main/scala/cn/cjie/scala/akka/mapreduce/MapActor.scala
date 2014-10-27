package cn.cjie.scala.akka.mapreduce

import akka.actor.Actor
import scala.collection.mutable.ArrayBuffer
import cn.cjie.scala.akka.mapreduce._

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午5:03
 * To change this template use File | Settings | File Templates.
 */
class MapActor extends Actor {
  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at",
    "be","do", "go", "if", "in", "is", "it", "of", "on", "the", "to")

  val defaultCount: Int = 1
  def receive: Receive = {
    case message: String =>
      sender ! evaluateExpression(message)
  }
  def evaluateExpression(line: String): MapData = MapData {
    //logic to map the words in the sentences
    line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
      (index, word) =>
        if(!STOP_WORDS_LIST.contains(word.toLowerCase))
          index += WordCount(word.toLowerCase, 1)
        else
          index
    }
  }
}
