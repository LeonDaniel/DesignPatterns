package behavioral

/**
 * The strategy pattern defines a family of encapsulated algorithms and lets the algorithm vary independently from clients that use it.
 * The pattern is handy when we need to select an algorithm at runtime.
 *
 * We use Scala first-class functions to express the same concept directly
 *
 * @author Daniel Leon
 */
object Strategy {

  type Strategy = (Int, Int) => Int

  class Context(computer: Strategy) {
    def use(a: Int, b: Int) = println("The result of operation : " + computer(a, b))
  }

  val add: Strategy = _ + _
  val multiply: Strategy = _ * _
  val complexStuff: Strategy = (a, b) => a * b + a - b

  def main(args : Array[String]) {

    new Context(multiply).use(2, 3)
    new Context(add).use(2, 3)
    new Context(complexStuff).use(2, 3)
  }
}
