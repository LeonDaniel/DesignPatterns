package behavioral.java7;

/**
 * The strategy pattern defines a family of encapsulated algorithms and lets the algorithm vary independently from clients that use it.
 * The pattern is handy when we need to select an algorithm at runtime.
 *
 * @author Daniel Leon
 */
interface Strategy {
    int compute(int a, int b);
}

class Add implements Strategy {
    public int compute(int a, int b) { return a + b; }
}

class Multiply implements Strategy {
    public int compute(int a, int b) { return a * b; }
}

class ComplexStuff implements Strategy {

    @Override
    public int compute(int a, int b) {
        return a * b + a - b;
    }
}

class Context  {
    private final Strategy strategy;

    public Context(Strategy strategy) { this.strategy = strategy; }

    public void use(int a, int b) {
        System.out.println("The result of operation : " + strategy.compute(a, b));
    }
}

public class StrategyPattern {

    public static void main(String[] args) {

        new Context(new Multiply()).use(2, 3);
        new Context(new Add()).use(2, 3);
        new Context(new ComplexStuff()).use(2, 3);
    }
}
