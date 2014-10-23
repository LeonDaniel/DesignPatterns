package creational

/**
 * Lazy values in Scala can hold null values. Access to lazy value is thread-safe.
 *
 * @author Daniel Leon
 */
object LazyInit {

  def main(args: Array[String]) {
    lazy val x = {
      print("(computing x in scala) ")
      42
    }

    println(x)
  }
}
