package controller;

/**
 * The Command interface defines two methods, execute and undo, which
 * encapsulate the operations to be performed by the
 *
 * commands executed by the controller. The execute method should perform the
 * action specified by the command, while the
 *
 * undo method should undo the action performed by the execute method, if
 * possible.
 *
 * @author Samad
 */
public interface Command {

    /**
     * Executes the command, performing the operation specified by the command.
     */
    void execute();

    /**
     * Undoes the operation performed by the execute method, if possible.
     */
    void undo();
}
