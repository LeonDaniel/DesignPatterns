package behavioral.java7;

/**
 * The chain of responsibility pattern decouples the sender of a request from its receiver,
 * by giving more than one object an opportunity to handle the request.
 * The request is processed by the chain until some object handles it.
 *
 * @author Daniel Leon
 */
interface Event {
    String getSource();
}

class KeyboardEvent implements Event {

    @Override
    public String getSource() {
        return "keyboard";
    }
}

class MouseEvent implements Event {

    @Override
    public String getSource() {
        return "mouse";
    }
}

abstract class EventHandler {
    private EventHandler next;

    void setNext(EventHandler handler) { next = handler; }

    public void handle(Event event) {
        if (canHandle(event)) doHandle(event);
        else if (next != null) next.handle(event);
    }

    abstract protected boolean canHandle(Event event);
    abstract protected void doHandle(Event event);
}

class KeyboardHandler extends EventHandler {

    @Override
    protected boolean canHandle(Event event) {
        return "keyboard".equals(event.getSource());
    }

    @Override
    protected void doHandle(Event event) {
        System.out.println("Display on monitor a character");
    }
}

class MouseHandler extends EventHandler {

    @Override
    protected boolean canHandle(Event event) {
        return "mouse".equals(event.getSource());
    }

    @Override
    protected void doHandle(Event event) {
        System.out.println("Move the mouse");
    }
}

class UndefinedHandler extends EventHandler {

    @Override
    protected boolean canHandle(Event event) {
        return true;
    }

    @Override
    protected void doHandle(Event event) {
        System.out.println("Unknown  event " + event.getSource());
    }
}

public class ChainOfResponsibility {

    public static void main(String[] args) {

        Event keyboardEvent = new KeyboardEvent();
        Event mouseEvent = new MouseEvent();
        Event undefinedEvent = new Event() {
            @Override
            public String getSource() {
                return "undefined";
            }
        };

        KeyboardHandler handler = new KeyboardHandler();
        MouseHandler mouseHandler = new MouseHandler();
        UndefinedHandler undefinedHandler = new UndefinedHandler();

        handler.setNext(mouseHandler);
        mouseHandler.setNext(undefinedHandler);


        handler.handle(keyboardEvent);
        handler.handle(mouseEvent);
        handler.handle(undefinedEvent);
    }
}
