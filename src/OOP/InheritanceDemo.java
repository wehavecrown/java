package OOP;

public class InheritanceDemo {

    static void main(String[] args) {

        //BASIC INHERITANCE
        Dog dog = new Dog("Buddy", 3, "Golden Retriver");
        dog.eat(); // inherited from Animal
        dog.sleep(); // inherited from Animal
        dog.bark(); // Dog's own method

        // POLYMORPHISM
        // parent reference can refer to child object
        Animal animal = new Dog("Max", 2, "German Sherperd");
        animal.eat(); // calls Dog's overridden eat() method
        animal.sleep(); // calls Dog's overridden sleep() method

        // INSTANCEOF OPERATOR
        if (animal instanceof Dog) {
            ((Dog) animal).bark(); // Cast to Dog to call bark()
        }

        // ACCESSING SUPERCLASS
        Dog anotherDog = new Dog("Charlie", 1, "Poodle");
        anotherDog.showInfo(); // Calls method that uses super

        // COMPOSITION VS INHERITANCE
        // Composition (has-a relationship)
        Car car = new Car("Toyota", "Camry");
        car.drive(); // Car uses Engine through composition
    }
}

// PARENT CLASS
class Animal {

    protected String name; // accessible in subclasses
    private int age; // Not accessible in subclasses

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    public void showInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// CHILD CLASS
class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age); // call parent constructor
        this.breed = breed;
    }

    // Method overriding - providing specific implementation
    @Override
    public void eat() {
        super.eat(); // optional call parent mehtod
        System.out.println(name + " the dog is eating dog food");
    }

    @Override
    public void sleep() {
        System.out.println(name + " the dog is sleeping in its dog bed");
    }

    // Additional method specific to Dog
    public void bark() {
        System.out.println(name + " ia barking: Woof! Woof!");
    }

    @Override
    public void showInfo() {
        super.showInfo(); // call parent showInfo
        System.out.println("Breed: " + breed);
    }
}

// COMPOSITION EXAMPLE

class Engine {

    private String type = "V6";

    public void start() {
        System.out.println("Engine starting...");
    }
}

class Car {

    private String make;
    private String model;
    private Engine engine; // Composition - Car HAS-A Engine

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        this.engine = new Engine();
    }

    public void drive() {
        engine.start();
        System.out.println(make + " " + model + " is driving");
    }
}
