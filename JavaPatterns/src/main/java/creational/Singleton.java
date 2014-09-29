package creational;

/**
 * The singleton pattern restricts the instantiation of a class to one object, and provides a global point of access to it.
 Singleton is probably the most well-known design pattern in Java. It is a clear sign of the missing language feature.
 */
public class Singleton {

    public static class Cat implements Runnable {
        private static final Cat instance = new Cat();

        private Cat() {}

        public void run() {
            System.out.println("I'm a java cat running around !");
            // do nothing
        }

        public static Cat getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        Cat.getInstance().run();
    }

}
