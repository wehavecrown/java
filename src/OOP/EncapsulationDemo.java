package OOP;

public class EncapsulationDemo {

    public static void main(String[] args) {

        // BASIC ENCAPSULATION

        Person person = new Person("Alice", 25);

        // Access through getters
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // Modify through setters
        person.setAge(26);
        System.out.println("Updated age: " + person.getAge());

        // DATA VALIDATION
        // Encapsulation allows validation in setters
        person.setAge(-5); // will reject invalid age
        System.out.println("Age after invalid set: " + person.getAge());

        // IMMUTABLE CLASSES
        ImmutablePoint point = new ImmutablePoint(3, 4);
        System.out.println("Point: x=" + point.getX() + ", y=" + point.getY());

        // ACCESS MODIFIERS SHOWCASE
        AccessExample example = new AccessExample();
        example.publicMethod();
        example.protectedMethod();
        example.defaultMethod();
    }
}

//  ENCAPSULATED CLASS

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter provide controlled access
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters allow controlled modification
    public void setAge(int age) {

        if (age >= 0 && age < 150) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Must be between 0 and 150");
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name. Cannot be empty.");
        }
    }
}

// IMMUTABLE CLASS

final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Only getters, no setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImmutablePoint moveX(int delta) {
        return new ImmutablePoint(x + delta, y);
    }
}

    // ACCESS MODIFIERS SHOWCASE
    class AccessExample {
        // Private - only accessible within this class
        private int privateField = 10;

        // Default (package-private) - accessible within same package
        int defaultField = 20;

        // Protected - accessible in same package or subclasses
        protected int protectedField = 30;

        // Public - accessible from anywhere
        public int publicField = 40;

        private void privateMethod() {
            System.out.println("Private method");
        }

        void defaultMethod() {
            System.out.println("Default (package-private) method");
        }

        protected void protectedMethod(){
            System.out.println("Protected method");
        }

        public void publicMethod() {
            System.out.println("Public method");
            System.out.println("Private field: " + privateField);
            privateMethod();
        }
    }