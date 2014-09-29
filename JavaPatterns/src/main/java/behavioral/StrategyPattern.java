package behavioral;

/**
 * The strategy pattern defines a family of encapsulated algorithms and lets the algorithm vary independently from clients that use it.
 The pattern is handy when we need to select an algorithm at runtime.
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
        new Context((new Add())).use(2, 3);

        //java 8 style
        System.out.println("Java 8 style");
        new Context( (a, b) -> a + b).use(2, 3);
    }
}
