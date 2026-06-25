package JVMBasics;

import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Demonstrates garbage collection concepts and techniques.
 * GC automatically manages memory by removing unreachable objects.
 */
public class GarbageCollection {
    public static void main(String[] args) throws InterruptedException {
        // ============ GC BASICS ============
        System.out.println("=== GARBAGE COLLECTION BASICS ===");
        System.out.println("GC automates memory management");
        System.out.println("- Objects are created on the heap");
        System.out.println("- Unreachable objects become eligible for GC");
        System.out.println("- GC runs automatically (you can request it)");

        // ============ REACHABILITY ============
        System.out.println("\n=== REACHABILITY ===");
        demonstrateReachability();

        // ============ REFERENCE TYPES ============
        System.out.println("\n=== REFERENCE TYPES ===");
        demonstrateReferenceTypes();

        // ============ FINALIZE ============
        System.out.println("\n=== FINALIZE ===");
        demonstrateFinalize();

        // ============ GC ALGORITHMS ============
        System.out.println("\n=== GC ALGORITHMS ===");
        System.out.println("Common GC algorithms:");
        System.out.println("1. Serial GC: Uses single thread for GC");
        System.out.println("2. Parallel GC: Uses multiple threads for GC");
        System.out.println("3. CMS (Concurrent Mark Sweep): Low pause GC");
        System.out.println("4. G1 (Garbage First): Server-style GC");
        System.out.println("5. ZGC: Low latency GC (Java 11+)");

        // ============ MEMORY LEAKS ============
        System.out.println("\n=== MEMORY LEAK EXAMPLES ===");
        demonstrateMemoryLeaks();

        // ============ GC TUNING ============
        System.out.println("\n=== GC TUNING ===");
        System.out.println("Common JVM GC tuning flags:");
        System.out.println("  -XX:+UseG1GC: Use G1 Garbage Collector");
        System.out.println("  -XX:+UseParallelGC: Use Parallel GC");
        System.out.println("  -Xms: Initial heap size");
        System.out.println("  -Xmx: Maximum heap size");
        System.out.println("  -XX:MaxGCPauseMillis: Target GC pause time");
    }

    private static void demonstrateReachability() {
        System.out.println("Objects with references are reachable:");

        // Strong reference
        Object strongRef = new Object();
        System.out.println("  Strong reference: " + strongRef);

        // Multiple references to same object
        Object anotherRef = strongRef;
        System.out.println("  Multiple references: " + anotherRef);

        // Nullify reference
        strongRef = null;
        System.out.println("  After nullifying one reference: " + strongRef);
        System.out.println("  Object still reachable through anotherRef: " + anotherRef);

        // All references nullified - object becomes eligible for GC
        anotherRef = null;
        System.out.println("  After nullifying all references, object is eligible for GC");

        // Isolated objects (no references)
        createIsolatedObject();
        System.out.println("  Created object in method - eligible for GC after method returns");
    }

    private static void createIsolatedObject() {
        @SuppressWarnings("unused")
        Object isolated = new Object();
        System.out.println("  Object created: " + isolated);
        // When method returns, 'isolated' goes out of scope
    }

    private static void demonstrateReferenceTypes() throws InterruptedException {
        // ============ SOFT REFERENCES ============
        System.out.println("Soft References - keep object unless memory is low:");
        Object strongObject = new Object();
        SoftReference<Object> softRef = new SoftReference<>(strongObject);
        System.out.println("  Soft reference created");
        strongObject = null;

        // Try to get object
        Object softObject = softRef.get();
        System.out.println("  Soft object retrieved: " + (softObject != null));

        // ============ WEAK REFERENCES ============
        System.out.println("\nWeak References - object can be collected at any time:");
        Object weakObject = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(weakObject);
        System.out.println("  Weak reference created");
        weakObject = null;

        // Request GC
        System.gc();
        Thread.sleep(100);

        Object retrieved = weakRef.get();
        System.out.println("  Weak object retrieved after GC: " + (retrieved != null));

        // ============ PHANTOM REFERENCES ============
        System.out.println("\nPhantom References - object is finalized, cannot retrieve:");
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object phantomObject = new Object();
        PhantomReference<Object> phantomRef = new PhantomReference<>(phantomObject, queue);
        System.out.println("  Phantom reference created");
        phantomObject = null;

        System.gc();
        Thread.sleep(100);

        // Phantom references always return null
        Object phantomRetrieved = phantomRef.get();
        System.out.println("  Phantom object retrieved: " + (phantomRetrieved != null));
        System.out.println("  Phantom enqueued: " + phantomRef.isEnqueued());

        // ============ REFERENCE QUEUE ============
        System.out.println("\nReference Queue - track objects being finalized:");
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        Object trackedObject = new Object();
        WeakReference<Object> trackedRef = new WeakReference<>(trackedObject, refQueue);
        trackedObject = null;

        System.gc();
        Thread.sleep(100);

        Reference<?> queueRef = refQueue.poll();
        System.out.println("  Object enqueued: " + (queueRef != null));
        if (queueRef != null) {
            System.out.println("  Enqueued reference type: " + queueRef.getClass().getSimpleName());
        }
    }

    private static void demonstrateFinalize() {
        System.out.println("Finalization - cleanup before GC:");

        // Create objects with finalize
        FinalizableObject obj1 = new FinalizableObject(1);
        FinalizableObject obj2 = new FinalizableObject(2);

        System.out.println("  Objects created: " + obj1 + ", " + obj2);

        // Nullify references
        obj1 = null;
        obj2 = null;

        System.out.println("  Objects nullified, eligible for GC");
        System.out.println("  finalize() will be called before collection");
        System.out.println("  (Note: finalize is deprecated, use Cleaner or try-with-resources)");

        // Request GC to see finalize in action
        System.gc();

        // System.runFinalization(); // Can be called to run finalizers
    }

    private static void demonstrateMemoryLeaks() {
        System.out.println("Common memory leak scenarios:");

        // 1. Unbounded collections
        System.out.println("1. Unbounded collections:");
        List<Object> leakList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            leakList.add(new Object());
            if (i % 100 == 0) {
                System.out.println("  Added " + (i + 1) + " objects");
            }
        }

        // 2. Static collections
        System.out.println("2. Static collections holding references:");
        StaticCache.cache.add("Leaked object");

        // 3. Listener/Event Handler leaks
        System.out.println("3. Event listeners not unregistered:");
        // In real code, listeners should be unregistered

        // 4. Inner class holding outer reference
        System.out.println("4. Inner class holding outer class reference:");
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        outer = null;  // Inner still holds reference to outer
        System.out.println("  Inner class still holds reference to outer");

        // 5. Unclosed resources
        System.out.println("5. Unclosed resources (file handles, connections):");
        // Always use try-with-resources or close in finally
    }

    // ============ FINALIZABLE CLASS ============
    static class FinalizableObject {
        private int id;

        FinalizableObject(int id) {
            this.id = id;
        }

        @Override
        @Deprecated
        protected void finalize() {
            System.out.println("  finalize() called for object " + id);
        }

        @Override
        public String toString() {
            return "FinalizableObject{" + id + "}";
        }
    }

    // ============ STATIC CACHE (Memory Leak) ============
    static class StaticCache {
        static List<String> cache = new ArrayList<>();
    }

    // ============ OUTER CLASS (Inner class memory leak) ============
    static class OuterClass {
        @SuppressWarnings("unused")
        private String data = "Data";

        class InnerClass {
            @SuppressWarnings("unused")
            private String innerData = "Inner";
            // Holds implicit reference to OuterClass
        }
    }
}