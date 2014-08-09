package cn.cjie.graphx

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{VertexId, GraphLoader}
import org.apache.spark.SparkContext._

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-8
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
object GraphxPregel extends App {
  val sc: SparkContext = new SparkContext("local[2]","graphpregel")
  val graph = GraphLoader.edgeListFile(sc, "data/graphx/web-google.txt")

  val sourceId:VertexId = 0

  val g = graph.mapVertices((id,_)=>if(id==sourceId) 0.0 else Double.PositiveInfinity)
  val sssp = g.pregel(Double.PositiveInfinity)(
       (id, dist, newDist) => math.min(dist,newDist),
       triplet=>{
        if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
          Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
        }
        else {
          Iterator.empty
        }
      },
      (a,b) =>math.min(a,b)
  )

  val ranks = graph.pageRank(0.01).vertices.map{
    case (id, prc) => (prc, id)
  }.sortByKey().map(x=>(x._2,x._1)).collect()

  val counts = graph.triangleCount().vertices.map{
    case (id, tc) => (tc, id)
  }.sortByKey().map(x=>(x._2,x._1)).collect()


}

