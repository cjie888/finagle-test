package com.cjie.akka.routor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.dispatch.Dispatchers;
import akka.routing.CustomRoute;
import akka.routing.CustomRouterConfig;
import akka.routing.Destination;
import akka.routing.RouteeProvider;
import scala.collection.immutable.Seq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-10
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class BurstyMessageRouter extends CustomRouterConfig {
    int noOfInstances;
    int messageBurst;
    public BurstyMessageRouter(int inNoOfInstances, int
            inMessageBurst) {
        noOfInstances = inNoOfInstances;
        messageBurst = inMessageBurst;
    }

    @Override
    public CustomRoute createCustomRoute(RouteeProvider routeeProvider) {
        // create the arraylist for holding the actors
        final List<ActorRef> routees = new ArrayList<ActorRef>(noOfInstances);
        Props props = new Props();
        for (int i = 0; i < noOfInstances; i++) {
        // initialize the actors and add to the arraylist
            routees.add(routeeProvider.context().actorOf(props));
        }
        // register the list
        routeeProvider.registerRoutees(routees);

        return new CustomRoute() {
            int messageCount = 0;
            int actorSeq = 0;
            public Seq<Destination> destinationsFor(ActorRef sender, Object message) {
                ActorRef actor = routees.get(actorSeq);
                List<Destination> destinationList = Arrays
                        .asList(new Destination[]
                                {new Destination(sender,
                                        actor)});
                //increment message count
                messageCount++;
                //check message count
                if (messageCount == messageBurst) {
                    actorSeq++;
                    //reset the counter
                    messageCount = 0;
                    //reset actorseq counter
                    if (actorSeq == noOfInstances) {
                        actorSeq = 0;
                    }
                }
                return (Seq<Destination>) destinationList;
            }
        };
    }

    public String routerDispatcher() {
        return Dispatchers.DefaultDispatcherId();
    }
    public SupervisorStrategy supervisorStrategy() {
        return SupervisorStrategy.defaultStrategy();
    }
}
