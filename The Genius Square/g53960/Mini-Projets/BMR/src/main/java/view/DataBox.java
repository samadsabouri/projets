package view;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.LifeStyle;

/**
 * The DataBox class represents a graphical user interface component that
 * displays input and output fields for user data
 *
 * related to calculating BMR and daily caloric needs. It extends the HBox
 * class.
 *
 * @author Samad
 */
public class DataBox extends HBox {

    private final InputGrid inputGrid;
    private final OutputGrid outputGrid;

    /**
     * Constructs a new DataBox object by creating an instance of the InputGrid
     * and OutputGrid classes and adding them to the HBox.
     */
    public DataBox() {
        inputGrid = new InputGrid();
        outputGrid = new OutputGrid();
        getChildren().addAll(inputGrid, outputGrid);
    }

    /**
     * Returns the TextField object that displays the output BMR.
     *
     * @return the output BMR TextField object
     */
    public TextField getOutputBmr() {
        return outputGrid.getBmr();
    }

    /**
     * Returns the TextField object that displays the output daily caloric
     * needs.
     *
     * @return the output daily caloric needs TextField object
     */
    public TextField getOutputCalories() {
        return outputGrid.getCalories();
    }

    /**
     * Returns a boolean indicating if the input radio button for selecting
     * gender is set to female.
     *
     * @return true if the input radio button for selecting gender is set to
     * female, false if set to male
     */
    public boolean isInputGridWomanSelected() {
        return inputGrid.isWomanSelected();
    }

    /**
     * Returns the TextField object that displays the user's height input.
     *
     * @return the height TextField object
     */
    public TextField getInputSize() {
        return inputGrid.getSize();
    }

    /**
     * Returns the TextField object that displays the user's weight input.
     *
     * @return the weight TextField object
     */
    public TextField getInputWeight() {
        return inputGrid.getWeight();
    }

    /**
     * Returns the TextField object that displays the user's birth year input.
     *
     * @return the birth year TextField object
     */
    public TextField getInputYear() {
        return inputGrid.getYear();
    }

    /**
     * Returns the ChoiceBox object that displays the user's lifestyle input.
     *
     * @return the lifestyle ChoiceBox object
     */
    public ChoiceBox<LifeStyle> getInputLifestyle() {
        return inputGrid.getLifestyle();
    }
}
