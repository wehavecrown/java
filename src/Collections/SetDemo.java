package Collections;

import java.util.*;

/**
 * Demonstrates Set interface and its implementations.
 * Set is a collection that contains no duplicate elements.
 */
public class SetDemo {
    public static void main(String[] args) {
        // ============ HASHSET ============
        System.out.println("=== HASHSET ===");

        // HashSet - no ordering, O(1) operations
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple");  // Duplicate - will be ignored
        hashSet.add("Date");

        System.out.println("HashSet: " + hashSet);
        System.out.println("Size: " + hashSet.size());
        System.out.println("Contains 'Banana': " + hashSet.contains("Banana"));
        System.out.println("Remove 'Cherry': " + hashSet.remove("Cherry"));
        System.out.println("After removal: " + hashSet);

        // ============ LINKEDHASHSET ============
        System.out.println("\n=== LINKEDHASHSET ===");

        // LinkedHashSet - maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("One");
        linkedHashSet.add("Two");
        linkedHashSet.add("Three");
        linkedHashSet.add("Four");
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);

        // ============ TREESET ============
        System.out.println("\n=== TREESET ===");

        // TreeSet - sorted order (natural or custom)
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);
        treeSet.add(9);
        System.out.println("TreeSet (sorted): " + treeSet);

        // TreeSet with custom comparator (reverse order)
        Set<Integer> reverseTreeSet = new TreeSet<>(Comparator.reverseOrder());
        reverseTreeSet.add(5);
        reverseTreeSet.add(2);
        reverseTreeSet.add(8);
        reverseTreeSet.add(1);
        reverseTreeSet.add(9);
        System.out.println("TreeSet (reverse order): " + reverseTreeSet);

        // TreeSet operations
        TreeSet<Integer> ts = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("TreeSet: " + ts);
        System.out.println("First: " + ts.first());
        System.out.println("Last: " + ts.last());
        System.out.println("Lower than 5: " + ts.lower(5));
        System.out.println("Higher than 5: " + ts.higher(5));
        System.out.println("Floor of 5: " + ts.floor(5));
        System.out.println("Ceiling of 5: " + ts.ceiling(5));
        System.out.println("Subset (2 to 6 exclusive): " + ts.subSet(2, 6));
        System.out.println("HeadSet (< 5): " + ts.headSet(5));
        System.out.println("TailSet (>= 5): " + ts.tailSet(5));

        // ============ SET OPERATIONS ============
        System.out.println("\n=== SET OPERATIONS ===");

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference (set1 - set2)
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);

        // Symmetric Difference
        Set<Integer> symmetricDiff = new HashSet<>(set1);
        symmetricDiff.addAll(set2);
        Set<Integer> temp = new HashSet<>(set1);
        temp.retainAll(set2);
        symmetricDiff.removeAll(temp);
        System.out.println("Symmetric Difference: " + symmetricDiff);

        // ============ SET WITH CUSTOM OBJECTS ============
        System.out.println("\n=== SET WITH CUSTOM OBJECTS ===");

        // Need to override equals() and hashCode() for custom objects
        Set<Book> books = new HashSet<>();
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("Brave New World", "Aldous Huxley"));
        books.add(new Book("1984", "George Orwell"));  // Duplicate - will be ignored

        System.out.println("Books HashSet (size): " + books.size());
        System.out.println("Books: " + books);

        // ============ UNMODIFIABLE SET ============
        System.out.println("\n=== UNMODIFIABLE SET ===");

        Set<String> modifiableSet = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> unmodifiableSet = Collections.unmodifiableSet(modifiableSet);
        System.out.println("Unmodifiable set: " + unmodifiableSet);
        // unmodifiableSet.add("D"); // Throws UnsupportedOperationException

        // Java 9+ - Set.of creates immutable set
        Set<String> immutableSet = Set.of("X", "Y", "Z");
        System.out.println("Immutable set: " + immutableSet);

        // ============ ITERATING OVER SET ============
        System.out.println("\n=== ITERATING OVER SET ===");

        Set<Integer> numbers = new HashSet<>(Arrays.asList(10, 20, 30, 40, 50));

        // For-each
        System.out.print("For-each: ");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Iterator
        System.out.print("Iterator: ");
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // Stream (Java 8+)
        System.out.print("Stream: ");
        numbers.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}

// ============ CUSTOM CLASS FOR SET ============
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Override equals and hashCode for proper Set behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}
