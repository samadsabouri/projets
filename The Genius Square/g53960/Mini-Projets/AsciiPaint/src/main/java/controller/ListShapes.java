package controller;

import Model.AsciiPaint;
import Model.Shape;

/**
 * The ListShapes command is responsible for displaying a list of all the shapes
 * in the AsciiPaint model to the console. If the list is empty, a message is
 * printed to inform the user. This command cannot be undone.
 *
 * @author Samad
 */
public class ListShapes implements Command {

    private final AsciiPaint model;

    /**
     * Constructs a new ListShapes command.
     *
     * @param model the AsciiPaint model
     */
    public ListShapes(AsciiPaint model) {

        this.model = model;
    }

    /**
     * Execute the displa; Displays the list of shapes to the console. If the
     * list is empty, prints a message to inform the user.
     */
    @Override
    public void execute() {

        if (model.getShapeSizeList() == 0) {
            System.out.println("The list is empty because there are no shapes!");
        }
        int i = 0;
        for (Shape s : model.getAllShapes()) {
            System.out.print(i + "." + s);
            if(i<model.getShapeSizeList()-1){
                System.out.print( " | ");
            }
            i++;
        }
        System.out.println("");
        
        
    }

    /**
     * Does nothing for ListShapes, as there is no state to undo.
     */
    @Override
    public void undo() {
        System.out.println("This command cannot be undone.");
    }
}
