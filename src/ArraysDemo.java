import java.util.Arrays;

public class ArraysDemo {

    static void main(String[] args) {

        // ARRAY DECLARATION

        // Method 1: Declaration and initialization in one step
        int[] numbers = {10, 20, 30, 40, 50};

        // Method 2: Declaration with size and separate initialization
        int[] moreNumbers = new int[5];
        moreNumbers[0] = 100;
        moreNumbers[1] = 200;

        // Method 3: Using 'new' and initializer
        int[] anotherArray = new int[]{1, 2, 3, 4, 5};

        // ACCESSING ELEMENTS
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);

        // TRAVERSING ARRAYS

        // Traditional for loop
        System.out.println("Traditional for loop");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Enhanced for loop
        System.out.println("Enhanced for loop:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // MULTI-DIMENSIONAL ARRAYS

        // 2D array (matrix)
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Matrix elements:");
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        // Jagged array (array with different lengths)
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1, 2};
        jagged[1] = new int[]{3, 4, 5};
        jagged[2] = new int[]{6, 7, 8, 9};

        // ARRAY UTILITIES

        // Sorting
        int[] unsorted = {5, 2, 8, 1, 9};
        Arrays.sort(unsorted);
        System.out.println("Sorted: " + Arrays.toString(unsorted));

        // Binary search (requires sorted array)
        int index = Arrays.binarySearch(unsorted, 8);
        System.out.println("Index of 8: " + index);

        // Fill with value
        int[] fillArray = new int[5];
        Arrays.fill(fillArray, 42);
        System.out.println("Filled array: " + Arrays.toString(fillArray));

        // Copy arrays
        int[] copied = Arrays.copyOf(numbers, numbers.length);
        int[] rangeCopy = Arrays.copyOfRange(numbers, 1, 4);

        //  Comparing arrays
        boolean isEqual = Arrays.equals(numbers, copied);
        System.out.println("Arrays equal: " + isEqual);

        // VARARGS - VARIABLE ARGUMENTS
        // Can be used to pass any number of arguments
        printNumbers(1, 2, 3);
        printNumbers(10, 20, 30, 40, 50);

        // ARRAY OF OBJECTS
        String[] names = {"Alice", "Bob", "Charkie"};
        //Arrays of objects can be sorted using Comparable or Comparator
        Arrays.sort(names);
        System.out.println("Sorted names: " + Arrays.toString(names));
    }

    /**
     * Demonstrates varargs parameter - can accept any number of int arguments
     * @param numbers Variable number of integers
     */
    static void printNumbers(int... numbers) {
        System.out.println("Varargs: " + Arrays.toString(numbers));
    }
}