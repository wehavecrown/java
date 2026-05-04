public class Main {

    public static void main(String[] args){

        // VARIABLES

        // INT
        int age = 30;
        int year = 2026;
        int quantity = 1;
        // DOUBLE
        double price = 19999.99;
        double gpa = 3.5;
        double temperature = 12.5;
        // CHAR
        char grade = 'A';
        char symbol = '!';
        char currency = '$';
        // BOOLEAN
        boolean isStudent = true;
        boolean forSale = false;
        boolean isOnline = true;


        // STRING
        String name = "Adelani";
        String food = "Pizza";
        String email= "dake123@gmail.com";
        String car = "Mustang";
        String color = "red";


        System.out.println("Your choice is" + " " + color + " " + year + " " + car);
        System.out.println("The price is: " + currency + price);

        if(forSale){
            System.out.println("There is a " + car + " for sale");
        } else {
            System.out.println("The " + car + " is not for sale");
        }

    }

}