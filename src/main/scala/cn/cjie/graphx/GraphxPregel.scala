package cn.cjie.graphx

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{VertexId, GraphLoader}

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-8
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
object GraphxPregel extends App {
  val sc: SparkContext = new SparkContext("local[2]","graphloader")
  val graph = GraphLoader.edgeListFile(sc, "data/graphx/web-google.txt")

  val sourceId:VertexId = 0

  val g = graph.mapVertices((id,_)=>if(id==sourceId) 0.0 else Double.PositiveInfinity)
  val sssp = g.pregel(Double.PositiveInfinity)(
       (id, dist, newDist) => math.min(dist,newDist),
       triplet=>{
        if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
          Iterator(triplet.dstId, triplet.srcAttr + triplet.attr)
        }
        else {
          Iterator.empty
        }
      },
      (a,b) =>math.min(a,b)
  )
}

