package com.cjie.akka.mapreduce;

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-10-27
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public final class WordCount {
    private final String word;
    private final Integer count;
    public WordCount(String inWord, Integer inCount) {
        word = inWord;
        count = inCount;
    }
    public String getWord() {
        return word;
    }
    public Integer getCount() {
        return count;
    }
}