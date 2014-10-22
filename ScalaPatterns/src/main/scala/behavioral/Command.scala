package behavioral

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * @author Daniel Leon
 */
 object Command {

  trait Order {
    def execute()
    def cancel()
  }

  class Cook {
    def cookPizza() = println("Cook makes a great pizza in Scala")
    def cookSoup() = println("Cook makes a even better soup in Scala")

    def cancelPizza() = println("Great, now I have to eat pizza again tonight!")
    def cancelSoup() = print("If you're my next customer, you're gonna have a bad time!")
  }

  class PizzaOrder(cook : Cook) extends Order {
    override def execute() = cook.cookPizza()
    override def cancel() = cook.cancelPizza()
  }

  class SoupOrder(cook : Cook) extends Order {
    override def execute() = cook.cookSoup()
    override def cancel() = cook.cancelSoup()
  }

  class Waiter{
    private var history: Seq[Order] = Seq.empty

    def serve(order: Order) = {
      order.execute()
      history :+= order
    }

    def cancel(order: Order) = {
      history.filterNot(_ != order)
      order.cancel()
    }
  }

  def main(args : Array[String]) {
    val chef = new Cook
    val pizzaOrder = new PizzaOrder(chef)
    val soupOrder = new SoupOrder(chef)
    val giovanni = new Waiter

    giovanni.serve(pizzaOrder)
    giovanni.serve(soupOrder)

    giovanni.cancel(soupOrder)
  }
}
