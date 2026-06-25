package Multithreading;

/**
 * Demonstrates basic thread creation and management.
 * Threads allow concurrent execution of code.
 */
public class ThreadBasics {
    public static void main(String[] args) throws InterruptedException {
        // ============ METHOD 1: EXTEND THREAD CLASS ============
        System.out.println("=== EXTEND THREAD ===");
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start();

        // ============ METHOD 2: IMPLEMENT RUNNABLE ============
        System.out.println("\n=== IMPLEMENT RUNNABLE ===");
        Thread thread2 = new Thread(new MyRunnable(), "Thread-2");
        thread2.start();

        // ============ METHOD 3: LAMBDA EXPRESSION ============
        System.out.println("\n=== LAMBDA EXPRESSION ===");
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-3");
        thread3.start();

        // ============ THREAD STATES ============
        System.out.println("\n=== THREAD STATES ===");
        Thread thread4 = new Thread(() -> {
            try {
                System.out.println("Thread state: " + Thread.currentThread().getState());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-4");

        System.out.println("State before start: " + thread4.getState());
        thread4.start();
        System.out.println("State after start: " + thread4.getState());
        Thread.sleep(100);
        System.out.println("State during execution: " + thread4.getState());
        thread4.join();  // Wait for thread to finish
        System.out.println("State after completion: " + thread4.getState());

        // ============ THREAD PRIORITIES ============
        System.out.println("\n=== THREAD PRIORITIES ===");
        Thread highPriority = new Thread(() -> System.out.println("High priority thread"));
        Thread lowPriority = new Thread(() -> System.out.println("Low priority thread"));

        highPriority.setPriority(Thread.MAX_PRIORITY);  // 10
        lowPriority.setPriority(Thread.MIN_PRIORITY);   // 1
        highPriority.start();
        lowPriority.start();

        // ============ DAEMON THREADS ============
        System.out.println("\n=== DAEMON THREADS ===");
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println("Main thread finishing...");
        Thread.sleep(1000);  // Main thread sleeps, daemon may still run
        // When main thread ends, daemon thread will be terminated

        // ============ JOINING THREADS ============
        System.out.println("\n=== JOINING THREADS ===");
        Thread worker1 = new Thread(() -> {
            System.out.println("Worker 1 started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 1 finished");
        });

        Thread worker2 = new Thread(() -> {
            System.out.println("Worker 2 started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 2 finished");
        });

        worker1.start();
        worker2.start();

        try {
            worker1.join();  // Wait for worker1 to finish
            worker2.join();  // Wait for worker2 to finish
            System.out.println("All workers completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ============ THREAD INTERRUPTION ============
        System.out.println("\n=== THREAD INTERRUPTION ===");
        Thread interruptible = new Thread(() -> {
            try {
                System.out.println("Interruptible thread sleeping...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
                // Clean up and exit
                return;
            }
            System.out.println("Thread finished naturally");
        });
        interruptible.start();

        Thread.sleep(1000);
        interruptible.interrupt();  // Interrupt the thread
    }
}

// ============ EXTEND THREAD CLASS ============
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + ": " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// ============ IMPLEMENT RUNNABLE ============
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}