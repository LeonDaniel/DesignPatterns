package behavioral

/**
 * The chain of responsibility pattern decouples the sender of a request from its receiver,
 * by giving more than one object an opportunity to handle the request.
 * The request is processed by the chain until some object handles it.
 *
 * Scala provides a straightforward approach, based on partial functions
 * that are executed only if the argument matches a certain condition
 *
 * @author Daniel Leon
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
