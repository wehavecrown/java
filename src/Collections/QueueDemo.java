package Collections;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Demonstrates Queue interface and its implementations.
 * Queue is a collection for holding elements prior to processing.
 */

public class QueueDemo {
    public static void main(String[] args) {
        // ============ PRIORITYQUEUE ============
        System.out.println("=== PRIORITYQUEUE ===");

        // PriorityQueue - elements ordered by natural order or comparator
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(5);
        priorityQueue.offer(2);
        priorityQueue.offer(8);
        priorityQueue.offer(1);
        priorityQueue.offer(9);

        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.println("Peek: " + priorityQueue.peek());
        System.out.println("Poll: " + priorityQueue.poll());
        System.out.println("After poll: " + priorityQueue);

        // PriorityQueue with custom comparator (reverse order)
        Queue<Integer> reversePriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        reversePriorityQueue.addAll(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Reverse PriorityQueue: " + reversePriorityQueue);
        System.out.println("Poll: " + reversePriorityQueue.poll());
        System.out.println("After poll: " + reversePriorityQueue);

        // PriorityQueue with custom objects
        Queue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("High priority", 1));
        taskQueue.offer(new Task("Medium priority", 2));
        taskQueue.offer(new Task("Low priority", 3));
        taskQueue.offer(new Task("High priority 2", 1));

        System.out.println("Task Queue:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }

        // ============ ARRAYDEQUE (Queue) ============
        System.out.println("\n=== ARRAYDEQUE AS QUEUE ===");

        // ArrayDeque - efficient queue operations
        Queue<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.offer("First");
        arrayDeque.offer("Second");
        arrayDeque.offer("Third");
        System.out.println("ArrayDeque: " + arrayDeque);
        System.out.println("Element: " + arrayDeque.element());
        System.out.println("Remove: " + arrayDeque.remove());
        System.out.println("After remove: " + arrayDeque);

        // ============ ARRAYDEQUE (Stack) ============
        System.out.println("\n=== ARRAYDEQUE AS STACK ===");

        Deque<String> stack = new ArrayDeque<>();
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);
        System.out.println("Peek: " + stack.peek());

        // ============ LINKEDLIST (Queue) ============
        System.out.println("\n=== LINKEDLIST AS QUEUE ===");

        Queue<String> linkedListQueue = new LinkedList<>();
        linkedListQueue.offer("Item 1");
        linkedListQueue.offer("Item 2");
        linkedListQueue.offer("Item 3");
        System.out.println("LinkedList Queue: " + linkedListQueue);
        System.out.println("Poll: " + linkedListQueue.poll());
        System.out.println("After poll: " + linkedListQueue);

        // ============ BLOCKINGQUEUE ============
        System.out.println("\n=== BLOCKINGQUEUE ===");

        // ArrayBlockingQueue - bounded blocking queue
        BlockingQueue<Integer> blockingQueue = new java.util.concurrent.ArrayBlockingQueue<>(3);
        try {
            blockingQueue.offer(1);
            blockingQueue.offer(2);
            blockingQueue.offer(3);
            // blockingQueue.offer(4); // Would return false if full

            System.out.println("BlockingQueue: " + blockingQueue);
            System.out.println("Take: " + blockingQueue.take());
            System.out.println("After take: " + blockingQueue);

            // Put (blocks if full)
            blockingQueue.put(4);
            System.out.println("After put: " + blockingQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ============ QUEUE OPERATIONS ============
        System.out.println("\n=== QUEUE OPERATIONS ===");

        Queue<Integer> q = new LinkedList<>();

        // add vs offer (add throws exception, offer returns false)
        q.add(1);
        q.offer(2);
        q.offer(3);
        System.out.println("Queue: " + q);

        // element vs peek (element throws exception, peek returns null)
        System.out.println("Element: " + q.element());
        System.out.println("Peek: " + q.peek());

        // remove vs poll (remove throws exception, poll returns null)
        System.out.println("Remove: " + q.remove());
        System.out.println("Poll: " + q.poll());
        System.out.println("After removals: " + q);

        // ============ ITERATING OVER QUEUE ============
        System.out.println("\n=== ITERATING OVER QUEUE ===");

        Queue<String> iterQueue = new ArrayDeque<>(Arrays.asList("A", "B", "C", "D"));

        // For-each (maintains queue order)
        System.out.print("For-each: ");
        for (String element : iterQueue) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Iterator
        System.out.print("Iterator: ");
        Iterator<String> it = iterQueue.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // Using poll (removes elements)
        System.out.print("Polling: ");
        while (!iterQueue.isEmpty()) {
            System.out.print(iterQueue.poll() + " ");
        }
        System.out.println();
    }
}

// ============ CLASS IMPLEMENTING COMPARABLE ============
class Task implements Comparable<Task> {
    private String description;
    private int priority;  // 1 = highest priority

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task: " + description + " (Priority: " + priority + ")";
    }
}