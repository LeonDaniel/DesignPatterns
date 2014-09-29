package behavioral

/**
 * In Scala, we can rely on by-name parameter to defer evaluation of any expression
 */
object Command {

  object Invoker {
    private var history: Seq[() => Unit] = Seq.empty

    def invoke(command: => Unit) { // by-name parameter
      command
      history :+= command _
    }
  }

  def main(args : Array[String]) {
    Invoker.invoke(println("foo"))

    Invoker.invoke {
      println("bar 1")
      println("bar 2")
    }
  }

}
