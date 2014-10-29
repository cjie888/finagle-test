package cn.cjie.scala.akka.actor

import akka.actor.{Props, ActorSystem}

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-29
 * Time: 下午1:01
 * To change this template use File | Settings | File Templates.
 */
object PingPongApplication extends App {
  val _system = ActorSystem("BecomeUnbecome")
  val pingPongActor = _system.actorOf(Props[PingPongActor])
  pingPongActor ! PING
  Thread.sleep(2000)
  _system.shutdown
}
