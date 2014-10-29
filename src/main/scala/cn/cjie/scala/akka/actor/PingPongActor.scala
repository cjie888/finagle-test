package cn.cjie.scala.akka.actor

import akka.actor.Actor

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-29
 * Time: 下午12:55
 * To change this template use File | Settings | File Templates.
 */
case class PING
case class PONG
class PingPongActor extends Actor {
  import context._
  var count = 0
  def receive: Receive = {
    case PING =>
      println("PING")
      count = count + 1
      Thread.sleep(100)
      self ! PONG
      become {
        case PONG =>
          println("PONG")
          count = count + 1
          Thread.sleep(100)
          self ! PING
          unbecome()
      }
      if(count > 10) context.stop(self)
  }
}
