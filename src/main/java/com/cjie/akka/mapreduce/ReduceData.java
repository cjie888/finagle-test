package com.cjie.akka.mapreduce;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
import java.util.HashMap;
public final class ReduceData {
    private final HashMap<String, Integer> reduceDataList;
    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }
    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }
}
