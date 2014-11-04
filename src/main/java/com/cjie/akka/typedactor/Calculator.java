package com.cjie.akka.typedactor;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.TypedActor;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import akka.japi.Option;
import akka.util.Timeout;
import scala.concurrent.Future;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-4
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 */
public class Calculator implements CalculatorInt, TypedActor.PreStart, TypedActor.PostStop {
    Integer counter = 0;
    //Non-blocking request response
    public Future<Integer> add(Integer first, Integer second) {
        return Futures.successful(first + second);
    }
    //Non-blocking request response
    public Future<Integer> subtract(Integer first,
                                    Integer second) {
        return Futures.successful(first - second);
    }
    //fire and forget
    public void incrementCount() {
        counter++;
    }
    //Blocking request response
    public Option<Integer> incrementAndReturn() {
        return Option.some(++counter);
    }

    LoggingAdapter log = Logging.getLogger(
            TypedActor.context().system(), this);
    //Allows to tap into the Actor PreStart hook
    public void preStart() {
        log.info("Actor Started !");
    }
    //Allows to tap into the Actor PostStop hook
    public void postStop() {
        log.info("Actor Stopped !");
    }

    public void onReceive(Object msg, ActorRef actor) {
        log.info("Received Message -> {}", msg);
    }

    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, new Timeout(10, TimeUnit.SECONDS).duration(),
                    new Function<Throwable, SupervisorStrategy.Directive>() {
                        public SupervisorStrategy.Directive apply(Throwable t) {
//                            if (t instanceof ArithmeticException) {
//                                return resume();
//                            } else if (t instanceof IllegalArgumentException) {
//                                return restart();
//                            } else if (t instanceof NullPointerException) {
//                                return stop();
//                            } else {
//                                return escalate();
//                            }
                            return  null;
                        }
                    });
}
