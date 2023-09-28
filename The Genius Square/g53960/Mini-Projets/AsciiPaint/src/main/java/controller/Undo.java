package controller;

/**
 * Represents an Undo command, which undoes the last executed command in the
 * AsciiPaint application. Implements the Command interface.
 *
 * @author Samad
 */
public class Undo implements Command {

    private final Command last;

    /**
     *  * Constructs a new Undo command.
     *
     * @param last the last executed command to be undone
     *
     */
    public Undo(Command last) {
        this.last = last;
    }

    /**
     * Executes the Undo command, which in turn undoes the last executed
     * command.
     *
     */
    @Override
    public void execute() {
        last.undo();
    }

    /**
     * Does nothing for the Undo command, as it cannot be undone itself.
     */
    @Override
    public void undo() {
        System.out.println("This command cannot be undone.");
    }

}
