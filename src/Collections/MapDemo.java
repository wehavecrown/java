package Collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates Map interface and its implementations.
 * Map stores key-value pairs, keys must be unique.
 */
public class MapDemo {
    public static void main(String[] args) {
        // ============ HASHMAP ============
        System.out.println("=== HASHMAP ===");

        // HashMap - O(1) operations, no ordering
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 20);
        hashMap.put("Cherry", 30);
        hashMap.put("Apple", 15);  // Overwrites previous value
        System.out.println("HashMap: " + hashMap);

        // Accessing values
        System.out.println("Value for 'Banana': " + hashMap.get("Banana"));
        System.out.println("Value for 'Date': " + hashMap.getOrDefault("Date", 0));

        // Check existence
        System.out.println("Contains key 'Apple': " + hashMap.containsKey("Apple"));
        System.out.println("Contains value 20: " + hashMap.containsValue(20));

        // Remove entry
        hashMap.remove("Cherry");
        System.out.println("After removal: " + hashMap);

        // ============ LINKEDHASHMAP ============
        System.out.println("\n=== LINKEDHASHMAP ===");

        // LinkedHashMap - maintains insertion order
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("One", 1);
        linkedHashMap.put("Two", 2);
        linkedHashMap.put("Three", 3);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);

        // Access order (LRU cache)
        Map<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        System.out.println("Access order map: " + accessOrderMap);

        accessOrderMap.get("A");
        accessOrderMap.get("B");
        System.out.println("After accessing A and B: " + accessOrderMap);

        // ============ TREEMAP ============
        System.out.println("\n=== TREEMAP ===");

        // TreeMap - sorted by keys
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Charlie", 30);
        treeMap.put("Alice", 25);
        treeMap.put("Bob", 28);
        treeMap.put("David", 35);
        System.out.println("TreeMap (sorted): " + treeMap);

        // TreeMap with custom comparator
        Map<String, Integer> reverseTreeMap = new TreeMap<>(Comparator.reverseOrder());
        reverseTreeMap.putAll(treeMap);
        System.out.println("TreeMap (reverse order): " + reverseTreeMap);

        // TreeMap operations
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(1, "One");
        tm.put(2, "Two");
        tm.put(3, "Three");
        tm.put(4, "Four");
        tm.put(5, "Five");
        System.out.println("TreeMap: " + tm);
        System.out.println("First entry: " + tm.firstEntry());
        System.out.println("Last entry: " + tm.lastEntry());
        System.out.println("Lower key than 3: " + tm.lowerKey(3));
        System.out.println("Higher key than 3: " + tm.higherKey(3));
        System.out.println("SubMap (2 to 4 exclusive): " + tm.subMap(2, 4));

        // ============ HASHTABLE (Legacy, synchronized) ============
        System.out.println("\n=== HASHTABLE (Legacy) ===");

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("One", 1);
        hashtable.put("Two", 2);
        hashtable.put("Three", 3);
        // hashtable.put(null, 4);  // Null not allowed
        System.out.println("Hashtable: " + hashtable);

        // ============ CONCURRENTHASHMAP ============
        System.out.println("\n=== CONCURRENTHASHMAP ===");

        Map<String, Integer> concurrentMap = new java.util.concurrent.ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        concurrentMap.put("C", 3);
        System.out.println("ConcurrentHashMap: " + concurrentMap);

        // ============ MAP OPERATIONS ============
        System.out.println("\n=== MAP OPERATIONS ===");

        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // putIfAbsent
        map.putIfAbsent("Two", 20);  // Won't change
        map.putIfAbsent("Four", 4);   // Will add
        System.out.println("After putIfAbsent: " + map);

        // computeIfAbsent
        map.computeIfAbsent("Five", k -> 5);
        map.computeIfAbsent("Three", k -> 30);  // Won't execute
        System.out.println("After computeIfAbsent: " + map);

        // computeIfPresent
        map.computeIfPresent("Two", (k, v) -> v * 10);
        map.computeIfPresent("Six", (k, v) -> 6);  // Won't execute
        System.out.println("After computeIfPresent: " + map);

        // compute
        map.compute("Seven", (k, v) -> 7);
        map.compute("Two", (k, v) -> null);  // Removes key
        System.out.println("After compute: " + map);

        // merge
        map.merge("Eight", 8, (oldVal, newVal) -> oldVal + newVal);
        map.merge("Eight", 2, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("After merge: " + map);

        // replace
        map.replace("Three", 3, 30);
        map.replace("Four", 40);
        System.out.println("After replace: " + map);

        // ============ VIEWS OF MAP ============
        System.out.println("\n=== VIEWS OF MAP ===");

        Map<Integer, String> viewMap = new HashMap<>();
        viewMap.put(1, "One");
        viewMap.put(2, "Two");
        viewMap.put(3, "Three");

        // Key set
        Set<Integer> keys = viewMap.keySet();
        System.out.println("Keys: " + keys);

        // Values collection
        Collection<String> values = viewMap.values();
        System.out.println("Values: " + values);

        // Entry set
        Set<Map.Entry<Integer, String>> entries = viewMap.entrySet();
        System.out.println("Entries:");
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ============ ITERATING OVER MAP ============
        System.out.println("\n=== ITERATING OVER MAP ===");

        Map<Integer, String> iterMap = new HashMap<>();
        iterMap.put(1, "A");
        iterMap.put(2, "B");
        iterMap.put(3, "C");

        // For-each with entry set
        System.out.println("For-each with entry set:");
        for (Map.Entry<Integer, String> entry : iterMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // For-each with keys
        System.out.println("For-each with keys:");
        for (Integer key : iterMap.keySet()) {
            System.out.println(key + " = " + iterMap.get(key));
        }

        // For-each with values
        System.out.println("For-each with values:");
        for (String value : iterMap.values()) {
            System.out.println(value);
        }

        // Stream (Java 8+)
        System.out.println("Stream:");
        iterMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

        // ============ UNMODIFIABLE MAP ============
        System.out.println("\n=== UNMODIFIABLE MAP ===");

        Map<String, Integer> mutableMap = new HashMap<>(Map.of("A", 1, "B", 2));
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
        System.out.println("Unmodifiable map: " + unmodifiableMap);
        // unmodifiableMap.put("C", 3); // Throws UnsupportedOperationException

        // Java 9+ - Map.of creates immutable map
        Map<String, Integer> immutableMap = Map.of("X", 10, "Y", 20, "Z", 30);
        System.out.println("Immutable map: " + immutableMap);
    }
}