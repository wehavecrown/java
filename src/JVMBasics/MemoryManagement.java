package JVMBasics;

/**
 * Demonstrates JVM memory management concepts.
 * Understanding memory is crucial for performance optimization.
 */
public class MemoryManagement {
    // Static variables - stored in Method Area
    private static int staticVar = 42;
    private static String staticString = "Static String";

    // Instance variable - stored in Heap
    private int instanceVar = 100;
    private String instanceString = "Instance String";

    public static void main(String[] args) {
        System.out.println("=== JVM MEMORY MANAGEMENT ===");
        System.out.println("This program demonstrates JVM memory areas");
        System.out.println("- Stack: stores method frames, local variables, and method calls");
        System.out.println("- Heap: stores all objects and their instance variables");
        System.out.println("- Method Area: stores class definitions, static variables, and constants");
        System.out.println("- PC Registers: store current executing instruction");
        System.out.println("- Native Method Stacks: for native method execution\n");

        // ============ STACK VS HEAP ============
        System.out.println("=== STACK VS HEAP ===");
        demonstrateStackVsHeap();

        // ============ OBJECTS ON HEAP ============
        System.out.println("\n=== OBJECTS ON HEAP ===");
        demonstrateObjectCreation();

        // ============ STRING POOL ============
        System.out.println("\n=== STRING POOL ===");
        demonstrateStringPool();

        // ============ MEMORY VISUALIZATION ============
        System.out.println("\n=== MEMORY USAGE ===");
        printMemoryUsage();

        // ============ GARBAGE COLLECTION ============
        System.out.println("\n=== GARBAGE COLLECTION ===");
        demonstrateGarbageCollection();

        // ============ STACK OVERFLOW ============
        System.out.println("\n=== STACK OVERFLOW ===");
        demonstrateStackOverflow();

        // ============ OUT OF MEMORY ============
        System.out.println("\n=== OUT OF MEMORY ===");
        demonstrateOutOfMemory();
    }

    private static void demonstrateStackVsHeap() {
        // Local variables are stored on the stack
        int localVar = 10;
        String localString = "Local String";

        // Objects are stored on the heap
        MemoryManagement obj = new MemoryManagement();

        System.out.println("Stack:");
        System.out.println("  - localVar (int): " + localVar + " (value stored directly)");
        System.out.println("  - localString (String): reference to 'Local String' in heap");
        System.out.println("  - obj (MemoryManagement): reference to object in heap");

        System.out.println("\nHeap:");
        System.out.println("  - obj instanceVar: " + obj.instanceVar);
        System.out.println("  - obj instanceString: " + obj.instanceString);
        System.out.println("  - String objects: 'Local String', 'Instance String'");

        System.out.println("\nMethod Area:");
        System.out.println("  - staticVar: " + staticVar);
        System.out.println("  - staticString: " + staticString);
        System.out.println("  - MemoryManagement class definition");
    }

    private static void demonstrateObjectCreation() {
        System.out.println("Objects created on the heap:");

        // Each new object creates memory on the heap
        MyObject obj1 = new MyObject(1);
        MyObject obj2 = new MyObject(2);
        MyObject obj3 = new MyObject(3);

        System.out.println("obj1 address: " + System.identityHashCode(obj1));
        System.out.println("obj2 address: " + System.identityHashCode(obj2));
        System.out.println("obj3 address: " + System.identityHashCode(obj3));

        // Object references can be changed
        obj3 = obj1;  // obj3 now references obj1's object
        System.out.println("After obj3 = obj1:");
        System.out.println("obj1 address: " + System.identityHashCode(obj1));
        System.out.println("obj3 address: " + System.identityHashCode(obj3));
        // The original obj3 object is now eligible for garbage collection
    }

    private static void demonstrateStringPool() {
        String str1 = "Hello";  // String literal - stored in String Pool
        String str2 = "Hello";  // Same literal - reuses from pool
        String str3 = new String("Hello");  // New String object on heap

        System.out.println("str1 == str2: " + (str1 == str2));  // true - same reference
        System.out.println("str1 == str3: " + (str1 == str3));  // false - different objects
        System.out.println("str1.equals(str3): " + str1.equals(str3));  // true - same content

        // String pool can be manually accessed
        String interned = str3.intern();  // Returns reference from String Pool
        System.out.println("str1 == str3.intern(): " + (str1 == interned));  // true

        System.out.println("\nString pool optimizes memory by reusing string literals.");
    }

    private static void printMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();

        // Get JVM memory information
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("JVM Memory Usage:");
        System.out.println("  Max Memory: " + maxMemory / (1024 * 1024) + " MB");
        System.out.println("  Total Memory: " + totalMemory / (1024 * 1024) + " MB");
        System.out.println("  Used Memory: " + usedMemory / (1024 * 1024) + " MB");
        System.out.println("  Free Memory: " + freeMemory / (1024 * 1024) + " MB");
    }

    private static void demonstrateGarbageCollection() {
        System.out.println("Creating objects that become garbage...");

        for (int i = 0; i < 1000; i++) {
            // Objects created inside loop become eligible for GC
            @SuppressWarnings("unused")
            Object obj = new Object();
            if (i % 100 == 0) {
                // Request garbage collection (not guaranteed to run)
                System.gc();
                printMemoryUsage();
            }
        }

        System.out.println("Objects eligible for garbage collection");
        System.out.println("GC is automatic but can be requested via System.gc()");
    }

    private static void demonstrateStackOverflow() {
        try {
            System.out.println("Calling recursive method (will cause StackOverflowError)...");
            recursiveMethod(1);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError occurred: " + e.getMessage());
            System.out.println("Stack is limited and recursion without base case causes overflow");
        }
    }

    private static int recursiveMethod(int count) {
        // No base case - will cause stack overflow
        return recursiveMethod(count + 1);
    }

    private static void demonstrateOutOfMemory() {
        try {
            System.out.println("Creating large arrays (may cause OutOfMemoryError)...");
            @SuppressWarnings("unused")
            int[][] largeArray = new int[1000][];
            for (int i = 0; i < 1000; i++) {
                largeArray[i] = new int[1000000];
                if (i % 100 == 0) {
                    System.out.println("Allocated " + (i + 1) * 1000000 + " ints");
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError occurred: " + e.getMessage());
            System.out.println("Heap memory is limited by JVM settings");
        }
    }

    // Helper class
    static class MyObject {
        int value;
        @SuppressWarnings("unused")
        byte[] data = new byte[1024];  // Simulate data

        MyObject(int value) {
            this.value = value;
        }
    }
}