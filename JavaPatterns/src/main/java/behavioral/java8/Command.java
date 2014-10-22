package behavioral.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * This is the extended example of command pattern, where the invoker has more than one method, as is usually
 * implemented with a do and undo method. In java 8 , we can skip the creation of concrete commands by having only
 * the command object and the functionality imported as functions/consumers
 *
 * @author Daniel Leon
 */
public class Command {

    //Command
    class Order
    {
        private Consumer<Cook> doAction;
        private Consumer<Cook> undoAction;
        private Cook cook;

        public Order(Cook cook, Consumer<Cook> doAction, Consumer<Cook> undoAction)
        {
            this.cook = cook;
            this.doAction = doAction;
            this.undoAction = undoAction;
        }

        void execute()
        {
            doAction.accept(cook);
        }

        void cancel()
        {
            undoAction.accept(cook);
        }
    }

    //Receiver
    class Cook{

        public void cookPizza()
        {
            System.out.println("Making a perfect pizza !");
        }

        public void cookSoup()
        {
            System.out.println("Making a perfect soup !");
        }

        public void cancelPizza()
        {
            System.out.println("Great, now I have to eat pizza again tonight!");
        }

        public void cancelSoup()
        {
            System.out.println("Keeping this soup for my next customer");
        }
    }

    class Waiter {
        private List<Order> orders = new ArrayList<>();

        public void serve(Order order)
        {
            orders.add(order);
            order.execute();
        }

        public void cancel(Order order)
        {
            orders.remove(order);
            order.cancel();
        }

        public int getOrderNumber() {
            return orders.size();
        }
    }

    public static  void main(String [] args) {
        Command command = new Command();
        Cook chef = command.new Cook();
        Waiter Giovanni = command.new Waiter();
        Order pizza  = command.new Order(
                chef,
                Cook::cookPizza,
                Cook::cancelPizza
        );
        Order soup  = command.new Order(
                chef,
                Cook::cookSoup,
                Cook::cancelSoup
        );

        Giovanni.serve(pizza);
        Giovanni.serve(soup);
        Giovanni.cancel(soup);
        System.out.println("Number of orders : " + Giovanni.getOrderNumber());
    }
}
