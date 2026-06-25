package OOP;

public class AbstractClassesDemo {

    static void main(String[] args) {

        // USING ABSTRACT CLASS

        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);

        // Using abstract methods
        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Circle perimeter: " + circle.calculatePerimeter());
        circle.displayInfo();

        System.out.println("Rectangle area: " + rectangle.calculatePerimeter());
        System.out.println("Rectangle perimeter: " + rectangle.calculatePerimeter());

        // POLYMORPHISM WITH ABSTRACT

        Shape[] shapes = {
                new Circle(3.0),
                new Rectangle(2.0, 5.0),
                new Circle(4.0)
        };

        for (Shape s : shapes) {
            System.out.println("Area: " + s.calculateArea());
            s.displayInfo();
            System.out.println();
        }

        // TEMPLATE METHOD PATTERN
        Game game1 = new Chess();
        Game game2 = new Soccer();

        System.out.println("Chess game: ");
        game1.play();

        System.out.println("\nSoccer game:");
        game2.play();
    }
}

// ABSTRACT CLASS
abstract class Shape {

    protected String color;

    public Shape() {
        this.color = "unknown";
    }

    public Shape(String color) {
        this.color = color;
    }

    // Abstract methods - must be implemented by subclass
    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    // Concrete methods - can be used by all subclasses
    public void displayInfo() {
        System.out.println("Shape color: " + color);
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
    }
}

// CONCRETE SUBCLASS

class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
        this.color = "red";
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.color = "blue";
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

// TEMPLATE METHOD PATTERN

abstract class Game {

    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    protected abstract void initialize();
    protected abstract void startPlay();
    protected abstract void endPlay();
}

class Chess extends Game {

    @Override
    protected void initialize() {
        System.out.println("Chess: Setting up board");
    }

    @Override
    protected void startPlay() {
        System.out.println("Chess: Starting game");
    }

    @Override
    protected void endPlay() {
        System.out.println("Chess: Game ended");
    }
}

class Soccer extends Game {

    @Override
    protected void initialize() {
        System.out.println("Soccer: Preparing field");
    }

    @Override
    protected void startPlay() {
        System.out.println("Soccer: Kickoff");
    }

    @Override
    protected void endPlay() {
        System.out.println("Soccer: Final whistle");
    }
}
