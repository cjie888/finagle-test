package com.cjie.akka.actor;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-29
 * Time: 下午12:53
 * To change this template use File | Settings | File Templates.
 */
public class PingPongActor extends UntypedActor {
    static String PING = "PING";
    static String PONG = "PONG";
    int count = 0;
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            if (((String) message).matches(PING)) {
                System.out.println("PING");
                count += 1;
                Thread.sleep(100);
                getSelf().tell(PONG,getSelf());
                getContext().become(new Procedure<Object>() {
                    public void apply(Object message) {
                        if (message instanceof String) {
                            if (((String) message).matches(PONG)) {
                                System.out.println("PONG");
                                count += 1;
                                try { Thread.sleep(100); }
                                catch(Exception e) {
                                }
                                getSelf().tell(PING, getSelf());
                                getContext().unbecome();
                            }
                        }
                    }
                });
                if (count > 10)
                    getContext().stop(getSelf());
            }
        }
    }
}
