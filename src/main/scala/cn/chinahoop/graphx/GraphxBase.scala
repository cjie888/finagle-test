package cn.chinahoop.graphx

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-4
 * Time: 下午8:04
 * To change this template use File | Settings | File Templates.
 */


import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.streaming.StreamingContext

// To make some of the examples work we will also need RDD
import org.apache.spark.rdd.RDD
object GraphxBase extends App {

  // Assume the SparkContext has already been constructed
  val sc: SparkContext = new SparkContext("local[2]","graphx")
  // Create an RDD for the vertices
  val users: RDD[(VertexId, (String, String))] =
    sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
      (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))
  // Create an RDD for edges
  val relationships: RDD[Edge[String]] =
    sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
      Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
  // Define a default user in case there are relationship with missing user
  val defaultUser = ("John Doe", "Missing")
  // Build the initial Graph
  val graph = Graph(users, relationships, defaultUser)

  // Count all users which are postdocs
  println(graph.vertices.filter { case (id, (name, pos)) => pos == "postdoc" }.count)
  // Count all the edges where src > dst
  println(graph.edges.filter(e => e.srcId > e.dstId).count)

  val triplets = graph.triplets.collect
  for (triplet <- triplets) {
     println(triplet)
  }


}
