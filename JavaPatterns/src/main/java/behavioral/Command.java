package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 The command is useful to delay, sequence or log method calls.
 */

class PrintCommand implements Runnable {
    private final String s;

    PrintCommand(String s) { this.s = s; }

    public void run() {
        System.out.println(s);
    }
}

class Invoker {
    private final List<Runnable> history = new ArrayList<>();

    void invoke(Runnable command) {
        command.run();
        history.add(command);
    }
}

public class Command {

    public static  void main(String [] args) {
        Invoker invoker = new Invoker();
        invoker.invoke(new PrintCommand("foo"));
        invoker.invoke(new PrintCommand("bar"));
    }

}
