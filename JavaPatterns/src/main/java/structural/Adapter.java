package structural;

import java.util.logging.Level;

/**
 * The adapter pattern converts interface of a class into expected interface,
 * allowing classes with incompatible interfaces to work together through inheritance.
 *
 * @author Daniel Leon
 */
//Adaptor
interface Log {
    void warning(String message);
    void error(String message);
}

//Adaptee
final class Logger {
    void log(Level level, String message) {
        System.out.printf("[%s] Logging message %s\n", level, message);
    }
}

//Adapter
class LoggerToLogAdapter implements Log {
    private final Logger logger;

    public LoggerToLogAdapter(Logger logger) { this.logger = logger; }

    public void warning(String message) {
        logger.log(Level.WARNING, message);
    }

    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}

public class Adapter {

    public static void main(String[] args) {

        Log log = new LoggerToLogAdapter(new Logger());

        log.error("Error message in Java");
        log.warning("Warning message in java");
    }
}
