package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents the output grid of the application. It contains two
 *
 * text fields to display the results of the BMR calculation and calorie
 *
 * expenditure calculation.
 *
 * The class extends the GridPane class from the JavaFX library.
 *
 * @author Samad
 */
public class OutputGrid extends GridPane {

    private final TextField bmr;
    private final TextField calories;

    /**
     * Constructor for OutputGrid class. It initializes the text fields for BMR
     * and calories and calls the method to view the grid.
     */
    public OutputGrid() {
        bmr = new TextField();
        calories = new TextField();
        viewOutputGrid();
    }

    /**
     * Method to view the output grid. It sets the padding, alignment, and gaps
     *
     * between nodes, and adds labels and text fields to the grid.
     */
    private void viewOutputGrid() {
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(15);   // la distance verticale entre chaque ligne
        Label secondTitle = new Label("Résultats");
        secondTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        secondTitle.setUnderline(true);
        add(secondTitle, 0, 0);

        Label bmrLabel = new Label("BMR ");
        add(bmrLabel, 0, 3);
        bmr.setPromptText("Résultats du BMR");
        bmr.setEditable(false);
        add(bmr, 1, 3);

        Label caloriesLabel = new Label("Calories ");
        add(caloriesLabel, 0, 4);
        calories.setPromptText("Dépense en calories");
        calories.setEditable(false);
        add(calories, 1, 4);

    }

    /**
     * Sets text of BMRField
     *
     * @param BMRResult the given bmr text
     */
    public void setBMRText(String BMRResult) {
        this.bmr.setText(BMRResult);
    }

    /**
     * Sets text of caloriesField
     *
     * @param caloriesResult the given calories
     */
    public void setCaloriesText(String caloriesResult) {
        this.calories.setText(caloriesResult);
    }

    /**
     * Returns the BMR text field.
     *
     * @return the BMR text field
     */
    public TextField getBmr() {
        return bmr;
    }

    /**
     * Returns the calories text field.
     *
     * @return the calories text field
     */
    public TextField getCalories() {
        return calories;
    }

}
