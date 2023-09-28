package controller;

import Model.AsciiPaint;

/**
 * * The ChangeColor class represents a command to change the color of a shape
 * in an AsciiPaint model.
 *
 * @author Samad
 */
public class ChangeColor implements Command {

    private final AsciiPaint model;
    private final int index;
    private final char color;

    /**
     * Constructs a new ChangeColor command with the given AsciiPaint model,
     * shape index, and color.
     *
     * @param model the AsciiPaint model
     * @param index the index of the shape to change color
     * @param color the new color to apply to the shape
     */
    public ChangeColor(AsciiPaint model, int index, char color) {
        this.model = model;
        this.index = index;
        this.color = color;
    }

    /**
     * Executes the change color command by setting the color of the shape at
     * the given index to the new color.
     */
    @Override
    public void execute() {
        model.setColor(index, color);

    }

    /**
     * Undoes the change color command by setting the color of the shape at the
     * given index back to its previous color.
     */
    @Override
    public void undo() {
        // Retour à la couleur précédente, par exemple:
        char previousColor = model.getAllShapes().get(index).getColor();
        model.setColor(index, previousColor);
    }
}
