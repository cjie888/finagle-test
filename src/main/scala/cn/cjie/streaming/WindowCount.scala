package streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel

/**
 * Created by Administrator on 14-7-27.
 */
object WindowCount {
  def main(args:Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: windowCount <master> <hostname> <port> <interval>\n"
        + "<windowLength> <slideinterval>"
        +"In local mode,<master> should be 'local[n]' with n>1")
      System.exit(1)
    }
    val ssc = new StreamingContext(args(0),"WindowCounter",Seconds(args(3).toInt),
      System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass).toSeq)
    ssc.checkpoint(".")
    val lines = ssc.socketTextStream(args(1),args(2).toInt,StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x=>(x,1)).reduceByKeyAndWindow((x:Int,y:Int)=>x+y,Seconds(args(4).toInt),Seconds(args(5).toInt))
    //val wordCounts = words.map(x=>(x,1)).reduceByKeyAndWindow(_+_,_-_,Seconds(args(4).toInt),Seconds(args(5).toInt)).reduce((a, b) => if (a._2 > b._2) a else b)

    //var wordcounts = words.map(x=>(x,1)).reduceByKeyAndWindow(_+_,Seconds(30))
    //var wordcounts = words.map(x=>(x,1)).reduceByKeyAndWindow((x:Int,y:Int)=>x+y,_-_,Seconds(5),Seconds(6))
    //var wordcounts = words.map(x=>(x,1)).countByValueAndWindow(Seconds(12),Seconds(30))

   // val sortedWordCont = wordCounts.map{case(char,count) => (count,char)}.transform{_.sortByKey(false)}
    wordCounts.print()
    ssc.start
    ssc.awaitTermination()

  }
}
