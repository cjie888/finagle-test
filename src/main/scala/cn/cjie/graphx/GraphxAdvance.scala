package cn.cjie.graphx

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.graphx.util.GraphGenerators

// To make some of the examples work we will also need RDD
import org.apache.spark.rdd.RDD

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-8
 * Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 */
object GraphxAdvance extends App{
  // Assume the SparkContext has already been constructed
  val sc: SparkContext = new SparkContext("local[2]","graphx")
  // Create a graph with "age" as the vertex property.  Here we use a random graph for simplicity.
  val graph: Graph[Double, Int] =
    GraphGenerators.logNormalGraph(sc, numVertices = 100).mapVertices( (id, _) => id.toDouble )
  // Compute the number of older followers and their total age
  val olderFollowers: VertexRDD[(Int, Double)] = graph.mapReduceTriplets[(Int, Double)](
    triplet => { // Map Function
      if (triplet.srcAttr > triplet.dstAttr) {
        // Send message to destination vertex containing counter and age
        Iterator((triplet.dstId, (1, triplet.srcAttr)))
      } else {
        // Don't send a message for this triplet
        Iterator.empty
      }
    },
    // Add counter and age
    (a, b) => (a._1 + b._1, a._2 + b._2) // Reduce Function
  )
  olderFollowers.foreach(println)
  // Divide total age by number of older followers to get average age of older followers
  val avgAgeOfOlderFollowers: VertexRDD[Double] =
    olderFollowers.mapValues( (id, value) => value match {
      case (count, totalAge) => totalAge / count } )
  // Display the results
  avgAgeOfOlderFollowers.collect.foreach(println(_))

  val neighbors = graph.collectNeighbors(EdgeDirection.Out)

}
