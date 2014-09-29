package creational.java8;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * My idea of factory in Java 8
 */
interface Animal {
    default void speak() {
        System.out.println("What ?");
    };

    default String getName() {
        return "Animal";
    }
}

class baseAnimal implements Animal {}

class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Hello, I'm a Java dog!");
    }

    @Override
    public String getName() {
        return "Dog";
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Java Miau");
    }

    @Override
    public String getName() {
        return "Cat";
    }
}

class AnimalFactory {

    private static List<Pair<Predicate<String>, Supplier<Animal>>> list = new LinkedList<>();

    public static void addAnimal(Predicate<String> name, Supplier<Animal> supplier) {
        list.add(new Pair(name, supplier));
    }

    public static Animal createAnimal(String name) {
         Optional<Animal> a = list.stream()
                .filter( set -> set.getKey().test(name))
                .findFirst().map( set -> set.getValue().get());

        return a.orElse(new baseAnimal());
    }
}

public class Factory {

    public static void main(String[] args) {
        AnimalFactory.addAnimal( name -> "dog".equals(name), () -> new Dog());
//        AnimalFactory.addAnimal( name -> "cat".equals(name), () -> new Cat());

        AnimalFactory.createAnimal("dog").speak();
        AnimalFactory.createAnimal("cat").speak();

        AnimalFactory.addAnimal( name -> "cat".equals(name), () -> new Cat());
        AnimalFactory.createAnimal("cat").speak();
    }
}
