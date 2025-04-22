package controller;

import Model.AsciiPaint;

/**
 * The AddRectangle class is a command that adds a new rectangle to the model
 * and stores its ID. The rectangle is specified by its coordinates, width,
 * height and color.
 *
 * @author Samad
 */
public class AddRectangle implements Command {

    private final AsciiPaint model;
    private final int x;
    private final int y;
    private final double width;
    private final double height;
    private final char color;
    private int id;

    /**
     * Constructs an AddRectangle command with the given parameters.
     *
     * @param model the AsciiPaint model to add the rectangle to.
     * @param x the x-coordinate of the top left corner of the rectangle.
     * @param y the y-coordinate of the top left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color the color of the rectangle.
     */
    public AddRectangle(AsciiPaint model, int x, int y, double width, double height, char color) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Executes the AddRectangle command by adding a new rectangle to the model
     * and storing its ID.
     */
    @Override
    public void execute() {
        id = model.newRectangle(x, y, width, height, color);
    }

    /**
     * Undoes the AddRectangle command by deleting the rectangle with the stored
     * ID from the model.
     *
     *
     */
    @Override
    public void undo() {
        model.deleteShape(id);
    }

}
