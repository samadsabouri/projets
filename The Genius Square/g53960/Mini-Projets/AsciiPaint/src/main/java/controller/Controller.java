package controller;

import Model.AsciiPaint;
import View.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Controller class is responsible for controlling the flow of the program.
 * It creates an instance of AsciiPaint and View classes, and initializes them
 * with a size It then calls methods on AsciiPaint to add various shapes, and
 * finally calls the View to display the resulting ASCII art.
 *
 * @author Samad
 */
public class Controller {

    private final AsciiPaint model;
    private final View view;
    private final List<Command> history;
    private final List<Command> redoHistory;

    /**
     * Constructs a new Controller object and initializes an instance of
     * AsciiPaint and View classes. The default size of AsciiPaint is set to
     * 20x20.
     */
    public Controller() {
        this.model = new AsciiPaint(100, 100);
        this.view = new View();
        history = new ArrayList();
        redoHistory = new ArrayList();
    }

    /**
     * This method starts the application and takes user input as commands,
     * builds them using CommandFactory, executes them, and adds them to the
     * history. It continues to prompt the user for input until the application
     * is terminated.
     */
    public void start() {
        Scanner clavier = new Scanner(System.in);
        view.displayWelcome();
        while (true) {
            try {
               view.displayMessage("Please enter a valid command: ");
                String input = clavier.nextLine();
                Command command = CommandFactory.build(model, input, history, redoHistory);
                history.add(command);
                command.execute();
                view.displayMessage("Done!");
            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }

    }
}
