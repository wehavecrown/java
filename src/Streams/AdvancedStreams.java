package Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Demonstrates advanced and modern Java stream features.
 * Includes Java 8-21 features.
 */
public class AdvancedStreams {
    static class Product {
        String name;
        String category;
        double price;
        int quantity;

        Product(String name, String category, double price, int quantity) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }

        @Override
        public String toString() {
            return String.format("%s (%s) $%.2f x%d", name, category, price, quantity);
        }
    }

    public static void main(String[] args) {
        // ============ ADVANCED GROUPING ============
        System.out.println("=== ADVANCED GROUPING ===");

        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 999.99, 10),
                new Product("Phone", "Electronics", 599.99, 20),
                new Product("Tablet", "Electronics", 399.99, 15),
                new Product("Shirt", "Clothing", 29.99, 50),
                new Product("Pants", "Clothing", 49.99, 30),
                new Product("Shoes", "Clothing", 79.99, 25),
                new Product("Book", "Books", 19.99, 100),
                new Product("Notebook", "Books", 9.99, 200)
        );

        // Group by category with sum of quantities
        Map<String, Integer> totalQuantityByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingInt(Product::getQuantity)
                ));
        System.out.println("Total quantity by category: " + totalQuantityByCategory);

        // Group by category with min price
        Map<String, Optional<Product>> cheapestByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.minBy(Comparator.comparingDouble(Product::getPrice))
                ));
        System.out.println("Cheapest by category: " + cheapestByCategory);

        // Group by category with average price
        Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));
        System.out.println("Average price by category: " + avgPriceByCategory);

        // ============ MULTI-LEVEL GROUPING ============
        System.out.println("\n=== MULTI-LEVEL GROUPING ===");

        // Group by category, then by price range
        Map<String, Map<String, List<Product>>> grouped = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.groupingBy(p ->
                                p.getPrice() < 50 ? "Budget" : "Premium"
                        )
                ));
        System.out.println("Multi-level grouping:");
        grouped.forEach((category, priceGroups) -> {
            System.out.println(category + ":");
            priceGroups.forEach((priceRange, items) ->
                    System.out.println("  " + priceRange + ": " + items.size() + " items")
            );
        });

        // ============ COMPLEX COLLECTORS ============
        System.out.println("\n=== COMPLEX COLLECTORS ===");

        // Category statistics
        Map<String, DoubleSummaryStatistics> statsByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summarizingDouble(Product::getPrice)
                ));
        statsByCategory.forEach((category, stats) ->
                System.out.println(category + ": " + stats)
        );

        // Collect to map with complex keys
        Map<String, String> categoryProducts = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(
                                Product::getName,
                                Collectors.joining(", ")
                        )
                ));
        System.out.println("Category products: " + categoryProducts);

        // ============ STREAMS WITH OPTIONALS ============
        System.out.println("\n=== STREAMS WITH OPTIONALS ===");

        List<Optional<String>> optionalList = Arrays.asList(
                Optional.of("Hello"),
                Optional.empty(),
                Optional.of("World"),
                Optional.of("Java"),
                Optional.empty()
        );

        // Filter empty Optionals
        List<String> presentValues = optionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Present values: " + presentValues);

        // Using flatMap with Optionals
        List<String> flatMapped = optionalList.stream()
                .flatMap(Optional::stream)  // Java 9+
                .collect(Collectors.toList());
        System.out.println("Flat mapped: " + flatMapped);

        // ============ TAKEWHILE/DROPWHILE (Java 9+) ============
        System.out.println("\n=== TAKEWHILE/DROPWHILE (Java 9+) ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Take while less than 6
        List<Integer> taken = numbers.stream()
                .takeWhile(n -> n < 6)
                .collect(Collectors.toList());
        System.out.println("Take while < 6: " + taken);

        // Drop while less than 6
        List<Integer> dropped = numbers.stream()
                .dropWhile(n -> n < 6)
                .collect(Collectors.toList());
        System.out.println("Drop while < 6: " + dropped);

        // ============ PREDICATE NOT (Java 11+) ============
        System.out.println("\n=== PREDICATE NOT (Java 11+) ===");

        List<String> words = Arrays.asList("apple", "", "banana", "", "cherry", null);

        // Filter non-empty strings
        List<String> nonEmpty = words.stream()
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        System.out.println("Non-empty strings: " + nonEmpty);

        // ============ STREAM TO LIST (Java 16+) ============
        System.out.println("\n=== STREAM TO LIST (Java 16+) ===");

        // toList() returns immutable list
        List<String> immutableList = words.stream()
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .map(String::toUpperCase)
                .toList();  // Java 16+
        System.out.println("Immutable list: " + immutableList);
        // immutableList.add("Test"); // UnsupportedOperationException

        // ============ COLLECTORS.TOFILTERING (Java 9+) ============
        System.out.println("\n=== COLLECTORS.TOFILTERING (Java 9+) ===");

        // Collect only expensive products per category
        Map<String, List<Product>> expensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.filtering(
                                p -> p.getPrice() > 500,
                                Collectors.toList()
                        )
                ));
        System.out.println("Expensive products by category: " + expensiveByCategory);

        // ============ COLLECTORS.FLATMAPPING (Java 9+) ============
        System.out.println("\n=== COLLECTORS.FLATMAPPING (Java 9+) ===");

        // Get all product names by category
        Map<String, List<String>> namesByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(
                                Product::getName,
                                Collectors.toList()
                        )
                ));
        System.out.println("Names by category: " + namesByCategory);

        // ============ PERFORMANCE TIPS ============
        System.out.println("\n=== PERFORMANCE TIPS ===");

        // Lazy evaluation
        System.out.println("Lazy evaluation demonstration:");
        Stream<Integer> lazyStream = Stream.of(1, 2, 3, 4, 5)
                .peek(x -> System.out.println("Source: " + x))
                .filter(x -> {
                    System.out.println("Filtering: " + x);
                    return x % 2 == 0;
                })
                .map(x -> {
                    System.out.println("Mapping: " + x);
                    return x * x;
                });
        System.out.println("Stream created - nothing processed yet");
        System.out.println("Now terminal operation:");
        lazyStream.findFirst().ifPresent(System.out::println);

        // Short-circuiting
        System.out.println("\nShort-circuiting:");
        long count = Stream.iterate(1, n -> n + 1)
                .limit(10)
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Count: " + count);

        // ============ COMPARATOR OPERATIONS ============
        System.out.println("\n=== COMPARATOR OPERATIONS ===");

        // Get top 3 most expensive products
        List<Product> topExpensive = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 most expensive: " + topExpensive);

        // Get least expensive per category
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.minBy(Comparator.comparingDouble(Product::getPrice))
                ))
                .forEach((category, product) ->
                        System.out.println("Cheapest " + category + ": " + product.orElse(null))
                );

        // ============ CUSTOM STREAM OPERATIONS ============
        System.out.println("\n=== CUSTOM STREAM OPERATIONS ===");

        // Custom collector for total inventory value
        double totalValue = products.stream()
                .collect(Collectors.summingDouble(p -> p.getPrice() * p.getQuantity()));
        System.out.println("Total inventory value: $" + totalValue);

        // Weighted average price by quantity
        double weightedAvg = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum() / products.stream().mapToInt(Product::getQuantity).sum();
        System.out.println("Weighted average price: $" + weightedAvg);
    }
}