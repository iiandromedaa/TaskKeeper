import java.io.IOException;
import java.util.Scanner;

public final class CLUtility {

    //my little utility class from last semester
    //its BACK
    //AGAIN

    private CLUtility() {
    }

    /**
     * @param prompt String that prompts the user to enter an integer input
     * @param bound Highest value that can be inputted
     * @param scanscan The scanner that will be used (duh)
     * @return valid input for switch statement
     */
    public static int switchValidator(String prompt, int bound, Scanner scanscan) {
        System.out.println(prompt);
        while(true){
            try {
                int inty = Integer.parseInt(scanscan.next());
                if (inty <= bound && inty > -1)
                    return inty;
                System.out.println("Invalid input.\n"+prompt);
            } catch(NumberFormatException ne) {
                System.out.println("Invalid input.\n"+prompt);
            }
        }
    }

    /**
     * clears the terminal, should work on both windows and unix-likes
     */
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("Clear failed :( " + e);
        }  
    }

}