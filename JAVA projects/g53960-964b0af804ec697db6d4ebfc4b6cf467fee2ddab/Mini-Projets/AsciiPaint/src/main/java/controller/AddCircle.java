package controller;

import Model.AsciiPaint;

/**
 * Command class to add a circle to the ASCII paint model.
 *
 * @author Samad
 */
public class AddCircle implements Command {

    private final AsciiPaint model;
    private int id;
    private final int x;
    private final int y;
    private final double radius;
    private final char color;

    /**
     * Constructor for AddCircle command.
     *
     * @param model the ASCII paint model to which the circle will be added
     * @param x the x-coordinate of the center of the circle
     * @param y the y-coordinate of the center of the circle
     * @param radius the radius of the circle
     * @param color the color of the circle
     */
    public AddCircle(AsciiPaint model, int x, int y, double radius, char color) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    /**
     *  * Adds the circle to the model and stores the id of the newly created
     * shape.
     *
     */
    @Override
    public void execute() {
        id = model.newCircle(x, y, radius, color);
    }

    /**
     *  * Removes the circle from the model using its stored id.
     *
     */
    @Override
    public void undo() {
        model.deleteShape(id);
    }

}
