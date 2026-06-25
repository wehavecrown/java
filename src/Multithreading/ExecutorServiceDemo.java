package Multithreading;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates Executor framework for thread pool management.
 * ExecutorService provides higher-level thread management than raw threads.
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // ============ FIXED THREAD POOL ============
        System.out.println("=== FIXED THREAD POOL ===");

        // Creates a thread pool with 3 threads
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        // Submit tasks
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        fixedPool.shutdown();  // No more tasks accepted
        fixedPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Fixed pool completed");

        // ============ CACHED THREAD POOL ============
        System.out.println("\n=== CACHED THREAD POOL ===");

        // Creates threads as needed, reuses idle threads
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        // Submit many short tasks
        for (int i = 0; i < 20; i++) {
            final int taskId = i;
            cachedPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " processing " + taskId);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        cachedPool.shutdown();
        cachedPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Cached pool completed");

        // ============ SCHEDULED THREAD POOL ============
        System.out.println("\n=== SCHEDULED THREAD POOL ===");

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);

        // Schedule a task after 1 second delay
        scheduledPool.schedule(() -> {
            System.out.println("Task executed after 1 second delay");
        }, 1, TimeUnit.SECONDS);

        // Schedule periodic task
        ScheduledFuture<?> periodicTask = scheduledPool.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed at: " + System.currentTimeMillis());
        }, 2, 1, TimeUnit.SECONDS);

        // Cancel after 5 seconds
        scheduledPool.schedule(() -> {
            periodicTask.cancel(true);
            scheduledPool.shutdown();
        }, 5, TimeUnit.SECONDS);

        scheduledPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Scheduled pool completed");

        // ============ CALLABLE AND FUTURE ============
        System.out.println("\n=== CALLABLE AND FUTURE ===");

        ExecutorService callablePool = Executors.newFixedThreadPool(3);

        // Submit tasks that return results
        Future<Integer> future1 = callablePool.submit(() -> {
            Thread.sleep(1000);
            return 42;
        });

        Future<Integer> future2 = callablePool.submit(() -> {
            Thread.sleep(500);
            return 36;
        });

        // Get results (blocks until ready)
        System.out.println("Result 1: " + future1.get());
        System.out.println("Result 2: " + future2.get());

        callablePool.shutdown();

        // ============ INVOKEALL - MULTIPLE TASKS ============
        System.out.println("\n=== INVOKEALL - MULTIPLE TASKS ===");

        ExecutorService invokeAllPool = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final int value = i;
            tasks.add(() -> {
                System.out.println("Computing task " + value);
                Thread.sleep(200);
                return value * 10;
            });
        }

        // Execute all tasks and get results
        List<Future<Integer>> results = invokeAllPool.invokeAll(tasks);
        for (Future<Integer> future : results) {
            System.out.println("Result: " + future.get());
        }

        invokeAllPool.shutdown();

        // ============ INVOKEANY - FIRST SUCCESSFUL ============
        System.out.println("\n=== INVOKEANY - FIRST SUCCESSFUL ===");

        ExecutorService invokeAnyPool = Executors.newFixedThreadPool(3);
        List<Callable<String>> stringTasks = new ArrayList<>();

        stringTasks.add(() -> {
            System.out.println("Task 1 attempting...");
            Thread.sleep(500);
            return "Result from Task 1";
        });

        stringTasks.add(() -> {
            System.out.println("Task 2 attempting...");
            Thread.sleep(200);
            return "Result from Task 2";
        });

        stringTasks.add(() -> {
            System.out.println("Task 3 attempting...");
            Thread.sleep(800);
            return "Result from Task 3";
        });

        // Returns result of first successful task
        String firstResult = invokeAnyPool.invokeAny(stringTasks);
        System.out.println("First result: " + firstResult);

        invokeAnyPool.shutdown();

        // ============ WORK STEALING POOL ============
        System.out.println("\n=== WORK STEALING POOL ===");

        // Java 8+ - work-stealing pool (ForkJoinPool)
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        List<Callable<String>> stealingTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int id = i;
            stealingTasks.add(() -> {
                System.out.println("Task " + id + " by " + Thread.currentThread().getName());
                Thread.sleep(100);
                return "Task " + id + " completed";
            });
        }

        List<Future<String>> stealingResults = workStealingPool.invokeAll(stealingTasks);
        for (Future<String> result : stealingResults) {
            System.out.println(result.get());
        }

        workStealingPool.shutdown();
    }
}