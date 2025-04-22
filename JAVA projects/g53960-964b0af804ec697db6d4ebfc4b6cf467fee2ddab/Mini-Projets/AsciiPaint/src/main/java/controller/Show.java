package controller;

import Model.AsciiPaint;

/**
 *
 * @author Samad
 */
public class Show implements Command {

    private final AsciiPaint model;

    /**
     *
     * @param model
     */
    public Show(AsciiPaint model) {

        this.model = model;
    }

    @Override
    public void execute() {
        model.getDrawing().asAscii();
    }

    @Override
    public void undo() {
        throw new IllegalArgumentException("This command cannot be undone.");
    }

}
