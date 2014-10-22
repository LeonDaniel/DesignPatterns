package creational.java8;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * My idea of factory in Java 8
 * (work in progress)
 *
 * @author Daniel Leon
 */
interface Animal {
    default void speak() {
        System.out.println("What ?");
    }

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
        Pair<Predicate<String>, Supplier<Animal>> newPair = new Pair<>(name, supplier);
        list.add(newPair);
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
        AnimalFactory.addAnimal("dog"::equals, Dog::new);
//        AnimalFactory.addAnimal("cat"::equals, Cat::new);

        AnimalFactory.createAnimal("dog").speak();
        AnimalFactory.createAnimal("cat").speak();

        AnimalFactory.addAnimal("cat"::equals, Cat::new);
        AnimalFactory.createAnimal("cat").speak();
    }
}
