package cn.cjie.scala

/**
 * Created with IntelliJ IDEA.
 * User: hucj
 * Date: 14-8-22
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
import util.control.Breaks._

object BreakAndContinueDemo extends App {

  // 1) BREAK: this example breaks out of the for loop when (i > 4)
  println("\n=== BREAK EXAMPLE ===")
  breakable {
    for (i <- 1 to 10) {
      println(i)
      if (i > 4) break  // break out of the for loop
    }
  }

  // 2) CONTINUE: this example acts like a Java continue statement
  // "A continue statement skips the current iteration of a for, while , or do-while loop."
  println("\n=== CONTINUE EXAMPLE ===")
  val searchMe = "peter piper picked a peck of pickled peppers"
  var numPs = 0
  for (i <- 0 until searchMe.length) {
    breakable {
      if (searchMe.charAt(i) != 'p') {
        break  // break out of the 'breakable', continue the outside loop
      } else {
        numPs += 1
      }
    }
  }
  println("Found " + numPs + " p's in the string.")
}