package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.ShapeColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * * The RightVBox class represents the right side of the application's main
 * view. It contains buttons for selecting colors and a shape grid pane for
 * displaying shapes.
 *
 * It provides methods to print shapes, update shape rotations, and interact
 * with the buttons.
 *
 * @author Samad
 */
public class RightVBox extends VBox {

    private final ShapesView shapesView;
    private final Button purpleButton;
    private final Button orangeButton;
    private final Button greyButton;
    private final Button lightBlueButton;
    private final Button blueButton;
    private final Button greenButton;
    private final Button redButton;
    private final Button yellowButton;
    private final Button brownButton;
    private final List<Button> buttonsColor;

    /**
     * Creates a new RightVBox instance.
     */
    public RightVBox() {

        this.shapesView = new ShapesView();
        this.buttonsColor = new ArrayList<>();
        this.getChildren().add(shapesView);

        // Ajout de padding entre les différentes grilles
        this.setSpacing(20);

        // Ajout de padding autour de chaque grille
        this.setPadding(new Insets(20));

        // Centrer l'affichage
        this.setAlignment(Pos.CENTER);

        // Création des boutons avec leurs labels correspondants
        purpleButton = createColorButton(ShapeColor.PURPLE, Color.PURPLE);
        orangeButton = createColorButton(ShapeColor.ORANGE, Color.ORANGE);
        greyButton = createColorButton(ShapeColor.GREY, Color.GREY);
        lightBlueButton = createColorButton(ShapeColor.LIGHTBLUE, Color.LIGHTBLUE);
        blueButton = createColorButton(ShapeColor.BLUE, Color.BLUE);
        greenButton = createColorButton(ShapeColor.GREEN, Color.GREEN);
        redButton = createColorButton(ShapeColor.RED, Color.RED);
        yellowButton = createColorButton(ShapeColor.YELLOW, Color.YELLOW);
        brownButton = createColorButton(ShapeColor.BROWN, Color.BROWN);
        buttonsColor.addAll(Arrays.asList(purpleButton, orangeButton, greyButton, lightBlueButton,
                blueButton, greenButton, redButton, yellowButton, brownButton));
        // Création du conteneur pour les boutons
        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(
                purpleButton, orangeButton, greyButton, lightBlueButton,
                blueButton, greenButton, redButton, yellowButton, brownButton
        );

        // Ajout du conteneur de boutons à la VBox
        this.getChildren().add(buttonContainer);
        setDisableButtons(true);
    }

    public ShapesView getShapesViews() {
        return shapesView;
    }

    /**
     * Creates a button for a specific color.
     *
     * @param colorName the shape color associated with the button
     * @param color the color value to set as the background color of the button
     * @return the created button
     */
    private Button createColorButton(ShapeColor colorName, Color color) {
        String buttonColor = colorName.toString();
        Button colorButton = new Button(buttonColor);
        colorButton.setPrefWidth(100);
        colorButton.setPrefHeight(30);
        colorButton.setStyle("-fx-background-color: " + toHexCode(color) + ";");

        colorButton.setGraphic(new VBox(colorButton));
        colorButton.setContentDisplay(ContentDisplay.TOP);
        colorButton.setAlignment(Pos.CENTER);

        return colorButton;
    }

    /**
     * Disables all the color buttons.
     */
    public void setDisableButtons(boolean disable) {

        for (Button button : buttonsColor) {
            button.setDisable(disable);
        }
    }

    /**
     *
     * Disables or enables a specific color button.
     *
     * @param color the shape color associated with the button to disable or
     * enable
     * @param disable true to disable the button, false to enable it
     */
    public void setDisableOneButton(ShapeColor color, Boolean disable) {
        String colorButton = color.toString();
        for (Button button : buttonsColor) {
            if (button.getText().equals(colorButton)) {
                button.setDisable(disable);
            }
        }
    }

    /**
     * Converts a Color object to a hexadecimal code representing the color.
     *
     * @param color the color to convert
     * @return the hexadecimal color code
     */
    private String toHexCode(Color color) {
        //convertit un objet Color en code hexadécimal représentant la couleur
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * Prints the shapes in the shape grid pane.
     *
     * @param game the game object containing the shapes to print
     */
    public void printShapes(Game game) {
        shapesView.printShapes(game);
    }

    /**
     * Gets the shape grid pane.
     *
     * @return the shape grid pane as a map of shape colors to shape views
     */
    public Map<ShapeColor, ShapeView> getShapeGridPane() {
        return shapesView.getShapesView();
    }

    /**
     *
     * Updates the rotation of a specific shape view.
     *
     * @param color the color of the shape to update
     * @param game the game object containing the updated shape rotation
     */
    public void updateViewShapeRotation(ShapeColor color, Game game) {
        this.shapesView.updateViewShapeRotation(color, game);
    }

    /**
     * Gets the shapes view.
     *
     * @return the shapes view as a map of shape colors to shape views
     */
    public Map<ShapeColor, ShapeView> getShapesView() {
        return shapesView.getShapesView();
    }

    /**
     * Clears the shape view for a specific color.
     *
     * @param color the color of the shape view to clear
     */
    public void clearShapeView(ShapeColor color, boolean placed) {
        this.shapesView.clearShapeView(color);
        if (placed == true) {
            this.shapesView.disableShapeView(color, true);
        }

    }

    public Button getPurpleButton() {
        return purpleButton;
    }

    public Button getOrangeButton() {
        return orangeButton;
    }

    public Button getGreyButton() {
        return greyButton;
    }

    public Button getLightBlueButton() {
        return lightBlueButton;
    }

    public Button getBlueButton() {
        return blueButton;
    }

    public Button getGreenButton() {
        return greenButton;
    }

    public Button getRedButton() {
        return redButton;
    }

    public Button getYellowButton() {
        return yellowButton;
    }

    public Button getBrownButton() {
        return brownButton;
    }
}
