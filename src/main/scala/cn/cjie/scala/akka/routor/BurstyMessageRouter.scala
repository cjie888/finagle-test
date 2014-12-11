package cn.cjie.scala.akka.routor

import akka.routing.{Destination, Route, RouteeProvider, RouterConfig}
import akka.dispatch.Dispatchers
import akka.actor.{Props, SupervisorStrategy}

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-10
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
abstract class BurstyMessageRouter(noOfInstances: Int, messageBurst: Int)
  extends RouterConfig {

  def routerDispatcher: String = Dispatchers.DefaultDispatcherId
  def supervisorStrategy: SupervisorStrategy = SupervisorStrategy.defaultStrategy

  //def createRoute(props: Props, routeeProvider: RouteeProvider): Route =
  //{
    //Nil[Route]
    //routeeProvider.createRoutees(noOfInstances)
//    {
//      case (sender, message) =>
//        var actor = routeeProvider.routees(actorSeq)
//        //increment message count
//        messageCount += 1
//        //check message count
//        if (messageCount == messageBurst) {
//          actorSeq += 1
//          //reset the counter
//          messageCount = 0
//          //reset actorseq counter
//          if (actorSeq == noOfInstances) {
//            actorSeq = 0
//          }
//        }
//        List(Destination(sender, actor))
    //List(Iterable.empty[Destination])
 //   }
 // }
}
