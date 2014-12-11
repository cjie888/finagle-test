package cn.cjie.streaming

import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel

/**
 * Created by Administrator on 14-7-27.
 */
object StatefulNetworkWordCount {
   def main(args:Array[String]) {
     if (args.length < 4) {
       System.err.println("Usage: NetworkWordCount <master> <hostname> <port> <seconds>\n"
         +"In local mode,<master> should be 'local[n]' with n>1")
       System.exit(1)
     }
     val updateFunc = (values:Seq[Int], state:Option[Int]) => {
       val currentCount = values.foldLeft(0)(_+_)
       val previousCount = state.getOrElse(0)
       Some(currentCount+previousCount)
     }
     val ssc = new StreamingContext(args(0),"NetWorkCount",Seconds(args(3).toInt),
       //System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass)) //spark 0.9
       System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass).toSeq) //spark 1.0 change
     ssc.checkpoint(".")
     val lines = ssc.socketTextStream(args(1),args(2).toInt,StorageLevel.MEMORY_ONLY_SER)
     val words = lines.flatMap(_.split(" "))
     // val wordCounts = words.map(x => (x,1)).reduceByKey(_+_)
     val wordDstream = words.map(x => (x,1))
     val stateDstram = wordDstream.updateStateByKey[Int](updateFunc)
     stateDstram.print()
     ssc.start()
     ssc.awaitTermination()
   }
}
