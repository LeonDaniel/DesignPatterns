package creational

/**
 * Scala provides concise direct realization of the singleton pattern in the language
 * Objects can inherit methods from classes or interfaces. Object can be referenced (directly or via an inherited interface).
 * In Scala, objects are initialized on-demand.
 */
object Singleton {

  def main(args:Array[String]) {

    object Cat extends Runnable {
      def run() {
        // do nothing
        println("I'm a scala cat running around !")
      }
    }

    Cat.run()
  }
}
