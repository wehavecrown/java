package LambdaExpressions;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Demonstrates functional interfaces - interfaces with a single abstract method.
 * Lambda expressions can be used to implement functional interfaces.
 */

public class FunctionalInterfacesDemo {

    public static void main(String[] args) {
        // ============ PREDICATE<T> ============
        // Takes one argument, returns boolean
        System.out.println("=== PREDICATE ===");

        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Is '' empty? " + isEmpty.test(""));

        // Predicate chaining
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("Is -2 even and positive? " + isEvenAndPositive.test(-2));

        // ============ FUNCTION<T,R> ============
        // Takes one argument, returns a result
        System.out.println("\n=== FUNCTION ===");

        Function<String, Integer> length = String::length;
        System.out.println("Length of 'Hello': " + length.apply("Hello"));

        Function<Integer, String> asString = Object::toString;
        System.out.println("42 as string: " + asString.apply(42));

        // Function chaining
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> reverse = s -> new StringBuilder(s).reverse().toString();
        Function<String, String> upperAndReverse = toUpperCase.andThen(reverse);
        System.out.println("Upper then reverse 'hello': " + upperAndReverse.apply("hello"));

        // ============ CONSUMER<T> ============
        // Takes one argument, no return
        System.out.println("\n=== CONSUMER ===");

        Consumer<String> print = System.out::println;
        print.accept("Hello Consumer!");

        Consumer<String> log = s -> System.out.println("LOG: " + s);
        Consumer<String> printAndLog = print.andThen(log);
        printAndLog.accept("Processing item");

        // ============ SUPPLIER<T> ============
        // No arguments, returns a value
        System.out.println("\n=== SUPPLIER ===");

        Supplier<Date> currentDate = Date::new;
        System.out.println("Current date: " + currentDate.get());

        Supplier<Double> random = Math::random;
        System.out.println("Random: " + random.get());

        // ============ UNARYOPERATOR<T> ============
        // Function with same type for input and output
        System.out.println("\n=== UNARYOPERATOR ===");

        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 5: " + square.apply(5));

        UnaryOperator<String> quote = s -> "\"" + s + "\"";
        System.out.println("Quoted: " + quote.apply("Hello"));

        // ============ BINARYOPERATOR<T> ============
        // Takes two arguments, returns result of same type
        System.out.println("\n=== BINARYOPERATOR ===");

        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("Sum: " + sum.apply(5, 3));

        BinaryOperator<String> concatenate = (a, b) -> a + b;
        System.out.println("Concatenated: " + concatenate.apply("Hello ", "World"));

        // ============ CUSTOM FUNCTIONAL INTERFACE ============
        System.out.println("\n=== CUSTOM FUNCTIONAL INTERFACE ===");

        // Using custom functional interface
        StringProcessor toUpperCaseProcessor = s -> s.toUpperCase();
        StringProcessor reverseProcessor = s -> new StringBuilder(s).reverse().toString();

        System.out.println("Upper case: " + toUpperCaseProcessor.process("hello"));
        System.out.println("Reversed: " + reverseProcessor.process("hello"));

        // ============ REAL-WORLD EXAMPLE ============
        System.out.println("\n=== REAL-WORLD EXAMPLE ===");

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 50000),
                new Employee("Bob", 60000),
                new Employee("Charlie", 45000),
                new Employee("David", 70000)
        );

        // Using functional interfaces in stream operations
        // Filter (Predicate), Map (Function), ForEach (Consumer)
        employees.stream()
                .filter(e -> e.getSalary() > 50000)           // Predicate
                .map(Employee::getName)                       // Function
                .map(String::toUpperCase)                    // Function
                .forEach(System.out::println);                // Consumer

        // ============ BIFUNCTION ============
        BiFunction<Integer, Integer, Integer> max = Math::max;
        System.out.println("Max of 5 and 3: " + max.apply(5, 3));

        // ============ BIPREDICATE ============
        BiPredicate<String, String> startsWith = (s, prefix) -> s.startsWith(prefix);
        System.out.println("'Hello' starts with 'He'? " + startsWith.test("Hello", "He"));
    }

    // ============ CUSTOM FUNCTIONAL INTERFACE ============
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
        // Only one abstract method allowed
        // String process2(); // Error

        // Default methods allowed
        default String processWithDefault(String input) {
            return "Processed: " + input;
        }
    }
}

// ============ HELPER CLASS FOR EXAMPLES ============
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}