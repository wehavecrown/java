package Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Demonstrates advanced stream operations.
 * Streams provide powerful operations for data processing.
 */

public class StreamOperations {
    static class Person {
        String name;
        int age;
        String city;
        double salary;

        Person(String name, int age, String city, double salary) {
            this.name = name;
            this.age = age;
            this.city = city;
            this.salary = salary;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return String.format("%s(%d, %s, $%.2f)", name, age, city, salary);
        }
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "New York", 70000),
                new Person("Bob", 30, "Chicago", 60000),
                new Person("Charlie", 35, "New York", 90000),
                new Person("David", 28, "Boston", 75000),
                new Person("Eve", 32, "Chicago", 85000),
                new Person("Frank", 40, "New York", 100000),
                new Person("Grace", 22, "Boston", 50000),
                new Person("Henry", 45, "Chicago", 110000)
        );

        // ============ COMPLEX FILTERING ============
        System.out.println("=== COMPLEX FILTERING ===");

        // Filter people with salary > 70000 and age < 40
        people.stream()
                .filter(p -> p.getSalary() > 70000)
                .filter(p -> p.getAge() < 40)
                .forEach(System.out::println);

        // ============ MAPPING AND TRANSFORMING ============
        System.out.println("\n=== MAPPING AND TRANSFORMING ===");

        // Extract names and convert to uppercase
        List<String> upperNames = people.stream()
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);

        // Create new objects
        List<Person> seniorPeople = people.stream()
                .filter(p -> p.getAge() >= 30)
                .map(p -> new Person(
                        "Senior " + p.getName(),
                        p.getAge(),
                        p.getCity(),
                        p.getSalary() * 1.1
                ))
                .collect(Collectors.toList());
        System.out.println("Senior people: " + seniorPeople);

        // ============ AGGREGATION AND REDUCTION ============
        System.out.println("\n=== AGGREGATION AND REDUCTION ===");

        // Total salary
        double totalSalary = people.stream()
                .mapToDouble(Person::getSalary)
                .sum();
        System.out.println("Total salary: $" + totalSalary);

        // Average salary
        double avgSalary = people.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0);
        System.out.println("Average salary: $" + avgSalary);

        // Max salary
        Optional<Person> highestPaid = people.stream()
                .max(Comparator.comparing(Person::getSalary));
        System.out.println("Highest paid: " + highestPaid.orElse(null));

        // Custom reduction - concatenate names
        String names = people.stream()
                .map(Person::getName)
                .reduce("Names: ", (a, b) -> a + ", " + b);
        System.out.println(names);

        // ============ GROUPING ============
        System.out.println("\n=== GROUPING ===");

        // Group by city
        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("By city: " + byCity.keySet());
        byCity.forEach((city, persons) ->
                System.out.println(city + ": " + persons.size() + " people"));

        // Group by age range
        Map<String, List<Person>> byAgeRange = people.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getAge() < 30) return "Under 30";
                    else if (p.getAge() < 40) return "30-39";
                    else return "40+";
                }));
        System.out.println("By age range:");
        byAgeRange.forEach((range, persons) ->
                System.out.println(range + ": " + persons));

        // ============ GROUPING WITH AGGREGATION ============
        System.out.println("\n=== GROUPING WITH AGGREGATION ===");

        // Average salary by city
        Map<String, Double> avgSalaryByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.averagingDouble(Person::getSalary)
                ));
        System.out.println("Average salary by city: " + avgSalaryByCity);

        // Total salary by city
        Map<String, Double> totalSalaryByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.summingDouble(Person::getSalary)
                ));
        System.out.println("Total salary by city: " + totalSalaryByCity);

        // Count by city
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.counting()
                ));
        System.out.println("Count by city: " + countByCity);

        // Complex grouping - city -> age range -> average salary
        Map<String, Map<String, Double>> complex = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.groupingBy(
                                p -> p.getAge() < 30 ? "Young" : "Senior",
                                Collectors.averagingDouble(Person::getSalary)
                        )
                ));
        System.out.println("Complex grouping: " + complex);

        // ============ SORTING AND ORDERING ============
        System.out.println("\n=== SORTING AND ORDERING ===");

        // Sort by salary descending
        people.stream()
                .sorted(Comparator.comparing(Person::getSalary).reversed())
                .limit(3)
                .forEach(System.out::println);

        // Sort by city then name
        people.stream()
                .sorted(Comparator.comparing(Person::getCity)
                        .thenComparing(Person::getName))
                .forEach(p -> System.out.println(p.getCity() + ": " + p.getName()));

        // ============ PEAK AND DEBUGGING ============
        System.out.println("\n=== PEAK AND DEBUGGING ===");

        List<Integer> result = Stream.of(1, 2, 3, 4, 5)
                .peek(x -> System.out.println("Source: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("Filtered: " + x))
                .map(x -> x * x)
                .peek(x -> System.out.println("Mapped: " + x))
                .limit(2)
                .peek(x -> System.out.println("Limited: " + x))
                .collect(Collectors.toList());

        // ============ STREAM TO MAP ============
        System.out.println("\n=== STREAM TO MAP ===");

        // Name to salary map
        Map<String, Double> nameToSalary = people.stream()
                .collect(Collectors.toMap(
                        Person::getName,
                        Person::getSalary,
                        (existing, replacement) -> existing  // Handle duplicates
                ));
        System.out.println("Name to salary: " + nameToSalary);

        // City to list of names
        Map<String, List<String>> cityToNames = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println("City to names: " + cityToNames);

        // ============ CUSTOM COLLECTOR ============
        System.out.println("\n=== CUSTOM COLLECTOR ===");

        // Collect to custom collection
        List<Person> filtered = people.stream()
                .filter(p -> p.getSalary() > 70000)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Filtered list: " + filtered);

        // ============ OPTIONAL OPERATIONS ============
        System.out.println("\n=== OPTIONAL OPERATIONS ===");

        // Find person with highest salary
        Optional<Person> maxSalary = people.stream()
                .max(Comparator.comparing(Person::getSalary));
        maxSalary.ifPresent(p -> System.out.println("Max salary: " + p));

        // Find person with salary > 200000
        Optional<Person> highEarner = people.stream()
                .filter(p -> p.getSalary() > 200000)
                .findFirst();
        System.out.println("High earner: " + highEarner.orElse(null));

        // Or else operations
        String city = people.stream()
                .filter(p -> p.getName().equals("Unknown"))
                .map(Person::getCity)
                .findFirst()
                .orElse("Unknown City");
        System.out.println("City: " + city);
    }
}
