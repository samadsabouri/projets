package controller;

import Model.AsciiPaint;

/**
 * * Represents a command to add a line to the ASCII paint model and the
 * ability to undo it.
 *
 * @author Samad
 */
public class AddLine implements Command {

    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;
    private final char color;
    private final AsciiPaint model;
    private int id;

    /**
     *
     * @param model the ASCII paint model to add the line to.
     * @param x1 the x-coordinate of the starting point of the line.
     * @param x2 the x-coordinate of the ending point of the line.
     * @param y1 the y-coordinate of the starting point of the line.
     * @param y2 the y-coordinate of the ending point of the line.
     * @param color the color of the line.
     */
    public AddLine(AsciiPaint model, int x1, int x2, int y1, int y2, char color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.model = model;

    }

    /**
     * Executes the `AddLine` command by adding a line to the ASCII paint model.
     * The method also saves the ID of the newly added shape for undoing the
     * command later.
     */
    @Override
    public void execute() {
        id = model.newLine(x1, y1, x2, y2, color);
    }

    /**
     *     * Undoes the `AddLine` command by removing the line from the ASCII paint
     * model.
     *
     */
    @Override
    public void undo() {
        model.deleteShape(id);
    }

}
