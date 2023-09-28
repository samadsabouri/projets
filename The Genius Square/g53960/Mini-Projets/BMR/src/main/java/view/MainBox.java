package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.LifeStyle;

/**
 * The MainBox class represents the main container of the user interface. It
 * contains the DataBox and two buttons: "Calculer" and "Clear".
 *
 * @author Samad
 */
public final class MainBox extends VBox {

    private final DataBox databox;
    private final Button buttonCalculer;
    private final Button buttonClear;

    /**
     * Constructor for the MainBox class. Initializes the DataBox and the two
     * buttons.
     *
     */
    public MainBox() {
        databox = new DataBox();
        buttonCalculer = new Button("Calcul de BMR");
        buttonClear = new Button("Clear");
        printButton();
        getChildren().addAll(databox, buttonCalculer, buttonClear);
        setAlignment(Pos.CENTER); //centrér le button

    }

    /**
     * Gets the "Calculer" button.
     *
     * @return The "Calculer" button.
     */
    public Button getButton() {
        return buttonCalculer;
    }

    /**
     * Gets the "Clear" button.
     *
     * @return The "Clear" button.
     */
    public Button getButtonClear() {
        return buttonClear;
    }

    /**
     * Gets the output BMR TextField from the DataBox.
     *
     * @return The output BMR TextField.
     */
    public TextField Bmr() {
        return databox.getOutputBmr();
    }

    /**
     * Gets the output Calories TextField from the DataBox.
     *
     * @return The output Calories TextField.
     */
    public TextField Calories() {
        return databox.getOutputCalories();
    }

    /**
     * Gets the value of the "Femme" RadioButton from the DataBox.
     *
     * @return True if the "Femme" RadioButton is selected, false otherwise.
     */
    public boolean getIsWomenSelected() {
        return databox.isInputGridWomanSelected();
    }

    /**
     * Gets the input Size TextField from the DataBox.
     *
     * @return The input Size TextField.
     */
    public TextField Size() {
        return databox.getInputSize();
    }

    /**
     * Gets the input Weight TextField from the DataBox.
     *
     * @return The input Weight TextField.
     */
    public TextField Weight() {
        return databox.getInputWeight();
    }

    /**
     * Gets the input Year TextField from the DataBox.
     *
     * @return The input Year TextField.
     */
    public TextField getInputYear() {
        return databox.getInputYear();
    }

    /**
     * Gets the input Lifestyle ChoiceBox from the DataBox.
     *
     * @return The input Lifestyle ChoiceBox.
     */
    public ChoiceBox<LifeStyle> Lifestyle() {
        return databox.getInputLifestyle();
    }

    /**
     * Sets the minimum size and padding for the "Calculer" and "Clear" buttons.
     *
     */
    private void printButton() {
        buttonCalculer.setMinSize(200, 20);
        buttonCalculer.setPadding(new Insets(10));
        buttonClear.setMinSize(200, 20);
        buttonClear.setPadding(new Insets(10));

    }

}
