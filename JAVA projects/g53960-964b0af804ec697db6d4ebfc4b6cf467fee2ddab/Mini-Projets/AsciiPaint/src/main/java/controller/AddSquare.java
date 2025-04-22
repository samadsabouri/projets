package controller;

import Model.AsciiPaint;

/**
 * * Represents a command to add a square to the AsciiPaint model.
 *
 * @author Samad
 */
public class AddSquare implements Command {

    private final AsciiPaint model;
    private final int x;
    private final int y;
    private final double side;
    private final char color;
    private int id;

    /**
     * Creates a new AddSquare command with the given parameters.
     *
     * @param model the AsciiPaint model to add the square to
     * @param x the x-coordinate of the top-left corner of the square
     * @param y the y-coordinate of the top-left corner of the square
     * @param side the length of a side of the square
     * @param color the color of the square
     */
    public AddSquare(AsciiPaint model, int x, int y, double side, char color) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.side = side;
        this.color = color;
    }

    /**
     * Adds the square to the AsciiPaint model.
     */
    @Override
    public void execute() {
        id = model.newSquare(x, y, side, color);
    }

    /**
     * Removes the square from the AsciiPaint model.
     *
     */
    @Override
    public void undo() {
        model.deleteShape(id);
    }
}
