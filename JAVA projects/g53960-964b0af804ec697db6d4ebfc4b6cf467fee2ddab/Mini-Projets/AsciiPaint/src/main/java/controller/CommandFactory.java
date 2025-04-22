package controller;

import Model.AsciiPaint;
import View.View;
import java.util.ArrayList;
import java.util.List;

/**
 * The CommandFactory class is responsible for creating Command objects based on
 * the user's input.
 *
 * It uses a static build method to parse the input string and create the
 * appropriate Command object
 *
 * based on the first token of the string. The CommandFactory class also handles
 * error checking and
 *
 * throws IllegalArgumentExceptions if the input is invalid or incomplete.
 *
 * @author Samad
 */
public class CommandFactory {

    /**
     *
     * Builds a Command object based on the user's input.
     *
     * @param model The AsciiPaint model.
     * @param input The user's input.
     * @param history A list of previously executed commands.
     * @param redoHistory A list of previously undone commands.
     * @return A Command object that represents the user's input.
     * @throws IllegalArgumentException If the input is invalid or incomplete.
     */
    public static Command build(AsciiPaint model, String input, List<Command> history, List<Command> redoHistory) throws IllegalArgumentException {

        String[] token = input.split(" ");
        Command command = null;

        switch (token[0]) {
            case "circle":
                if (token.length < 5) {
                    throw new IllegalArgumentException("The 'circle' command requires 5 arguments: x, y, radius, color");
                }
                int x = Integer.parseInt(token[1]);
                int y = Integer.parseInt(token[2]);
                double radius = Double.parseDouble(token[3]);
                char color = token[4].charAt(0);
                command = new AddCircle(model, x, y, radius, color);
                break;
            case "rectangle":
                if (token.length < 6) {
                    throw new IllegalArgumentException("The 'rectangle' command requires 6 arguments: x, y, width, height, color");
                }
                x = Integer.parseInt(token[1]);
                y = Integer.parseInt(token[2]);
                double width = Double.parseDouble(token[3]);
                double height = Double.parseDouble(token[4]);
                color = token[5].charAt(0);
                command = new AddRectangle(model, x, y, width, height, color);
                break;
            case "square":
                if (token.length < 5) {
                    throw new IllegalArgumentException("The 'square' command requires 5 arguments: x, y, side, color");
                }
                x = Integer.parseInt(token[1]);
                y = Integer.parseInt(token[2]);
                double side = Double.parseDouble(token[3]);
                color = token[4].charAt(0);
                command = new AddSquare(model, x, y, side, color);
                break;
            case "line":
                if (token.length < 6) {
                    throw new IllegalArgumentException("The 'line' command requires 6 arguments: x1, y1, x2, y2, color");
                }
                int x1 = Integer.parseInt(token[1]);
                int y1 = Integer.parseInt(token[2]);
                int x2 = Integer.parseInt(token[3]);
                int y2 = Integer.parseInt(token[4]);
                color = token[5].charAt(0);
                command = new AddLine(model, x1, y1, x2, y2, color);
                break;
            case "move":
                if (token.length < 3) {
                    throw new IllegalArgumentException("The 'move' command requires 3 arguments: x, y");
                }
                x = Integer.parseInt(token[1]);
                y = Integer.parseInt(token[2]);
                command = new Move(model, x, y);
                break;
            case "undo":
                if (history.isEmpty()) {
                    throw new IllegalArgumentException("Cannot undo, no command has been executed");
                }
                Command last = history.remove(history.size() - 1);
                command = new Undo(last);
                redoHistory.add(last);
                break;
            case "redo":
                if (redoHistory.isEmpty()) {
                    throw new IllegalArgumentException("Cannot redo, no command has been undone");
                }
                Command toRedo = redoHistory.remove(redoHistory.size() - 1);
                command = new Redo(toRedo, history, redoHistory); 
                break;
            case "color":
                if (token.length < 3) {
                    throw new IllegalArgumentException("The 'color' command requires 2 arguments: index, color");
                }
                int index = Integer.parseInt(token[1]);
                color = token[2].charAt(0);
                command = new ChangeColor(model, index, color);
                break;
            case "delete":
                if (token.length < 2) {
                    throw new IllegalArgumentException("The 'delete' command requires an argument: the index of the shape to delete");
                }
                int indexToDelete = Integer.parseInt(token[1]);
                command = new DeleteShape(model, indexToDelete);
                break;
            case "group":
                if (token.length < 2) {
                    throw new IllegalArgumentException("The 'group' command requires at least one argument: the indexes of the shapes to group");
                }
                List<Integer> shapeIndexes = new ArrayList<>();
                for (int i = 1; i < token.length; i++) {
                    shapeIndexes.add(Integer.parseInt(token[i]));
                }
                command = new Group(model, shapeIndexes);
                break;
            case "ungroup":
                if (token.length != 2) {
                    throw new IllegalArgumentException("The 'ungroup' command requires one argument: the index of the shape to ungroup");
                }
                index = Integer.parseInt(token[1]);
                command = new UnGroup(model, index);
                break;
            case "list":
                command = new ListShapes(model);
                break;
            case "show":
                command = new Show(model);
                break;
            case "help":
                String helpMessage = "List of available commands:\n"
                        + "- circle: add a circle\n"
                        + "- rectangle: add a rectangle\n"
                        + "- square: add a square\n"
                        + "- line: add a line\n"
                        + "- move: move a shape\n"
                        + "- delete: delete a shape\n"
                        + "- color: change the color of a shape\n"
                        + "- list: show the list of shapes\n"
                        + "- show: show the drawing\n"
                        + "- undo: undo the last command\n"
                        + "- redo: redo the last undone command\n"
                        + "- help: show the list of commands\n";
                command = new Help(helpMessage);
                break;

            default:
                throw new IllegalArgumentException("Invalid command ");
        }
        return command;

    }

}
