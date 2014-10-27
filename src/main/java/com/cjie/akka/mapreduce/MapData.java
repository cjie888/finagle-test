package com.cjie.akka.mapreduce;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;
public final class MapData {
    private final List<WordCount> dataList;
    public List<WordCount> getDataList() {
        return dataList;
    }
    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
