public class ControlFlow {

    public static void main(String[] args) {

        // IF-ELSE STATEMENTS
        int score = 85;
        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else if (score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade: " + grade);

        // SWITCH STATEMENTS

        // enhanced switch with arrow syntax
        String day = "MONDAY";
        String dayType = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Invalid day";
        };
        System.out.println(day + " is a " + dayType);

        // Traditional switch with break
        int month = 3;
        String monthName;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            default:
                monthName = "Unknown";
        }
        System.out.println("Month: " + monthName);

        // LOOPS

        // FOR Loop
        System.out.println("For loop:");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Enhanced FOR loop
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("For-each loop:");

        for (int num : numbers){
            System.out.print(num);
        }
        System.out.println();

        // WHILE loop
        System.out.println("While loop:");
        int i = 0;
        while (i < 5) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();

        // DO WHILE loop
        System.out.println("Do-while loop:");
        int j = 0;
        do {
            System.out.print(j + " ");
            j++;
        } while (j < 5);
        System.out.println();

        // BREAK AND CONTINUE
        System.out.println("Break and continue:");
        for (int k = 0; k < 10; k++){
            if (k == 3) continue; // skip iteration 3
            if (k == 7) break; // exit loop at 7
            System.out.print(k + " ");
        }
        System.out.println();


    }
}
