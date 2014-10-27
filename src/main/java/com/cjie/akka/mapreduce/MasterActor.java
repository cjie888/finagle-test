package com.cjie.akka.mapreduce;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class MasterActor extends UntypedActor {
    ActorRef mapActor = getContext().actorOf(
            new Props(MapActor.class).withRouter(new
                    RoundRobinRouter(5)), "map");
    ActorRef reduceActor = getContext().actorOf(
            new Props(ReduceActor.class).withRouter(new
                    RoundRobinRouter(5)),"reduce");
    ActorRef aggregateActor = getContext().actorOf(
            new Props(AggregateActor.class), "aggregate");
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message,getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message,getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message, getSelf());
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext());
        } else
            unhandled(message);
    }
}
