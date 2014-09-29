package behavioral

/**
 * Because such an implementation is somewhat similar to the Decorator‘s one,
 * we may use abstract override functionality to achieve the goal.
 * However, Scala provides an even more straightforward approach, that is based on partial functions
 */
object ChainOfResponsibility {

  case class Event(source: String)

  type EventHandler = PartialFunction[Event, Unit]

  val defaultHandler: EventHandler = PartialFunction(e => println("unknown event " + e.source))

  val keyboardHandler: EventHandler = {
    case Event("keyboard") => println("Display on monitor a character")
  }

  def mouseHandler(delay: Int): EventHandler = {
    case Event("mouse") => println("Move the mouse with delay " + delay)
  }

  def main(args : Array[String]) {

    val keyEvent = Event("keyboard")
    val mouseEvent = Event("mouse")

    val eventHandler = keyboardHandler
      .orElse(mouseHandler(100))
      .orElse(defaultHandler)

    eventHandler(keyEvent)
    eventHandler(mouseEvent)
    eventHandler(Event("bla"))
  }
}
