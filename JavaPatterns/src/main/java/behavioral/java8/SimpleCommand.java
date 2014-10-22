package behavioral.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * The command object and its concrete implementations can be reduced to a set of consumer functions in Java 8
 * for the simple command pattern where the command has only one method
 *
 * @author Daniel Leon
 */
public class SimpleCommand {

    class Cook {

        public void cookPizza() {
            System.out.println("Making a perfect pizza !");
        }

        public void cookSoup() {
            System.out.println("Making a perfect soup");
        }

    }

    class Waiter {
        private List<Consumer<Cook>> orders = new ArrayList<>();

        public void serve(Consumer<Cook> order, Cook cook) {
            orders.add(order);
            order.accept(cook);
        }

        public int getOrderNumber() {
            return orders.size();
        }
    }

    public static void main(String[] args) {
        SimpleCommand command = new SimpleCommand();
        Cook chef = command.new Cook();
        Waiter Giovanni = command.new Waiter();

        Giovanni.serve(Cook::cookPizza, chef);
        Giovanni.serve(Cook::cookSoup, chef);

        System.out.println("Number of orders : " + Giovanni.getOrderNumber());
    }
}
