package behavioral.java8;

import java.util.function.BinaryOperator;

/**
 * The strategy pattern defines a family of encapsulated algorithms and lets the algorithm vary independently from clients that use it.
 * The pattern is handy when we need to select an algorithm at runtime.
 *
 *
 * This is the simple approach only one behavior is needed in the strategy object;
 * For a more complex strategy where more methods are required, we need a class to contain all the functions
 * that must be injected in the constructor.
 * The advantage is that one object contains endpoints for behaviors that are injected at the object creation
 * and we don't need to create implementation of the service, unless there is a complex function unfit for a lambda
 *
 * @author Daniel Leon
 */
class Context  {
    private final BinaryOperator<Integer> strategy;

    public Context(BinaryOperator<Integer> strategy) { this.strategy = strategy; }

    public void use(int a, int b) {
        System.out.println("The result of operation : " + strategy.apply(a, b));
    }
}

public class StrategyPattern {

    public static void main(String[] args) {

        BinaryOperator<Integer> complexOperation = (a, b) -> a * b + a - b;

        new Context(complexOperation).use(2, 3);
        new Context((a, b) ->  a + b).use(2, 3);
        new Context((a, b) ->  a * b).use(2, 3);
    }
}
