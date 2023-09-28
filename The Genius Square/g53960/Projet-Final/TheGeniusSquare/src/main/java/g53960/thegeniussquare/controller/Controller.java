package g53960.thegeniussquare.controller;

import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.Position;
import g53960.thegeniussquare.model.ShapeColor;
import g53960.thegeniussquare.view.View;
import java.util.Objects;

/**
 * The Controller class handles the user interactions and updates the model and
 * view accordingly.
 *
 * @author Samad
 */
public class Controller {

    private final View view;
    private final Game game;
    private Boolean clickPlay;
    private Boolean clickShape;
    private ShapeColor shapeColorToPlace;
    private Boolean isPlaced;
    // private Map<ShapeColor, Boolean> placedShapes;

    /**
     * Constructs a Controller object with the specified view and game.
     *
     * @param view the view object
     * @param game the game object
     */
    public Controller(View view, Game game) {
        this.view = Objects.requireNonNull(view, "Pas de null");
        this.game = Objects.requireNonNull(game, "Pas de null");
        this.clickPlay = false;
        this.clickShape = false;
        this.isPlaced = false;
        this.shapeColorToPlace = null;
        //this.placedShapes = new HashMap<>();
        view.printScene();
        game.addObserver(view);
    }

    /**
     * Starts the game.
     */
    public void start() {

        clickPlay = true;
        view.printShapes(game);
        view.setDisableButtons();
        game.decrementTimer();
        view.updateBoard(game);

    }

    public Boolean shapeIsPlaced() {
        return isPlaced;
    }

    /**
     * Performs the undo operation in the game.
     */
    public void Undo() {
        game.undo();
    }
    
    public void Redo(){
        game.redo();
    }

    /**
     * Rotates the shape of the specified color.
     *
     * @param color the color of the shape to rotate
     */
    public void rotate(ShapeColor color) {
        if (getTimer() > 0 || !game.isWin()) {
            this.game.rotateShape(color);
        }

    }

    /**
     * Returns the state of the clickPlay flag.
     *
     * @return true if the play button was clicked, false otherwise
     */
    public Boolean getClickPlay() {
        return clickPlay;
    }

    /**
     * Sets the shape color to be placed on the game board.
     *
     * @param color the color of the shape to place
     */
    public void setShapeToPlaced(String color) {
        isPlaced= false;
        if (game.getTimer() > 0) {
            shapeColorToPlace = switchColor(color);
            clickShape = true;
        } else {
            shapeColorToPlace = null;
        }

    }

    /**
     * Places the shape at the specified position on the game board.
     *
     * @param pos the position to place the shape
     */
    public void placeShape(Position pos) {

        if (shapeColorToPlace != null) {
            isPlaced = game.placeShape(shapeColorToPlace, pos);
            // game.placeShape(shapeColorToPlace, pos);
            //  placedShapes.put(shapeColorToPlace, isPlaced);
            shapeColorToPlace = null;
            clickShape = false;
        }
    }

    /**
     * Returns the state of the clickShape.
     *
     * @return true if a shape was clicked, false otherwise
     */
    public Boolean getClickShape() {
        return clickShape;
    }

    /**
     * Converts a string representation of a color to the corresponding
     * ShapeColor enum value.
     *
     * @param color the string representation of the color
     * @return the ShapeColor enum value corresponding to the color
     */
    private ShapeColor switchColor(String color) {
        switch (color) {
            case "PURPLE":
                return ShapeColor.PURPLE;
            case "ORANGE":
                return ShapeColor.ORANGE;
            case "GREY":
                return ShapeColor.GREY;
            case "LIGHTBLUE":
                return ShapeColor.LIGHTBLUE;
            case "BLUE":
                return ShapeColor.BLUE;
            case "GREEN":
                return ShapeColor.GREEN;
            case "RED":
                return ShapeColor.RED;
            case "YELLOW":
                return ShapeColor.YELLOW;
            default:
                return ShapeColor.BROWN;
        }

    }

    /**
     * Returns the current timer value.
     *
     * @return the current timer value
     */
    public int getTimer() {
        return game.getTimer();
    }

//   public Boolean shapeIsPlaced(ShapeColor color){
//   return placedShapes.get(color);
//   }
}
