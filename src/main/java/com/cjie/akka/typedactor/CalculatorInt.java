package com.cjie.akka.typedactor;

import akka.actor.TypedActor;
import akka.japi.Option;
import scala.concurrent.Future;


/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-4
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public interface CalculatorInt extends TypedActor.Receiver {
    public Future<Integer> add(Integer first, Integer second);
    public Future<Integer> subtract(Integer first,
                                    Integer second);
    public void incrementCount();
    public Option<Integer> incrementAndReturn();
}