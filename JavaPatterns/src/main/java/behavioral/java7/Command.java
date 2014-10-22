package behavioral.java7;

import java.util.ArrayList;
import java.util.List;

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * This is the extended example of command pattern, where the invoker has more than one method, as is usually
 * implemented with a do and undo method
 *
 * @author Daniel Leon
 */
public class Command {

    //Command interface
    interface Order
    {
        void execute();
        void cancel();
    }

    //Command implementation
    class PizzaOrder implements Order {

        private Cook cook;

        PizzaOrder(Cook cook) {
            this.cook = cook;
        }

        @Override
        public void execute() {
            cook.cookPizza();
        }

        @Override
        public void cancel() {
            cook.cancelPizza();
        }
    }

    //Command implementation
    class SoupOrder implements Order{

        private Cook cook;

        SoupOrder(Cook cook) {
            this.cook = cook;
        }

        @Override
        public void execute() {
            cook.cookSoup();
        }

        @Override
        public void cancel() {
            cook.cancelSoup();
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
            System.out.println("Making a perfect soup");
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
        Order pizza = command.new PizzaOrder(chef);
        Order soup = command.new SoupOrder(chef);
        Waiter Giovanni = command.new Waiter();

        Giovanni.serve(pizza);
        Giovanni.serve(soup);
        Giovanni.cancel(soup);
        System.out.println("Number of orders : " + Giovanni.getOrderNumber());
    }
}
