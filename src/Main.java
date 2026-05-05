import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //MAD LIBS GAME

        Scanner scanner = new Scanner(System.in);

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;

        System.out.print("Enter an adjective: ");
        adjective1 = scanner.nextLine();

        System.out.print("Enter a noun: ");
        noun1 = scanner.nextLine();

        System.out.print("Enter an adjective: ");
        adjective2 = scanner.nextLine();

        System.out.print("Enter a verb end with -ing: ");
        verb1 = scanner.nextLine();

        System.out.print("Enter an adjective: ");
        adjective3 = scanner.nextLine();

        System.out.println("\nToday i went to a " + adjective1 + " zoo.");
        System.out.println("In an exhibit, i saw a " + noun1 + ".");
        System.out.println(noun1 + " was " + adjective2 + " and " + verb1 + "!");
        System.out.println("I was " + adjective3 + "!");

        scanner.close();
    }
}
