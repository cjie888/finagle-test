package cn.cjie.finagle

/**
 * Created by hucj on 2016/1/13.
 */
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

object Client extends App {
  val client: Service[http.Request, http.Response] = Http.newService("www.scala-lang.org:80")
  val request = http.Request(http.Method.Get, "/")
  request.host = "www.scala-lang.org"
  val response: Future[http.Response] = client(request)
  response.onSuccess { resp: http.Response =>
    println("GET success: " + resp)
  }
  Await.ready(response)
}
