package com.cjie.akka.mapreduce;

import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public class ReduceActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
            // reduce the incoming data and forward the result to Master actor
            getSender().tell(reduce(mapData.getDataList()),self());
        } else
            unhandled(message);
    }
    private ReduceData reduce(List<WordCount> dataList) {
        HashMap<String, Integer> reducedMap = new HashMap<String,
                Integer>();
        for (WordCount wordCount : dataList) {
            if (reducedMap.containsKey(wordCount.getWord())) {
                Integer value = (Integer)
                        reducedMap.get(wordCount.getWord());
                value++;
                reducedMap.put(wordCount.getWord(), value);
            } else {
                reducedMap.put(wordCount.getWord(), Integer.valueOf(1));
            }
        }
        return new ReduceData(reducedMap);
    }
}
