package structural;

import java.util.logging.Level;

/**
 * * The adapter pattern converts interface of a class into expected interface,
 * allowing classes with incompatible interfaces to work together.
 */
interface Log {
    void warning(String message);
    void error(String message);
}

final class Logger {
    void log(Level level, String message) {
        System.out.printf("Logging message %s\n", message);
    }
}

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
        log.error("error message in java");
        log.warning("warning message in java");
    }

}
