package behavioral;

/**
 * Value object is a small immutable object that represents a simple entity whose equality isn’t based on identity. Value objects are equal if all their fields are equal.
 Value objects are widely used to represent numbers, dates, colors, etc. In enterprise applications they are used as DTOs a for inter-process communication. Because of the immutability,
 value objects are handy in multi-threaded programing.
 In Java, there’s no special syntax for value objects, so we have to define (and update) a constructor, getters and auxiliary methods explicitly
 */

class Point {
    private final int x, y;

    public Point(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }

    public int getY() { return y; }

    public boolean equals(Object o) {
        Point that = (Point) o;
        return x == that.x && y == that.y;
    }

    public int hashCode() {
        return 31 * x + y;
    }

    public String toString() {
        return String.format("Point(%d, %d)", x, y);
    }
}

public class Value {

    public static void main(String[] args) {

        Point point = new Point(1, 2);
        Point point1 = new Point(1, 2);
        System.out.println(point.equals(point1) ? "The points are the same" : "The points are different");
    }

}
