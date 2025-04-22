package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.ShapeColor;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents the main HBox in the game view. Extends the HBox class and
 * contains the left VBox and right VBox.
 *
 * @author Samad
 */
public class MainBox extends HBox {

    private final LeftVBox leftVBox;
    private final RightVBox rightVBox;

    /**
     * Constructs a new MainBox object. Initializes and configures the left VBox
     * and right VBox.
     */
    public MainBox() {
        this.leftVBox = new LeftVBox();
        this.rightVBox = new RightVBox();
        this.getChildren().addAll(leftVBox, rightVBox);
        this.setSpacing(100);
    }

    /**
     * Retrieves the right VBox.
     *
     * @return The right VBox.
     */
    public RightVBox getRightVBox() {
        return rightVBox;
    }

    /**
     * Retrieves the left VBox.
     *
     * @return The left VBox.
     */
    public LeftVBox getLeftVbox() {
        return leftVBox;
    }

    /**
     * Retrieves the Play button.
     *
     * @return The Play button.
     */
    public Button getPlayButton() {
        return this.leftVBox.getButtonPlay();
    }

    /**
     * Updates the timer label with the specified timer value.
     *
     * @param timer The timer value to be displayed.
     */
    public void updateTimer(int timer) {
        leftVBox.updateTimerLabel(timer);
    }

    /**
     * Prints the shapes on the right VBox.
     *
     * @param game The game instance containing the shapes.
     */
    public void printShapes(Game game) {
        rightVBox.printShapes(game);
    }

    /**
     * Retrieves the shape grid pane containing the shapes.
     *
     * @return The shape grid pane.
     */
    public Map<ShapeColor, ShapeView> getShapesView() {
        return rightVBox.getShapesView();
    }

    /**
     * Updates the rotation of the view shape for the specified color.
     *
     * @param color The color of the shape.
     * @param game The game instance.
     */
    public void updateViewShapeRotation(ShapeColor color, Game game) {
        this.rightVBox.updateViewShapeRotation(color, game);
    }

    /**
     * Retrieves the Purple button.
     *
     * @return The Purple button.
     */
    public Button getPurpleButton() {
        return rightVBox.getPurpleButton();
    }

    /**
     * Retrieves the Orange button.
     *
     * @return The Orange button.
     */
    public Button getOrangeButton() {
        return rightVBox.getOrangeButton();
    }

    /**
     * Retrieves the Grey button.
     *
     * @return The Grey button.
     */
    public Button getGreyButton() {
        return rightVBox.getGreyButton();
    }

    /**
     * Retrieves the Light Blue button.
     *
     * @return The Light Blue button.
     */
    public Button getLightBlueButton() {
        return rightVBox.getLightBlueButton();
    }

    /**
     * Retrieves the Blue button.
     *
     * @return The Blue button.
     */
    public Button getBlueButton() {
        return rightVBox.getBlueButton();
    }

    /**
     * Retrieves the Green button.
     *
     * @return The Green button.
     */
    public Button getGreenButton() {
        return rightVBox.getGreenButton();
    }

    /**
     * Retrieves the Red button.
     *
     * @return The Red button.
     */
    public Button getRedButton() {
        return rightVBox.getRedButton();
    }

    /**
     * Retrieves the Yellow button.
     *
     * @return The Yellow button.
     */
    public Button getYellowButton() {
        return rightVBox.getYellowButton();
    }

    /**
     * Retrieves the Brown button.
     *
     * @return The Brown button.
     */
    public Button getBrownButton() {
        return rightVBox.getBrownButton();
    }

    /**
     * Retrieves the game board.
     *
     * @return The game board view.
     */
    public SquareBoardView[][] getBoard() {
        return leftVBox.getBoard();
    }

    /**
     * Retrieves the displayInfo label.
     *
     * @return The displayInfo label.
     */
    public Label getDisplayInfo() {
        return leftVBox.getDisplayInfo();
    }

    /**
     * Disables all shape buttons.
     */
    public void setDisableButtons() {
        this.rightVBox.setDisableButtons(false);
        this.leftVBox.getUndoButton().setDisable(false);
        this.leftVBox.getRedoButton().setDisable(false);
    }

    /**
     *
     * Sets the disable state of a specific shape button.
     *
     * @param color The color of the shape.
     * @param disable The disable state to be set.
     */
    public void setDisableOneButton(ShapeColor color, Boolean disable) {
        this.rightVBox.setDisableOneButton(color, disable);
    }

    /**
     * Plays the win animation
     */
    public void playWinAnimation() {
        this.leftVBox.playWinAnimation();
    }

    /**
     * Retrieves the Undo button.
     *
     * @return The Undo button.
     */
    public Button getUndoButton() {
        return this.leftVBox.getUndoButton();
    }

    /**
     * Retrieves the Redo button.
     *
     * @return The Redo button.
     */
    public Button getRedoButton() {
        return this.leftVBox.getRedoButton();
    }

    /**
     * Clears the shape view for the specified color.
     *
     * @param color The color of the shape.
     * @param placed
     */
    public void clearShapeView(ShapeColor color, boolean placed) {
        this.rightVBox.clearShapeView(color, placed);
    }
}
