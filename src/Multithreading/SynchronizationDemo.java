package Multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

/**
 * Demonstrates thread synchronization to prevent race conditions.
 * Synchronization ensures that shared resources are accessed safely by multiple threads.
 */
public class SynchronizationDemo {
    private static Counter counter = new Counter();
    private static SynchronizedCounter syncCounter = new SynchronizedCounter();
    private static AtomicCounter atomicCounter = new AtomicCounter();
    private static LockCounter lockCounter = new LockCounter();

    public static void main(String[] args) throws InterruptedException {
        // ============ RACE CONDITION DEMONSTRATION ============
        System.out.println("=== RACE CONDITION ===");

        // Create multiple threads that increment a counter
        Thread[] threads = new Thread[1000];

        // Using non-synchronized counter (will be incorrect)
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Expected: 1,000,000");
        System.out.println("Without synchronization: " + counter.getValue());

        // ============ SYNCHRONIZED METHOD ============
        System.out.println("\n=== SYNCHRONIZED METHOD ===");

        Thread[] syncThreads = new Thread[1000];
        for (int i = 0; i < syncThreads.length; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    syncCounter.increment();
                }
            });
        }

        for (Thread t : syncThreads) t.start();
        for (Thread t : syncThreads) t.join();

        System.out.println("With synchronized method: " + syncCounter.getValue());

        // ============ SYNCHRONIZED BLOCK ============
        System.out.println("\n=== SYNCHRONIZED BLOCK ===");

        SynchronizedBlockExample blockExample = new SynchronizedBlockExample();
        Thread[] blockThreads = new Thread[1000];
        for (int i = 0; i < blockThreads.length; i++) {
            blockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    blockExample.increment();
                }
            });
        }

        for (Thread t : blockThreads) t.start();
        for (Thread t : blockThreads) t.join();

        System.out.println("With synchronized block: " + blockExample.getValue());

        // ============ ATOMIC VARIABLES ============
        System.out.println("\n=== ATOMIC VARIABLES ===");

        Thread[] atomicThreads = new Thread[1000];
        for (int i = 0; i < atomicThreads.length; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.increment();
                }
            });
        }

        for (Thread t : atomicThreads) t.start();
        for (Thread t : atomicThreads) t.join();

        System.out.println("With AtomicInteger: " + atomicCounter.getValue());

        // ============ REENTRANT LOCK ============
        System.out.println("\n=== REENTRANT LOCK ===");

        Thread[] lockThreads = new Thread[1000];
        for (int i = 0; i < lockThreads.length; i++) {
            lockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    lockCounter.increment();
                }
            });
        }

        for (Thread t : lockThreads) t.start();
        for (Thread t : lockThreads) t.join();

        System.out.println("With ReentrantLock: " + lockCounter.getValue());

        // ============ DEADLOCK DEMONSTRATION ============
        System.out.println("\n=== DEADLOCK DEMONSTRATION ===");
        demonstrateDeadlock();

        // ============ READ-WRITE LOCK ============
        System.out.println("\n=== READ-WRITE LOCK ===");
        ReadWriteLockExample rwExample = new ReadWriteLockExample();

        // Multiple readers
        Thread[] readers = new Thread[10];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    rwExample.readValue();
                }
            });
        }

        // One writer
        Thread writer = new Thread(() -> {
            for (int j = 0; j < 3; j++) {
                rwExample.writeValue(j);
            }
        });

        for (Thread t : readers) t.start();
        writer.start();

        for (Thread t : readers) t.join();
        writer.join();
    }

    // ============ DEADLOCK EXAMPLE ============
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void demonstrateDeadlock() {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 1 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 acquired lock2");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 2 waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread 2 acquired lock1");
                }
            }
        });

        thread1.start();
        thread2.start();

        // To prevent deadlock in demo, wait and then interrupt
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        // In real code, you'd need to detect and handle deadlocks
        // Here we just interrupt to continue
        thread1.interrupt();
        thread2.interrupt();
    }
}

// ============ UNSYNCHRONIZED COUNTER ============
class Counter {
    private int value = 0;

    public void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}

// ============ SYNCHRONIZED METHOD ============
class SynchronizedCounter {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}

// ============ SYNCHRONIZED BLOCK ============
class SynchronizedBlockExample {
    private int value = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {  // Synchronized block
            value++;
        }
    }

    public int getValue() {
        return value;
    }
}

// ============ ATOMIC VARIABLE ============
class AtomicCounter {
    private AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();  // Atomic operation
    }

    public int getValue() {
        return value.get();
    }
}

// ============ REENTRANT LOCK ============
class LockCounter {
    private int value = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();  // Always unlock in finally block
        }
    }

    public int getValue() {
        return value;
    }
}

// ============ READ-WRITE LOCK ============
class ReadWriteLockExample {
    private int value = 0;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public int readValue() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + value);
            Thread.sleep(100);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public void writeValue(int newValue) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + newValue);
            value = newValue;
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
