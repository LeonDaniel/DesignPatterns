package behavioral

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * Here we can use the behavior encapsulated in the command object as a simple function that takes a receiver object and
 * calls the associated method; this way we skip the creation of a command interface and its concrete
 * implementations
 *
 * @author Daniel Leon
 */
object SimpleCommand {

  class Cook {
    def cookPizza() = println("Cooking some nice pizza")

    def cookSoup() = println("Pouring some soup from yesterday")
  }

  object Waiter {
    private var history: Seq[(Cook) => Unit] = Seq.empty

    def serve(command: (Cook) => Unit, cook: Cook): Unit = {
      command.apply(cook)
      history :+= command
    }
  }

  //Even simpler case
  object Invoker {
    private var history: Seq[() => Unit] = Seq.empty

    def invoke(command: => Unit) { // by-name parameter
      command
      history :+= command _
    }
  }

  def main(args: Array[String]) {

    val chef = new Cook

    Waiter.serve(cook => cook.cookPizza(), chef)
    Waiter.serve(cook => cook.cookSoup(), chef)

    println("\nAn even simpler case:")
    Invoker.invoke(println("Simple command"))

    Invoker.invoke {
      println("So much easy!")
      println("Ok...done..nothing to do here!")
    }
  }
}