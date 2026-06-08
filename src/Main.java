public class Main {

    public static void main(String[] args) {

        // NESTED WHILE LOOP

        int i = 1;

        while (i <= 4) {
            System.out.println("Hi " + i);

            int j = 1;
            while (j <= 3) {
                System.out.println("Hello " + j);
                j++;
            }

            i++;
        }

        System.out.println("the value of i is: " + i);
    }
}

