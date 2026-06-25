package Collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates List interface and its implementations.
 * List is an ordered collection that allows duplicates and indexed access.
 */
public class ListDemo {
    public static void main(String[] args) {
        // ============ ARRAYLIST ============
        System.out.println("=== ARRAYLIST ===");

        // ArrayList - resizable array implementation
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(0, "Strawberry");  // Insert at index 0
        System.out.println("ArrayList: " + arrayList);

        // Access by index
        System.out.println("Element at index 2: " + arrayList.get(2));

        // Check if contains
        System.out.println("Contains 'Banana': " + arrayList.contains("Banana"));

        // Remove by object or index
        arrayList.remove("Banana");
        arrayList.remove(0);
        System.out.println("After removals: " + arrayList);

        // Iteration methods
        System.out.println("Using for-each:");
        for (String fruit : arrayList) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        System.out.println("Using Iterator:");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // ============ LINKEDLIST ============
        System.out.println("\n=== LINKEDLIST ===");

        // LinkedList - doubly-linked list implementation
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.addFirst("Z");  // Add to front
        linkedList.addLast("Y");   // Add to end
        System.out.println("LinkedList: " + linkedList);

        // Queue operations
        System.out.println("First element (peek): " + linkedList.peek());
        System.out.println("Remove first (poll): " + linkedList.poll());
        System.out.println("After poll: " + linkedList);
        linkedList.offer("X");  // Add to end (queue operation)
        System.out.println("After offer: " + linkedList);

        // Stack operations
        LinkedList<String> stack = new LinkedList<>();
        stack.push("1");  // Push to stack
        stack.push("2");
        stack.push("3");
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());  // Pop from stack
        System.out.println("After pop: " + stack);

        // ============ VECTOR (Legacy) ============
        System.out.println("\n=== VECTOR (Legacy) ===");

        Vector<String> vector = new Vector<>();
        vector.add("One");
        vector.add("Two");
        vector.add("Three");
        System.out.println("Vector: " + vector);
        System.out.println("Vector capacity: " + vector.capacity());
        System.out.println("Vector size: " + vector.size());

        // ============ STACK (Legacy) ============
        System.out.println("\n=== STACK (Legacy) ===");

        Stack<Integer> stack2 = new Stack<>();
        stack2.push(10);
        stack2.push(20);
        stack2.push(30);
        System.out.println("Stack: " + stack2);
        System.out.println("Peek: " + stack2.peek());
        System.out.println("Pop: " + stack2.pop());
        System.out.println("After pop: " + stack2);
        System.out.println("Search for 10: " + stack2.search(10));

        // ============ LIST OPERATIONS ============
        System.out.println("\n=== LIST OPERATIONS ===");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Original: " + numbers);

        // Sorting
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);

        // Reverse
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);

        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);

        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 3);
        System.out.println("Index of 3: " + index);

        // Copy
        List<Integer> dest = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        Collections.copy(dest, numbers);
        System.out.println("Copied list: " + dest);

        // Fill
        Collections.fill(dest, 99);
        System.out.println("Filled with 99: " + dest);

        // Min/Max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));

        // Frequency
        numbers.add(3);
        System.out.println("Frequency of 3: " + Collections.frequency(numbers, 3));

        // ============ SORTING WITH COMPARATOR ============
        System.out.println("\n=== SORTING WITH COMPARATOR ===");

        List<String> words = new ArrayList<>(Arrays.asList("apple", "Banana", "cherry", "Date"));

        // Natural order (String implements Comparable)
        words.sort(null);
        System.out.println("Natural order: " + words);

        // Custom comparator - case insensitive
        words.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case insensitive: " + words);

        // Reverse order
        words.sort(Comparator.reverseOrder());
        System.out.println("Reverse: " + words);

        // Comparator chain
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Alice", 20));
        people.add(new Person("Charlie", 30));

        // Sort by name, then age
        people.sort(Comparator.comparing(Person::getName)
                .thenComparingInt(Person::getAge));
        System.out.println("Sorted by name, then age:");
        people.forEach(System.out::println);

        // ============ CONVERTING TO ARRAY ============
        System.out.println("\n=== CONVERTING TO ARRAY ===");

        List<String> stringList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        String[] stringArray = stringList.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(stringArray));
    }
}

// ============ HELPER CLASS ============
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
