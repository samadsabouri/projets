package controller;

import Model.AsciiPaint;
import Model.Shape;

/**
 * This class represents a command to delete a shape from the model's drawing.
 * It implements the Command interface.
 *
 * @author Samad
 */
public class DeleteShape implements Command {

    private final AsciiPaint model;
    private final int index;
    private Shape deletedShape;

    /**
     *
     * Constructs a new DeleteShape command.
     *
     * @param model the model containing the drawing to modify
     * @param index the index of the shape to delete in the drawing's list of
     * shapes
     */
    public DeleteShape(AsciiPaint model, int index) {
        this.model = model;
        this.index = index;
    }

    /**
     * Executes the DeleteShape command by removing the shape at the specified
     * index from the drawing. If the index is invalid, an
     * IllegalArgumentException is thrown.
     */
    @Override
    public void execute() {
        if (index < 0 || index >= model.getDrawing().getShapes().size()) {
            throw new IllegalArgumentException("L'index spécifié est invalide");
        }
        // model.deleteShape(index);
        deletedShape = model.getDrawing().getShapes().remove(index);
    }

    /**
     * * Undoes the DeleteShape command by adding the shape back to the
     * drawing. This method is not implemented since deleting a shape cannot be
     * undone in this application.
     */
    @Override
    public void undo() {
        if (deletedShape == null) {
            return; // Nothing to undo
        }
        // Add the deleted shape back to the drawing at the same index it was removed from
        model.getDrawing().getShapes().add(index, deletedShape);
        deletedShape = null; // Reset the deletedShape field
    }

}
