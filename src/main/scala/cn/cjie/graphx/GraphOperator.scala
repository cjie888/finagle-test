package cn.cjie.graphx

import org.apache.spark.graphx.{VertexId, GraphLoader}
import org.apache.spark.SparkContext

/**
 * Created by hucj on 14-8-4.
 */
object GraphOperator extends App {
  val sc: SparkContext = new SparkContext("local[2]","graphloader")
  val graph = GraphLoader.edgeListFile(sc, "data/graphx/web-google.txt")

  println(graph.vertices.count)
  println(graph.edges.count)
  graph.vertices.take(10)

  val temp = graph.mapVertices((id,attr)=>attr.toInt*2)

  graph.edges.take(10)
  val temp2 = graph.mapEdges(e=>e.attr.toInt*2)
  val temp3 = graph.mapTriplets(
    et=>et.srcAttr.toInt*2+ et.dstAttr.toInt*3)
  val subGraph = graph.subgraph(epred = e=>e.srcId>e.dstAttr, vpred = (id,_)=>id>500000)
  subGraph.vertices.take(10)
  val tempIndegrees = graph.inDegrees
  tempIndegrees.take(10)

  def max(a:(VertexId,Int), b:(VertexId,Int)) :(VertexId,Int) = {
    if (a._2>b._2) a else b
  }
  graph.degrees.reduce(max)  //求最大的degeree
  val rawGraph = graph.mapVertices((id,attr)=>0)
  val cutGraph = rawGraph.outDegrees //所有有出度的点的集合
  val joinGraph = rawGraph.joinVertices[Int](cutGraph)((_,_,optDat)=>optDat)
  val joinGraph2 = rawGraph.outerJoinVertices[Int,Int](cutGraph)((_, _, optDeg) => optDeg.getOrElse(0))

}
