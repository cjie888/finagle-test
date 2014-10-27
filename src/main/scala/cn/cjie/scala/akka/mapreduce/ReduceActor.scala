package cn.cjie.scala.akka.mapreduce

import akka.actor.Actor

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
class ReduceActor extends Actor {
  def receive: Receive = {
    case MapData(dataList) =>
      sender ! reduce(dataList)
  }
  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    //Reduces the list for duplicate words in the mapped data list
      words.foldLeft(Map.empty[String, Int]) { (index, words) =>
        if (index contains words.word)
          index + (words.word -> (index.get(words.word).get + 1))
        else
          index + (words.word -> 1)
      }
  }
}
