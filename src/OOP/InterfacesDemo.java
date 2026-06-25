package OOP;

public class InterfacesDemo {

    public static void main(String[] args) {

        // BASIC INTERFACE USAGE
        Flyable bird = new Sparrow();
        bird.fly();
        bird.makeSound();

        // MULTIPLE INTERFACES
        Duck duck = new Duck();
        duck.fly();
        duck.swim();

        // INTERFACE POLYMORPHISM
        Flyable flyingObject = new Sparrow();
        flyingObject.fly();

        Swimmable swimmingObject = new Duck();
        swimmingObject.swim();

        // DEFAULT METHODS
        Flyable bird2 = new Sparrow();
        bird2.fly();
        bird2.glide();

        // STATIC METHODS
        Flyable.printFlyingInfo();

        // FUNCTIONAL INTERFACES
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        Calculator subtract = (a, b) -> a - b;

        System.out.println("Add: " + add.calculate(5, 3));
        System.out.println("Multiply: " + multiply.calculate(5, 3));

        // MARKER INTERFACES
        MarkerClass markerObject = new MarkerClass();
        if (markerObject instanceof MarkerInterface) {
            System.out.println("Object implements MarkerInterface");
        }
    }
}

// BASIC INTERFACE

interface Flyable {
    // All methods are implicitly public and abstract
    void fly();

    void makeSound();

    default void glide() {
        System.out.println("Gliding through the air");
    }

    static void printFlyingInfo() {
        System.out.println("Flying objects can move through the air");
    }
}

interface Swimmable {
    void swim();
}

// IMPLEMENTING INTERFACE
class Sparrow implements Flyable {

    @Override
    public void fly() {
        System.out.println("Sparrow flying low and fast");
    }

    @Override
    public void makeSound() {
        System.out.println("Sparrow chirping: Chirp chirp!");
    }
}

// IMPLEMENTING MULTIPLE INTERFACES
class Duck implements Flyable, Swimmable {

    @Override
    public void fly() {
        System.out.println("Duck flying awkwardly");
    }

    @Override
    public void makeSound() {
        System.out.println("Duck quacking: Quack quack!");
    }

    @Override
    public void swim() {
        System.out.println("Duck swimming gracefully");
    }
}

// FUNCTIONAL INTERFACE
// @FunctionalInterface annotation ensures only one abstract method

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    // int anotherMethod(); // Error - only one abstract method allowed
}

// MARKER INTERFACE

interface MarkerInterface {
    // No methods - just marks the class
}

class MarkerClass implements MarkerInterface {
    // Implementation
}