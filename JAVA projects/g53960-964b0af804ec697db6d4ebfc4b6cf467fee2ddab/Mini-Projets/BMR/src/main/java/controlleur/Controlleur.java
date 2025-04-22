package controlleur;

import java.util.Objects;
import model.LifeStyle;
import model.Model;
import view.View;

/**
 * The Controller class acts as an intermediary between the Model and View
 * classes. It receives input from the user via the View, processes it using the
 * Model, and updates the View with the results.
 *
 * @author Samad
 */
public class Controlleur {

    private final Model model;
    private final View view;

    /**
     * Constructs a Controller object with the given Model and View objects.
     *
     * @param model the Model object to use
     * @param view the View object to use
     */
    public Controlleur(Model model, View view) {
        this.model = Objects.requireNonNull(model, "Pas de null");
        this.view = Objects.requireNonNull(view, "Pas de null");

    }

    /**
     * Starts the program by adding the View as an observer of the Model and
     * initializing the View.
     */
    public void start() {
        model.addObserver(view);
        view.PrintScreen();
        view.initialize();

    }

    /**
     * Calculates the BMR and calorie needs based on the user's input, using the
     * Model. If the input data is invalid, displays an alert in the View.
     */
    public void calculate() {
        if (isValidParameters()) {
            view.setStyleColorBlack();
            int size = Integer.parseInt(view.getSize().getText());
            int weight = Integer.parseInt(view.getWeight().getText());
            int year = Integer.parseInt(view.getYear().getText());
            boolean womenSelected = view.isWomanSelected();
            LifeStyle lifeStyle = view.getLifestyle().getValue();

            this.model.calculateBMR(size, weight, year, womenSelected);
            this.model.calculateCalories(lifeStyle);

        } else {
            view.setStyleColorRedFail();
            view.createAlert();

        }
    }

    /**
     * Checks if the data is valid
     *
     * @return true if the data is correct, otherwise false
     */
    public boolean isValidParameters() {
        String size = view.getSize().getText();
        String weight = view.getWeight().getText();
        String year = view.getYear().getText();

        return size.matches("\\d+")
                && Integer.parseInt(size) > 0
                && weight.matches("\\d+")
                && Integer.parseInt(weight) > 0
                && year.matches("\\d+")
                && Integer.parseInt(year) > 0;
    }

    /**
     * Resets data and results fields
     */
    public void clearDataResult() {
        view.resetInputData();
        view.resetOutputData();
    }
}
