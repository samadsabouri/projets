package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.ShapeColor;
import g53960.thegeniussquare.model.ShapeComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * * The View class represents the user interface view of the Genius Square
 * game. It implements the PropertyChangeListener interface to listen for
 * property change events. The view is responsible for rendering the game
 * elements and handling user interactions.
 *
 * @author Samad
 */
public class View implements PropertyChangeListener {

    private MainBox mainBox;
    private final Stage stage;

    /**
     * Creates a new View instance.
     *
     * @param stage the stage to display the view on
     */
    public View(Stage stage) {
        this.mainBox = new MainBox();
        this.stage = stage;
    }

    /**
     * Prints the scene and displays it on the stage.
     */
    public void printScene() {
        stage.setTitle("THE GENIUS SQUARE");
        stage.initStyle(StageStyle.DECORATED);
        // Récupérer les dimensions de l'écran principal
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        // Définir la taille de la fenêtre en fonction des dimensions de l'écran
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        mainBox = new MainBox();
        Scene scene = new Scene(mainBox, screenWidth, screenHeight);
        stage.setFullScreen(true);
        backgroundColor();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the background color of the main box using a gradient.
     */
    private void backgroundColor() {
        // Définir un dégradé vertical 
        Stop[] stops = {
            new Stop(0, Color.PINK),
            new Stop(0.2, Color.CORNSILK),
            new Stop(0.4, Color.BISQUE),
            new Stop(0.6, Color.BISQUE),
            new Stop(0.8, Color.BISQUE),
            new Stop(1, Color.PINK)
        };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(gradient, null, null);
        Background background = new Background(backgroundFill);
        mainBox.setBackground(background);
    }

    /**
     * This method is called when a property change event occurs. It updates the
     * view based on the type of property change event.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

            String name = evt.getPropertyName();
            ShapeColor color;
            Game game;

            switch (name) {
                case "Undo":
                    mainBox.getUndoButton().setDisable(true);
                    color = (ShapeColor) evt.getOldValue();
                    mainBox.getRightVBox().getShapesViews().disableShapeView(color, false);
                    game = (Game) evt.getNewValue();
                    updateBoard(game);
                    setDisableOneButton(color, false);
                    updateViewShapeRotation(color, game);
                    break;
                case "message":
                    String message = (String) evt.getNewValue();
                    displayUtilisateur(message);
                    break;
                case "shapePlaced":
                    mainBox.getUndoButton().setDisable(false);
                    color = (ShapeColor) evt.getOldValue();
                    clearShapeView(color, true);
                    game = (Game) evt.getNewValue();
                    updateBoard(game);
                    setDisableOneButton(color, true);
                    break;
                case "Redo":
                    mainBox.getUndoButton().setDisable(false);
                    color = (ShapeColor) evt.getOldValue();
                    clearShapeView(color, true);
                    game = (Game) evt.getNewValue();
                    updateBoard(game);
                    setDisableOneButton(color, true);
                    break;
                case "shapeRotated":
                    color = (ShapeColor) evt.getOldValue();
                    game = (Game) evt.getNewValue();
                    clearShapeView(color, false);
                    updateViewShapeRotation(color, game);
                    updateBoard(game);
                    break;
                case "changeTimer":
                    mainBox.getPlayButton().setDisable(true);
                    int timer = (int) evt.getNewValue();
                    int checkTimer = (int) evt.getOldValue();
                    if (checkTimer == -1) {
                        updateTimer(0);
                    } else {
                        updateTimer(timer);
                    }
                    if (timer <= 0) {
                        setDisableButtons();
                    }
                    break;
                case "isWin":
                    String isWin = (String) evt.getNewValue();
                    displayUtilisateur(isWin);
                    playWinAnimation();
                    break;
                default:
                    System.out.println("Notification inconnue : " + name);
                    break;
            }
        });
    }

    /**
     * Sets the action handler for the play button.
     *
     * @param eventHandler the event handler to be set
     */
    public void setPlayAction(EventHandler<ActionEvent> eventHandler) {
        this.mainBox.getPlayButton().setOnAction(eventHandler);

    }

    /**
     * Sets the action handlers for the color buttons.
     *
     * @param eventHandler the event handler to be set
     */
    public void setBouttonsActions(EventHandler<ActionEvent> eventHandler) {
        this.mainBox.getBlueButton().setOnAction(eventHandler);
        this.mainBox.getBrownButton().setOnAction(eventHandler);
        this.mainBox.getPurpleButton().setOnAction(eventHandler);
        this.mainBox.getGreyButton().setOnAction(eventHandler);
        this.mainBox.getGreenButton().setOnAction(eventHandler);
        this.mainBox.getYellowButton().setOnAction(eventHandler);
        this.mainBox.getOrangeButton().setOnAction(eventHandler);
        this.mainBox.getRedButton().setOnAction(eventHandler);
        this.mainBox.getLightBlueButton().setOnAction(eventHandler);
    }

    /**
     * Updates the game board view based on the provided game state.
     *
     * @param game the game instance containing the updated board information
     */
    public void updateBoard(Game game) {
        SquareBoardView[][] rectangles = this.mainBox.getBoard();
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                ShapeComponent square = game.getBoard().getSquares()[i][j];
                switch (square.getColor()) {
                    case WHITE:
                        rectangles[i][j].setStyle("-fx-background-color: white");
                        break;
                    case BLACK:
                        rectangles[i][j].setStyle("-fx-background-color: black");
                        break;
                    case YELLOW:
                        rectangles[i][j].setStyle("-fx-background-color: yellow");
                        break;
                    case BLUE:
                        rectangles[i][j].setStyle("-fx-background-color: blue");
                        break;
                    case RED:
                        rectangles[i][j].setStyle("-fx-background-color: red");
                        break;
                    case GREEN:
                        rectangles[i][j].setStyle("-fx-background-color: green");
                        break;
                    case GREY:
                        rectangles[i][j].setStyle("-fx-background-color: grey");
                        break;
                    case ORANGE:
                        rectangles[i][j].setStyle("-fx-background-color: orange");
                        break;
                    case PURPLE:
                        rectangles[i][j].setStyle("-fx-background-color: purple");
                        break;
                    case BROWN:
                        rectangles[i][j].setStyle("-fx-background-color: brown");
                        break;
                    case LIGHTBLUE:
                        rectangles[i][j].setStyle("-fx-background-color: lightblue");
                        break;

                }
            }

        }
    }

    /**
     * Updates the timer value in the view.
     *
     * @param timer the updated timer value
     */
    public void updateTimer(int timer) {
        mainBox.updateTimer(timer);
    }

    /**
     * Prints the shapes on the game board.
     *
     * @param game the game object containing the shapes
     */
    public void printShapes(Game game) {
        mainBox.printShapes(game);
    }

    /**
     * Returns the mapping of shape colors to shape views in the shape grid
     * pane.
     *
     * @return the mapping of shape colors to shape views
     */
    public Map<ShapeColor, ShapeView> getShapesView() {
        return mainBox.getShapesView();
    }

    /**
     * Updates the view with the rotation of a specific shape color in the game.
     *
     * @param color the color of the shape being rotated
     * @param game the game object containing the rotated shape
     */
    public void updateViewShapeRotation(ShapeColor color, Game game) {
        this.mainBox.updateViewShapeRotation(color, game);
    }

    /**
     * Returns the 2D array of square board views representing the game board.
     *
     * @return the 2D array of square board views
     */
    public SquareBoardView[][] getBoard() {
        return mainBox.getBoard();
    }

    /**
     * Displays a message to the user in the view.
     *
     * @param message the message to be displayed
     */
    public void displayUtilisateur(String message) {
        Label displayInfo = mainBox.getDisplayInfo();
        displayInfo.setText(message);
    }

    /**
     * Disables all buttons in the view.
     */
    public void setDisableButtons() {
        this.mainBox.setDisableButtons();
    }

    /**
     * Disables or enables a specific button associated with a shape color.
     *
     * @param color the color of the shape associated with the button
     * @param disable true to disable the button, false to enable it
     */
    public void setDisableOneButton(ShapeColor color, Boolean disable) {
        this.mainBox.setDisableOneButton(color, disable);
    }

    /**
     * Plays the win animation in the view
     */
    public void playWinAnimation() {
        this.mainBox.playWinAnimation();
    }

    /**
     * Sets the action for the undo button.
     *
     * @param eventHandler the event handler for the undo button action
     */
    public void setUndoAction(EventHandler<ActionEvent> eventHandler) {
        this.mainBox.getUndoButton().setOnAction(eventHandler);

    }

    /**
     * Sets the action for the redo button.
     *
     * @param eventHandler the event handler for the redo button action
     */
    public void setRedoAction(EventHandler<ActionEvent> eventHandler) {
        this.mainBox.getRedoButton().setOnAction(eventHandler);

    }

    /**
     * Clears the view representation of a specific shape color.
     *
     * @param color the color of the shape to be cleared
     */
    public void clearShapeView(ShapeColor color, boolean placed) {
        this.mainBox.clearShapeView(color, placed);
    }
}
