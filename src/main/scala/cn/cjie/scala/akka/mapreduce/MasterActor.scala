package cn.cjie.scala.akka.mapreduce;


import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.routing.RoundRobinRouter

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
class MasterActor extends Actor {
    val mapActor = context.actorOf(Props[MapActor].withRouter(
            RoundRobinRouter(nrOfInstances = 5)), name = "map")
    val reduceActor = context.actorOf(Props[ReduceActor].withRouter(
            RoundRobinRouter(nrOfInstances = 5)), name = "reduce")
    val aggregateActor = context.actorOf(Props[AggregateActor],
            name = "aggregate")
    def receive: Receive = {
        case line: String =>
            mapActor ! line
        case mapData: MapData =>
            reduceActor ! mapData
        case reduceData: ReduceData =>
            aggregateActor ! reduceData
        case Result =>
            aggregateActor forward Result
    }
}
