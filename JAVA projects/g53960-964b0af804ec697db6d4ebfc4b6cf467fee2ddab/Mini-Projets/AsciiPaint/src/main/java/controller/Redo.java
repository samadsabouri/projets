package controller;

import java.util.List;

/**
 * A Command that represents the redo action. This command does not modify the
 * state of the AsciiPaint model, but is used to call the redo() method on the
 * history of commands.
 *
 * The Redo command is a meta-command, which means that it contains another
 * command that should be executed when redo() is called.
 *
 * @author Samad
 */
public class Redo implements Command {

    private final Command toRedo;
    private final List<Command> history;
    private final List<Command> redoHistory;

    /**
     * Constructs a new Redo command with the command to redo, the command
     * history, and the redo command history.
     *
     * @param toRedo the command to redo
     * @param history the command history
     * @param redoHistory the redo command history
     */
    public Redo(Command toRedo, List<Command> history, List<Command> redoHistory) {
        this.toRedo = toRedo;
        this.history = history;
        this.redoHistory = redoHistory;
    }

    /**
     * Executes the Redo command by redoing the previously undone command.
     *
     * This command has no effect if there are no commands to redo.
     */
    @Override
    public void execute() {
        toRedo.execute();
        history.add(toRedo);
        redoHistory.remove(this);
        redoHistory.add(toRedo);
        System.out.println("exec");
    }

    /**
     * This command cannot be undone.
     */
    @Override
    public void undo() {
        toRedo.undo();
        history.remove(toRedo);
        redoHistory.add(this);
    }
}
