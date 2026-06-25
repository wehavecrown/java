package ExceptionHandling;

import java.io.*;

/**
 * Demonstrates exception handling in Java.
 * Exceptions are events that disrupt normal program flow.
 * Java provides robust exception handling mechanisms.
 */

public class ExceptionHandling {

    public static void main(String[] args) {
        // ============ TRY-CATCH BLOCKS ============
        System.out.println("=== TRY-CATCH ===");

        try {
            // Code that might throw an exception
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            // Handle the specific exception
            System.out.println("Cannot divide by zero!");
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }

        // ============ MULTIPLE CATCH BLOCKS ============
        System.out.println("\n=== MULTIPLE CATCH ===");

        String[] fruits = {"Apple", "Banana", "Orange"};

        try {
            // Multiple exception sources
            int index = 5;
            System.out.println(fruits[index]);  // May throw ArrayIndexOutOfBoundsException
            System.out.println(fruits[0].length());  // May throw NullPointerException

            // Uncomment to cause NullPointerException
            // fruits[1] = null;
            // System.out.println(fruits[1].length());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds!");
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception!");
        } catch (Exception e) {
            // Catch-all for any other exceptions
            System.out.println("General exception: " + e.getClass());
        }

        // ============ FINALLY BLOCK ============
        System.out.println("\n=== FINALLY BLOCK ===");

        try {
            System.out.println("Processing resource...");
            // Simulate resource usage
            if (true) throw new RuntimeException("Something went wrong");
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            // Always executes, regardless of exception
            System.out.println("Finally block - cleaning up resources");
        }

        // ============ TRY-WITH-RESOURCES ============
        // Java 7+ - automatically closes resources
        System.out.println("\n=== TRY-WITH-RESOURCES ===");

        try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
            String line = reader.readLine();
            System.out.println("Read: " + line);
        } catch (FileNotFoundException e) {
            System.out.println("File not found - expected for this demo");
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
        // BufferedReader is automatically closed

        // ============ CHECKED VS UNCHECKED EXCEPTIONS ============
        System.out.println("\n=== CHECKED VS UNCHECKED ===");

        // Checked Exception - must be handled or declared
        try {
            throwCheckedException();  // Must handle or throw
        } catch (IOException e) {
            System.out.println("Handled checked exception: " + e.getMessage());
        }

        // Unchecked Exception - not required to be handled
        throwUncheckedException();  // May cause runtime error if not handled

        // ============ CUSTOM EXCEPTION ============
        System.out.println("\n=== CUSTOM EXCEPTION ===");

        try {
            validateAge(15);  // Will throw custom exception
        } catch (InvalidAgeException e) {
            System.out.println("Custom exception: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
        }

        // ============ EXCEPTION CHAINING ============
        System.out.println("\n=== EXCEPTION CHAINING ===");

        try {
            processData();
        } catch (CustomBusinessException e) {
            System.out.println("Business exception: " + e.getMessage());
            System.out.println("Original exception: " + e.getCause());
            e.printStackTrace();
        }
    }

    // ============ THROWING CHECKED EXCEPTION ============
    public static void throwCheckedException() throws IOException {
        // IOException is a checked exception
        throw new IOException("This is a checked exception");
    }

    // ============ THROWING UNCHECKED EXCEPTION ============
    public static void throwUncheckedException() {
        // RuntimeException is unchecked
        throw new NullPointerException("This is an unchecked exception");
    }

    // ============ USING CUSTOM EXCEPTION ============
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18", "ERR001");
        }
        System.out.println("Valid age: " + age);
    }

    // ============ EXCEPTION CHAINING EXAMPLE ============
    public static void processData() throws CustomBusinessException {
        try {
            // Simulate an error in data processing
            String data = null;
            if (data == null) {
                throw new IllegalArgumentException("Invalid data");
            }
        } catch (IllegalArgumentException e) {
            // Wrap the original exception in a business exception
            throw new CustomBusinessException("Failed to process data", e);
        }
    }
}

// ============ CUSTOM EXCEPTION CLASS ============
class InvalidAgeException extends Exception {
    private final String errorCode;

    public InvalidAgeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

// ============ CUSTOM BUSINESS EXCEPTION ============
class CustomBusinessException extends Exception {
    public CustomBusinessException(String message, Throwable cause) {
        super(message, cause);  // Chaining
    }
}