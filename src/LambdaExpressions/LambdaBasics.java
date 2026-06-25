package LambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Demonstrates lambda expressions - functional programming in Java.
 * Lambdas provide concise way to implement functional interfaces.
 */
public class LambdaBasics {
    public static void main(String[] args) {
        // ============ BASIC LAMBDA SYNTAX ============
        System.out.println("=== BASIC LAMBDA SYNTAX ===");

        // 1. Without parameters
        Runnable noParams = () -> System.out.println("No parameters lambda");
        noParams.run();

        // 2. One parameter (parentheses optional)
        Consumer<String> oneParam = s -> System.out.println("Hello, " + s);
        oneParam.accept("World");

        // 3. Multiple parameters with return
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("Sum: " + add.apply(5, 3));

        // 4. Multiple statements (use braces)
        BinaryOperator<Integer> calculate = (a, b) -> {
            int sum = a + b;
            int product = a * b;
            System.out.println("Sum: " + sum);
            return product;
        };
        System.out.println("Product: " + calculate.apply(4, 2));

        // ============ LAMBDA WITH COLLECTIONS ============
        System.out.println("\n=== LAMBDA WITH COLLECTIONS ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Before Java 8 - traditional
        System.out.println("Traditional iteration:");
        for (String name : names) {
            System.out.println(name);
        }

        // With lambda
        System.out.println("Lambda iteration:");
        names.forEach(name -> System.out.println(name));

        // Method reference (shorter)
        System.out.println("Method reference iteration:");
        names.forEach(System.out::println);

        // ============ PREDICATE - FILTERING ============
        System.out.println("\n=== PREDICATE - FILTERING ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate to check if number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Filter numbers
        System.out.println("Even numbers:");
        numbers.stream()
                .filter(isEven)
                .forEach(System.out::println);

        // Combine predicates
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        System.out.println("Even numbers greater than 5:");
        numbers.stream()
                .filter(isEven.and(isGreaterThan5))
                .forEach(System.out::println);

        // ============ FUNCTION - TRANSFORMATION ============
        System.out.println("\n=== FUNCTION - TRANSFORMATION ===");

        // Convert String to Integer
        Function<String, Integer> strToInt = s -> Integer.parseInt(s);
        Integer result = strToInt.apply("123");
        System.out.println("Converted: " + result);

        // Chain functions
        Function<Integer, String> intToStr = n -> "Number: " + n;
        Function<String, Integer> parseThenFormat = strToInt.andThen(intToStr);
        String formatted = parseThenFormat.apply("456");
        System.out.println("Chained function: " + formatted);

        // ============ CONSUMER - SIDE EFFECTS ============
        System.out.println("\n=== CONSUMER - SIDE EFFECTS ===");

        // Consumer that prints and logs
        Consumer<String> logger = s -> {
            System.out.println("Consuming: " + s);
            System.out.println("Logging: " + s.toUpperCase());
        };
        logger.accept("Hello World");

        // ============ SUPPLIER - GENERATE DATA ============
        System.out.println("\n=== SUPPLIER - GENERATE DATA ===");

        Supplier<String> greeting = () -> "Hello from Supplier!";
        System.out.println(greeting.get());

        Supplier<Double> randomGenerator = () -> Math.random();
        System.out.println("Random value: " + randomGenerator.get());

        // ============ METHOD REFERENCES ============
        System.out.println("\n=== METHOD REFERENCES ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Static method reference
        words.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Instance method reference
        List<String> resultList = new ArrayList<>();
        words.stream()
                .map(String::toUpperCase)
                .forEach(resultList::add);  // Reference to instance method

        // Constructor reference
        Function<String, Integer> integerCreator = Integer::valueOf;
        System.out.println("Constructor reference: " + integerCreator.apply("789"));
    }
}
