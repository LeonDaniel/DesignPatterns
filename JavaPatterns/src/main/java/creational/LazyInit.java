package creational;

/**
 * Lazy initialization is a special case of lazy evaluation strategy. It’s a technique that initializes a value (or an object) on its first access.
 Lazy initialization allows to defer (or avoid) some expensive computation.
 A typical Java implementation uses null value to indicate uninitialized state. However, if null is a valid final value,
 then a separate flag is needed to indicate whether the initialization process has taken place.
 */
public class LazyInit {

    public class Component {
        public Component() {
            System.out.print("(computing x in java) ");
            System.out.print(42);
        }
    }
    private volatile Component component;

    public Component getComponent() {
        Component result = component;
        if (result == null) {
            synchronized(this) {
                result = component;
                if (result == null) {
                    component = result = new Component();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LazyInit init = new LazyInit();

        System.out.print("x = ");
        init.getComponent();
    }
}
