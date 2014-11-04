package cn.cjie.scala.akka.typedactor

import akka.actor.{OneForOneStrategy, SupervisorStrategy, ActorRef, TypedActor}
import scala.concurrent.{Promise, Future}
import akka.actor.TypedActor.{PreStart, PostStop}
import akka.event.Logging
import akka.actor.SupervisorStrategy.Resume

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-4
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 */
class Calculator extends CalculatorInt with PreStart with PostStop {
  var counter: Int = 0
  import TypedActor.dispatcher
  def add(first: Int, second: Int): Future[Int] =
    Promise successful first + second
  def subtract(first: Int, second: Int): Future[Int] =
    Promise successful first - second
  def incrementCount(): Unit = counter += 1
  def incrementAndReturn(): Option[Int] = {
    counter += 1
    Some(counter)
  }
  import  TypedActor.context
  val log = Logging(context.system,
    TypedActor.self.getClass())
  def preStart(): Unit = {
    log.info ("Actor Started")
  }
  def postStop(): Unit = {
    log.info ("Actor Stopped")
  }

  def onReceive(message: Any, sender: ActorRef): Unit = {
    log.info("Message received->{}", message)
  }

  def supervisorStrategy(): SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10,
      withinTimeRange = 10 seconds) {
      case _: ArithmeticException => Resume
      case _: IllegalArgumentException => Restart
      case _: NullPointerException => Stop
      case _: Exception => Escalate
    }
}
