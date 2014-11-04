package cn.cjie.scala.akka.typedactor

import scala.concurrent.Future


/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-11-4
 * Time: 上午9:47
 * To change this template use File | Settings | File Templates.
 */
trait CalculatorInt {
  def add(first: Int, second: Int): Future[Int]
  def subtract(first: Int, second: Int): Future[Int]
  def incrementCount(): Unit
  def incrementAndReturn(): Option[Int]
}
