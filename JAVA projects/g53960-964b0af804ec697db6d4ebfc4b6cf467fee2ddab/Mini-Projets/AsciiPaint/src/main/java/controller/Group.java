package controller;

import Model.AsciiPaint;
import java.util.List;

/**
 * The Group class represents a command to group selected shapes in an
 * AsciiPaint model. This command implements the Command interface.
 *
 * @author Samad
 */
public class Group implements Command {

    /**
     * Executes the group command.
     */
    private final AsciiPaint model;
    private final List<Integer> shapeIndexes;

    public Group(AsciiPaint model, List<Integer> shapeIndexes) {
        this.model = model;
        this.shapeIndexes = shapeIndexes;
    }
/**
 * Execute the group command by calling the method groupShape from AsciiPaint
 */
    @Override
    public void execute() {
        model.groupShape(shapeIndexes);
    }

    /**
     * Undoes the group command.
     */
    @Override
    public void undo() {
       
    }

}
