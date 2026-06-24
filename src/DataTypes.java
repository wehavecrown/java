public class DataTypes {

    public static void main(String[] args) {

        // PRIMITIVE DATA TYPES

        // Integer
        byte byteVar = 127; // 8-bit
        short  shortVar = 32767; // 16-bit
        int intVar = 2_147_483_647; // 32-bit
        long longVar = 9_223_372_036_854_775_807L; // 64-bit, L suffix

        // Floating-point types (decimal numbers)
        float floatVar = 3.14159f; // 32-bit, f suffix
        double doubleVar = 3.141592653589793; // 64-bit, default for decimals

        // Character type (single Unicode character)
        char charVar = 'A'; // 16-bit, uses single quotes

        // Boolean type (true/false)
        boolean boolVar = true; // 1-bit

        // REFERENCE DATA TYPES

        String strVar = "Hello Java"; // String is a class, not a primitive

        // Arrays are reference types
        int[] intArray = {1, 2, 3, 4, 5};

        // TYPE INFERENCE
        var inferredInt = 42; //inferred as int
        var inferredString = "Hello"; //inferred as String
        var inferredArray = new int[]{1, 2, 3};

        // TYPE CONVERSION

        // Implicit casting
        int intValue = 100;
        long longValue = intValue;
        double doubleValue = intValue;

        // Explicit casting
        double pi = 3.14159;
        int intPi = (int) pi;

        // STRING OPERATIONS
        String firstName = "John";
        String lastName = "Doe";

        // Concatenation
        String fullName = firstName + " " + lastName;
        System.out.println("Full name: " + fullName);

        // String methods
        System.out.println("Length: " + fullName.length());
        System.out.println("Uppercase: " + fullName.toUpperCase());
        System.out.println("Contains 'John': " + fullName.contains("John"));

        // WRAPPER CLASSES
        Integer intWrapper = 43; // Auto-boxing (primitive to  object)
        int unboxed = intWrapper; // Auto-unboxing (object to primitive)

        // Useful wrapper methods
        int parsed = Integer.parseInt("123"); // String to int
        String intString = Integer.toString(456); // int to String
    }
}
