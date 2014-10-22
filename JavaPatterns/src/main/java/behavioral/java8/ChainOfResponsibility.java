package behavioral.java8;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The chain of responsibility pattern decouples the sender of a request from its receiver,
 * by giving more than one object an opportunity to handle the request.
 * The request is processed by the chain until some object handles it.
 *
 * @author Daniel Leon
 */
@FunctionalInterface
interface Event {
    String getSource();
}

class Handler {
    private final Predicate<Event> canHandle;
    private final Consumer<Event> consumer;
    private Handler next;

    public Handler() {
        this.canHandle = event -> true;
        this.consumer = event -> System.out.println("Undefined event : ' " + event.getSource() + " ' ");
        this.next = null;
    }

    public Handler(Predicate<Event> canHandle, Consumer<Event> consumer) {

        this.canHandle = canHandle;
        this.consumer = consumer;
    }

    void setNext(Handler handler) { next = handler; }

    void doHandle(Event event) {
        if (canHandle.test(event)) {
            consumer.accept(event);
        }
        else {
            next.doHandle(event);
        }
    }
}

public class ChainOfResponsibility {

    public static void main(String[] args) {

        Handler mouseHandler = new Handler(
                event -> event.getSource().equals("mouse"),
                event -> System.out.println("Mouse handler in action !")
        );
        Handler keyboardHandler = new Handler(
                event -> event.getSource().equals("keyboard"),
                event -> System.out.println("Keyboard handler in action !")
        );

        mouseHandler.setNext(keyboardHandler);
        keyboardHandler.setNext(new Handler());

        mouseHandler.doHandle(() -> "keyboard");
        mouseHandler.doHandle(() -> "mouse");
        mouseHandler.doHandle(() -> "undefined");

        // second method that handles the event in all the handlers one by one
        System.out.println("\nSecond method");
        Consumer<Event> keyboardConsumer = event -> System.out.println("Working with the keyboard handler");
        Consumer<Event> mouseConsumer = event -> System.out.println("Working with the mouse event handler");
        Consumer<Event> undefinedConsumer = event -> System.out.println("No No senior no esta");

        keyboardConsumer.andThen(mouseConsumer).andThen( undefinedConsumer).accept(() -> "keyboard");
        keyboardConsumer.andThen(mouseConsumer).andThen( undefinedConsumer).accept(() -> "mouse");

    }
}
