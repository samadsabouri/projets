package controller;

/**
 * A class that represents the Help command in the AsciiPaint application. When
 * executed, it prints a help message to the console. This command cannot be
 * undone.
 *
 * @author Samad
 */
public class Help implements Command {

    private final String message;

    /**
     * Constructs a new Help command with the given help message.
     *
     * @param message the help message to be printed to the console
     */
    public Help(String message) {
        this.message = message;
    }

    /**
     * Executes the Help command by printing the help message to the console.
     *
     */
    @Override
    public void execute() {
        System.out.println(message);
    }

    /**
     * This command cannot be undone. When undo() is called, it prints a message
     * indicating that the command cannot be undone.
     *
     */
    @Override
    public void undo() {
        System.out.println("This command cannot be undone");
    }

}
