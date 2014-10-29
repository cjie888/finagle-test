package com.cjie.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-29
 * Time: 下午12:57
 * To change this template use File | Settings | File Templates.
 */
public class PingPongApplication {

    public static void main(String[] args) throws InterruptedException {
        ActorSystem _system = ActorSystem.create("BecomeUnbecome");
        ActorRef pingPongActor = _system
                .actorOf(new Props(cn.cjie.scala.akka.actor.PingPongActor.class));
        pingPongActor.tell(PingPongActor.PING, pingPongActor);
        Thread.sleep(2000);
        _system.shutdown();
    }
}
