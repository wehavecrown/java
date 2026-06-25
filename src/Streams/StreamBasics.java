package Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 * Demonstrates Stream API basics.
 * Streams provide a functional approach to processing sequences of elements.
 */
public class StreamBasics {
    public static void main(String[] args) {
        // ============ CREATING STREAMS ============
        System.out.println("=== CREATING STREAMS ===");

        // From collections
        List<String> list = Arrays.asList("A", "B", "C", "D");
        Stream<String> streamFromList = list.stream();

        // From array
        String[] array = {"A", "B", "C", "D"};
        Stream<String> streamFromArray = Arrays.stream(array);

        // From values
        Stream<String> streamFromValues = Stream.of("A", "B", "C", "D");

        // Infinite streams
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
        Stream<Integer> limitedStream = infiniteStream.limit(5);

        // Stream builder
        Stream<String> builderStream = Stream.<String>builder()
                .add("A")
                .add("B")
                .add("C")
                .build();

        // ============ INTERMEDIATE OPERATIONS ============
        System.out.println("\n=== INTERMEDIATE OPERATIONS ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter - keep elements matching predicate
        System.out.println("Filter - even numbers:");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // map - transform each element
        System.out.println("Map - square numbers:");
        numbers.stream()
                .map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // flatMap - flatten nested structures
        System.out.println("FlatMap - flatten nested lists:");
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        nested.stream()
                .flatMap(Collection::stream)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // distinct - remove duplicates
        System.out.println("Distinct:");
        Stream.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // sorted - sort elements
        System.out.println("Sorted:");
        Stream.of(5, 2, 8, 1, 9, 3)
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // sorted with comparator
        System.out.println("Sorted (reverse):");
        Stream.of(5, 2, 8, 1, 9, 3)
                .sorted(Comparator.reverseOrder())
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // limit - restrict size
        System.out.println("Limit - first 5:");
        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // skip - skip elements
        System.out.println("Skip - skip first 5:");
        numbers.stream()
                .skip(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // peek - debug/perform side effect
        System.out.println("Peek - debugging:");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("Filtered: " + n))
                .map(n -> n * n)
                .peek(n -> System.out.println("Mapped: " + n))
                .limit(3)
                .forEach(n -> System.out.println("Result: " + n));

        // ============ TERMINAL OPERATIONS ============
        System.out.println("\n=== TERMINAL OPERATIONS ===");

        // forEach - perform action on each element
        System.out.println("ForEach:");
        numbers.stream().limit(5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // collect - collect into a collection
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Collect to list: " + evenNumbers);

        // toArray - convert to array
        Integer[] numberArray = numbers.stream().toArray(Integer[]::new);
        System.out.println("To array: " + Arrays.toString(numberArray));

        // reduce - reduce to a single value
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Reduce - sum: " + sum);

        int product = numbers.stream()
                .limit(5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Reduce - product (first 5): " + product);

        // count - count elements
        long count = numbers.stream().filter(n -> n > 5).count();
        System.out.println("Count of numbers > 5: " + count);

        // anyMatch/allMatch/noneMatch
        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("Any even: " + anyEven);
        System.out.println("All positive: " + allPositive);
        System.out.println("None negative: " + noneNegative);

        // findFirst/findAny
        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("First even: " + firstEven.orElse(-1));

        // min/max
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println("Max: " + max.orElse(-1));
        System.out.println("Min: " + min.orElse(-1));

        // ============ COLLECTORS ============
        System.out.println("\n=== COLLECTORS ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        // Join strings
        String joined = words.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined);

        // Group by length
        Map<Integer, List<String>> byLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Group by length: " + byLength);

        // Partition by (true/false groups)
        Map<Boolean, List<String>> partitioned = words.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 5));
        System.out.println("Partition by length > 5: " + partitioned);

        // Summarizing statistics
        IntSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Summary: " + stats);

        // Map to count occurrences
        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word count: " + wordCount);

        // ============ PARALLEL STREAMS ============
        System.out.println("\n=== PARALLEL STREAMS ===");

        long startTime = System.currentTimeMillis();
        long sumParallel = numbers.parallelStream()
                .reduce(0, Integer::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel sum: " + sumParallel + " (Time: " + (endTime - startTime) + "ms)");

        // Parallel stream with custom processing
        List<Integer> processed = numbers.parallelStream()
                .map(n -> {
                    System.out.println(Thread.currentThread().getName() + " processing " + n);
                    return n * n;
                })
                .collect(Collectors.toList());
        System.out.println("Parallel processed: " + processed);
    }
}