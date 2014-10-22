package behavioral.java7;

import java.util.ArrayList;
import java.util.List;

/**
 * The command pattern is used to encapsulate all the information needed to call a method at a latter time.
 * This information includes the method name, the object that owns the method and values for the method parameters.
 * The command is useful to delay, sequence or log method calls.
 *
 * @author Daniel Leon
 */
//Command
interface Order {
    void execute();
}
//Command implementation 1
class PizzaOrder implements Order {

    private Cook cook;

    PizzaOrder(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookPizza();
    }
}

//Command implementation 2
class SoupOrder implements Order {

    private Cook cook;

    SoupOrder(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookSoup();
    }
}

//Receiver
class Cook {

    public void cookPizza() {
        System.out.println("Making a perfect pizza !");
    }

    public void cookSoup() {
        System.out.println("Making a perfect soup");
    }

}

//Invoker
class Waiter {
    private List<Order> orders = new ArrayList<>();

    public void serve(Order order) {
        orders.add(order);
        order.execute();
    }

    public int getOrderNumber() {
        return orders.size();
    }
}

//Client
public class SimpleCommand {

    public static void main(String[] args) {
        Cook chef = new Cook();
        Order pizza = new PizzaOrder(chef);
        Order soup = new SoupOrder(chef);

        Waiter Giovanni = new Waiter();

        Giovanni.serve(pizza);
        Giovanni.serve(soup);
        System.out.println("Number of orders : " + Giovanni.getOrderNumber());
    }
}
