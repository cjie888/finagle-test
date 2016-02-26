package cn.cjie.finagle

/**
 * Created by hucj on 2016/1/13.
 */
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

object Proxy extends App {
  val client: Service[Request, Response] =
    Http.newService("www.baidu.com")

  val server = Http.serve(":8080", client)
  Await.ready(server)
}
