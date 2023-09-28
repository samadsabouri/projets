package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LifeStyle;

/**
 * The View class represents the user interface of the BMR calculator
 * application.
 *
 * It implements the PropertyChangeListener interface to listen for property
 * changes in the model.
 *
 * @author Samad
 */
public class View implements PropertyChangeListener {

    private final Stage stage;
    private MainBox mainBox;

    /**
     * Constructs a View object with the specified stage.
     *
     * @param stage the stage where the application is displayed
     */
    public View(Stage stage) {
        this.stage = stage;
        this.mainBox = new MainBox();
    }

    /**
     * Initializes the user interface of the application.
     */
    public void initialize() {
        mainBox = new MainBox();
        Scene scene = new Scene(mainBox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the title, style, and size of the stage.
     */
    public void PrintScreen() {
        stage.setTitle("Calcul du BMR");
        stage.centerOnScreen();
        stage.initStyle(StageStyle.DECORATED);
        stage.setMinWidth(500);
        stage.setMinHeight(500);
    }

    /**
     * Returns the text field for inputting the height of the user.
     *
     * @return the text field for inputting the height of the user
     */
    public TextField getSize() {
        return mainBox.Size();
    }

    /**
     * Returns the text field for inputting the weight of the user.
     *
     * @return the text field for inputting the weight of the user
     */
    public TextField getWeight() {
        return mainBox.Weight();
    }

    /**
     * Returns the text field for inputting the year of birth of the user.
     *
     * @return the text field for inputting the year of birth of the user
     */
    public TextField getYear() {
        return mainBox.getInputYear();
    }

    /**
     * Returns the choice box for selecting the lifestyle of the user.
     *
     * @return the choice box for selecting the lifestyle of the user
     */
    public ChoiceBox<LifeStyle> getLifestyle() {
        return mainBox.Lifestyle();
    }

    /**
     * Returns the text field for displaying the BMR of the user.
     *
     * @return the text field for displaying the BMR of the user
     */
    public TextField getBmr() {
        return mainBox.Bmr();
    }

    /**
     * Returns the text field for displaying the recommended daily calorie
     * intake of the user.
     *
     * @return the text field for displaying the recommended daily calorie
     * intake of the user
     */
    public TextField getCalories() {
        return mainBox.Calories();
    }

    /**
     * Checks if the woman is selected
     *
     * @return true if the woman is selected, otherwise false
     */
    public boolean isWomanSelected() {
        return mainBox.getIsWomenSelected();
    }

    /**
     * Sets text of BMRField
     *
     * @param BMRResult the given bmr text
     */
    public void setBMRText(String BMRResult) {
        this.getBmr().setText(BMRResult);
    }

    /**
     * Sets text of caloriesField
     *
     * @param caloriesResult the given calories
     */
    public void setCaloriesText(String caloriesResult) {
        this.getCalories().setText(caloriesResult);
    }

    /**
     * Returns the calculer button.
     *
     * @return the calculer button
     */
    public Button getButtonCalculer() {
        return this.mainBox.getButton();
    }

    /**
     * Returns the clear button.
     *
     * @return the clear button
     */
    public Button getButtonClear() {
        return this.mainBox.getButtonClear();
    }

    /**
     * Creates an alert and displays a pop-up on the screen
     */
    public void createAlert() {
        Alert a = new Alert(AlertType.ERROR, "The data entered is invalid. Try again!");
        a.show();
    }

    /**
     * Sets the action for the "Calculate" button.
     *
     * @param eventHandler The event handler to set for the button.
     */
    public void setCalculateAction(EventHandler<ActionEvent> eventHandler) {
        this.mainBox.getButton().setOnAction(eventHandler);

    }

    /**
     * Sets the action for the "Clear" button.
     *
     * @param handler The event handler to set for the button.
     */
    public void setClearButtonAction(EventHandler<ActionEvent> handler) {
        mainBox.getButtonClear().setOnAction(handler);
    }

    /**
     * This method is called whenever a property in the model is changed, and
     * updates the corresponding view element.
     *
     * @param evt the PropertyChangeEvent that contains the information about
     * the changed property
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();     
        switch (name) {
            case "Notification bmr":
                double bmr = (double) evt.getNewValue();
                setBMRText("" + bmr);
                break;
            case "Notification calories":
                double calories = (double) evt.getNewValue();
                setCaloriesText("" + calories);
                break;
            default:
                System.out.println("Notification inconnue : " + name);
                break;
        }
    }

    /**
     * Resets all data fields
     */
    public void resetInputData() {
        getSize().clear();
        getWeight().clear();
        getYear().clear();
    }

    /**
     * Resets all result fields
     */
    public void resetOutputData() {
        getBmr().clear();
        getCalories().clear();
    }

    /**
     * Sets the color to red for BMRField and caloriesField
     */
    public void setStyleColorRedFail() {
        this.setBMRText("Failed");
        this.setCaloriesText("Failed");
        this.getBmr().setStyle("-fx-text-fill: red; -fx-font-size: 13px;");
        this.getCalories().setStyle("-fx-text-fill: red; -fx-font-size: 13px;");
    }

    /**
     * Sets the text color to black for BMRField and caloriesField
     */
    public void setStyleColorBlack() {
        this.getBmr().setStyle("-fx-text-fill: black; -fx-font-size: 13px;");
        this.getCalories().setStyle("-fx-text-fill: black; -fx-font-size: 13px;");
    }
}
