package View;

/**
 * This class represents the view of the AsciiPaint application. It is
 * responsible for displaying the current state of the
 *
 * drawing in ASCII format.
 *
 * @author Samad
 */
public class View {

    public View() {
    }

    /**
     * Displays a welcome message.
     */
    public void displayWelcome() {
        System.out.println("================================================================");
        System.out.println("                 Welcome to AsciiPaint!");
        System.out.println("             Realized by Abdessamad Sabouri 53960                  ");

        System.out.println("    If you don't know the commands of the game, type : help      ");
        System.out.println("================================================================");

    }

    /**
     * Displays a Done message.
     */
    public void displayMessage(String s) {
        System.out.println(s);
    }
    
   
}
