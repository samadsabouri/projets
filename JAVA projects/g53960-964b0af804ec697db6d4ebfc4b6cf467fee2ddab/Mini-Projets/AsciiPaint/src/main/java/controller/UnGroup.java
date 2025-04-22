package controller;

import Model.AsciiPaint;
import Model.Shape;
import Model.ShapeGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * The UnGroup command is used to ungroup a group of shapes in the AsciiPaint
 * model.
 *
 * This command has no effect if the selected shape is not a group.
 *
 * This command cannot be undone.
 *
 * @author Samad
 */
public class UnGroup implements Command {

    private final AsciiPaint model;
    private final int index;

    /**
     * Constructs a new UnGroup command with the given model and index of the
     * selected shape.
     *
     * @param model the AsciiPaint model
     * @param index the index of the selected shape
     */
    public UnGroup(AsciiPaint model, int index) {
        this.model = model;
        this.index = index;
    }

    /**
     * Executes the UnGroup command by ungrouping the selected shape in the
     * AsciiPaint model. This command has no effect if the selected shape is not
     * a group.
     */
    @Override
    public void execute() {
        ShapeGroup group = (ShapeGroup) model.getDrawing().getShapes().get(index);
        if (group.isGroup()) {
            model.ungroupShape(index);
        }
    }

    /**
     * This method ungroups the selected shape in the AsciiPaint model. If the
     * selected shape is not a group, this method does nothing.
     */
    @Override
    public void undo() {
        Shape shape = model.getDrawing().getShapes().get(index);
        if (shape instanceof ShapeGroup) {
            ShapeGroup group = (ShapeGroup) shape;
            List<Integer> indexes = new ArrayList<>();
            for (Shape child : group.getShapes()) {
                int i = model.getDrawing().getShapes().indexOf(child);
                if (i != -1) {
                    indexes.add(i);
                }
            }
            model.groupShape(indexes);
        }
    }
}
