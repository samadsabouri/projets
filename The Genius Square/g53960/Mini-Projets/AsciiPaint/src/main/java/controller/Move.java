package controller;

import Model.AsciiPaint;

/**
 * A command to move a shape in the AsciiPaint model.
 *
 * @author Samad
 */
public class Move implements Command {

    private final int x;
    private final int y;
    private final AsciiPaint model;
    private int index;

    /**
     * Constructs a new Move command.
     *
     * @param model the AsciiPaint model
     * @param x the amount to move in the x direction
     * @param y the amount to move in the y direction
     */
    public Move(AsciiPaint model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;

    }

    /**
     * Executes the move command by calling the model's moveShape method.
     *
     */
    @Override
    public void execute() {
        model.moveShape(index, x, y);
    }

    /**
     * Undoes the move command by calling the model's moveShape method with the
     * negative of the x and y values used in execute.
     */
    @Override
    public void undo() {
        model.moveShape(index, -x, -y);
    }

}
