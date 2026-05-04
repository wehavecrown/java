import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // Calculate area of a rectangle

        double width = 0;
        double length = 0;
        double area = 0;

        Scanner scanner = new Scanner(System.in);


        System.out.print("What is the width? ");
        width = scanner.nextDouble();

        System.out.print("What is the length? ");
        length = scanner.nextDouble();

        area = width * length;

        System.out.println("The area of the rectangle is : " + area);

        scanner.close();
    }
}
