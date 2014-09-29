package creational.java7;

/**
 * The factory method pattern provides an interface for creating an object that encapsulates the actual class instantiation in a method,
 * and lets subclasses decide which class to instantiate.
 */
interface Animal {
    void speak();
}

class AnimalFactory {

    private static AnimalFactory instance = null;

    private AnimalFactory() {
    }

    public static AnimalFactory getInstance() {
        if (instance == null) {
            instance = new AnimalFactory();
        }
        return instance;
    }

    private class Dog implements Animal {
        @Override
        public void speak() {
            System.out.println("Hello, I'm a Java dog!");
        }
    }

    private class Cat implements Animal {
        @Override
        public void speak() {
            System.out.println("Java Miau");
        }
    }


    public Animal createAnimal(String kind) {
        if ("cat".equals(kind)) return new Cat();
        if ("dog".equals(kind)) return new Dog();
        throw new IllegalArgumentException();
    }

}
public class Factory {

    public static void main(String[] args) {
        Animal dog = AnimalFactory.getInstance().createAnimal("dog");
        Animal cat = AnimalFactory.getInstance().createAnimal("cat");

        dog.speak();
        cat.speak();

    }
}
