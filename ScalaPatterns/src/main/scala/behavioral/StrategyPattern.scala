package behavioral

/**
 * Because Scala offers first-class functions, we can use them to express the same concept directly
 */
object StrategyPattern {

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
