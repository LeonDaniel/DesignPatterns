package behavioral

/**
 * Value object is a small immutable object that represents a simple entity whose equality isn’t based on identity. Value objects are equal if all their fields are equal.
 * Value objects are widely used to represent numbers, dates, colors, etc. In enterprise applications they are used as DTOs a for inter-process communication.
 * Because of the immutability, value objects are handy in multi-threaded programing.
 *
 * Case classes exports constructor parameters as properties. By default, case classes are immutable.
 * Another approach could be using tuple sets
 *
 * @author Daniel Leon
 */
object Value {

  case class Point(x: Int, y: Int)

  def main(args:Array[String]) {
    //first method using case classes
    val firstPoint = Point(1, 2)
    val secondPoint = Point(1, 3)

    firstPoint match {
      case matchPoint if matchPoint.x == secondPoint.x && matchPoint.y == secondPoint.y => println("First approach : The points are the same")
      case _ => println("First approach : The points are different")
    }

    //second method using tuple
    val tuple = (1,2)
    tuple match {
      case (1,3) => println("Second approach : The points are the same")
      case _ => println("Second approach : The points are different")
    }
  }
}
