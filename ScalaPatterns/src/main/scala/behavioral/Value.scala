package behavioral

/**
 * Case classes export constructor parameters as properties. By default, case classes are immutable.
 */

object Value {

  case class Point(x: Int, y: Int)

  def main(args:Array[String]) {
    val point = Point(1, 2)
    val point1 = Point(1, 2)

    point match {
      case point1 => println("The points are the same")
      case _ => println("The points are different")
    }
  }
}
