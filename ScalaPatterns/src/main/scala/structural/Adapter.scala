package structural

import java.util.logging.Level

/**
 * In Scala, we have a built-in concept of interface adapters, expressed as implicit classes.
 * When expected type of expression is Log, yet a Logger instance is used, Scala compiler will automatically wrap that instance in the adapter class.
 *
 * @author Daniel Leon
 */
object Adapter {

  trait Log {
    def warning(message: String)
    def error(message: String)
  }

  final class Logger {
    def log(level: Level, message: String) { printf("[%s] Logging message %s\n" , level, message) }
  }

  implicit class LoggerToLogAdapter(logger: Logger) extends Log {
    def warning(message: String) { logger.log(Level.WARNING, message) }
    def error(message: String) { logger.log(Level.SEVERE, message) }
  }

  def main(args:Array[String]) {
    val log: Log = new Logger()

    log.error("Error message in Scala")
    log.warning("Warning message in Scala")
  }
}
