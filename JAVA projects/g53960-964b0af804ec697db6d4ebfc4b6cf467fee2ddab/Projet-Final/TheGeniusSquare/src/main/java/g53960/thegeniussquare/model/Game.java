package g53960.thegeniussquare.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a game of The Genius Square.
 *
 * @author Samad
 */
public class Game {

    private final Board board;
    private final Map<ShapeColor, Shape> shapes;
    Map<ShapeColor, List<Position>> shapeNewPositions;
    private final PropertyChangeSupport observable;
    private int timer = 120;
    private final List<ShapeComponent> shapesPlacedDone;
    private ShapeColor redoColor;
    private Position redoPos;

    /**
     * Constructs a new instance of the Game class. Initializes the board,
     * shapes, and observable properties.
     */
    public Game() {
        this.shapes = new HashMap<>();
        this.shapeNewPositions = new HashMap<>();
        this.shapesPlacedDone = new ArrayList<>();
        Dice dice = new Dice();
//        Position[] blockerPositions = {new Position(0, 0), //juste un cas pour gagner le jeux
//            new Position(0, 2),
//            new Position(0, 5),
//            new Position(3, 2),
//            new Position(3, 4),
//            new Position(5, 1),
//            new Position(5, 5)};

        Position[] blockerPositions = dice.getRandom();
        this.board = new Board(blockerPositions);
        this.observable = new PropertyChangeSupport(this);
        initialize();
    }

    /**
     * Constructor for test
     *
     * @param board the Board of game
     */
    public Game(Board board) {
        this.shapes = new HashMap<>();
        this.shapeNewPositions = new HashMap<>();
        this.shapesPlacedDone = new ArrayList<>();
        this.board = board;
        this.observable = new PropertyChangeSupport(this);
        initialize();
    }

    /**
     * Initializes the shapes by creating and storing instances of each shape.
     *
     */
    private void initialize() {

        for (ShapeColor color : ShapeColor.values()) {
            if (color != ShapeColor.BLACK && color != ShapeColor.WHITE) {
                Shape shape = ShapeFactory.build(color);
                shapes.put(color, shape);
            }
        }
    }

    /**
     * Place a colored shape on the specified position.
     *
     * @param color The color of the shape to be placed.
     * @param pos The position where the shape should be placed.
     * @return {@code true} if the shape was successfully placed, otherwise
     * {@code false}.
     */
    public Boolean placeShape(ShapeColor color, Position pos) {
        List<Position> savedPositions = new ArrayList<>();
        List<ShapeComponent> savedComponents = new ArrayList<>();

        List<ShapeComponent> components = shapes.get(color).getShapeComponents();
        Position startPosition = components.get(0).getPos();
        int startX = pos.getRow() - startPosition.getRow();
        int startY = pos.getCol() - startPosition.getCol();

        if (!isValidPlacement(components, startX, startY)) {
            return false;
        }

        savePositionsAndComponents(components, savedPositions, savedComponents, startX, startY);
        updateSquares(savedPositions, savedComponents);
        updateComponentsAndShapesPlacedDone(savedComponents, savedPositions);

        observable.firePropertyChange("shapePlaced", color, this);
        observable.firePropertyChange("message", null, "Shape placed!");
        redoColor = color;
        redoPos = pos;

        return true;
    }

    /**
     * Checks whether the placement of the shape from the specified starting
     * coordinates is valid.
     *
     * @param components The list of components of the shape to be placed.
     * @param startX The starting X-coordinate.
     * @param startY The starting Y-coordinate.
     * @return {@code true} if the placement is valid, otherwise {@code false}.
     */
    private boolean isValidPlacement(List<ShapeComponent> components, int startX, int startY) {
        ShapeComponent[][] squares = this.board.getSquares();

        for (ShapeComponent component : components) {
            int x = startX + component.getPos().getRow();
            int y = startY + component.getPos().getCol();

            if (x < 0 || x >= squares.length || y < 0 || y >= squares[x].length) {
                observable.firePropertyChange("message", null, "Choose a correct place please!");
                return false;
            }

            if (squares[x][y].getType() != ShapeType.NEUTRAL) {
                observable.firePropertyChange("message", null, "The position is occupied!\n Choose another correct place!");
                return false;
            }
        }

        return true;
    }

    /**
     * Saves the positions and components of the shape's components.
     *
     * @param components The list of components of the shape to be placed.
     * @param savedPositions The list to store the saved positions.
     * @param savedComponents The list to store the saved components.
     * @param startX The starting X-coordinate.
     * @param startY The starting Y-coordinate.
     */
    private void savePositionsAndComponents(List<ShapeComponent> components, List<Position> savedPositions,
            List<ShapeComponent> savedComponents, int startX, int startY) {
        for (ShapeComponent component : components) {
            int x = startX + component.getPos().getRow();
            int y = startY + component.getPos().getCol();
            Position position = new Position(x, y);
            savedPositions.add(position);
            savedComponents.add(component);
        }
    }

    /**
     * Updates the squares on the board with the saved positions and components.
     *
     * @param savedPositions The list of saved positions.
     * @param savedComponents The list of saved components.
     */
    private void updateSquares(List<Position> savedPositions, List<ShapeComponent> savedComponents) {
        ShapeComponent[][] squares = this.board.getSquares();
        for (int i = 0; i < savedComponents.size(); i++) {
            squares[savedPositions.get(i).getRow()][savedPositions.get(i).getCol()] = savedComponents.get(i);
        }
    }

    /**
     * Updates the components with the saved positions and adds the components
     * to the list of placed shapes.
     *
     * @param savedComponents The list of saved components.
     * @param savedPositions The list of saved positions.
     */
    private void updateComponentsAndShapesPlacedDone(List<ShapeComponent> savedComponents,
            List<Position> savedPositions) {
        for (int i = 0; i < savedComponents.size(); i++) {
            ShapeComponent component = savedComponents.get(i);
            component.setPos(savedPositions.get(i));
            shapesPlacedDone.add(component);
        }
    }

    /**
     * Gets the board of the game.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the shapes available in the game.
     *
     * @return a map of shape colors to shape objects
     */
    public Map<ShapeColor, Shape> getShapes() {
        return shapes;
    }

    /**
     * Rotates a shape clockwise.
     *
     * @param color the color of the shape to rotate
     */
    public void rotateShape(ShapeColor color) {
        Shape shape = shapes.get(color);

        shape.rotate();

        observable.firePropertyChange("shapeRotated", color, this);
        observable.firePropertyChange("message", null, "Shape rotated!");
    }

    /**
     * Adds an observer to listen for property changes in the game.
     *
     * @param listener the listener to add
     */
    public void addObserver(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }

    /**
     * Decrements the game timer. Notifies observers of timer changes.
     */
    public void decrementTimer() {
        observable.firePropertyChange("message", null, "Please place a shape in the board!");
        Thread timerThread = new Thread(() -> {
            try {
                observable.firePropertyChange("changeTimer", 0, timer); // Mettre à jour le timer initial
                Boolean win = false;
                while (timer > 0) {
                    Thread.sleep(1000);
                    win = isWin();
                    timer--;
                    if (win) {
                        timer = 0;
                    }
                    observable.firePropertyChange("changeTimer", timer + 1, timer);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        timerThread.setDaemon(true);
        timerThread.start();
    }

    /**
     * Retrieves the current timer value of the game.
     *
     * @return The timer value.
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Checks if the player has won the game.
     *
     * @return true if the player has won, fals} otherwise
     */
    public Boolean isWin() {
        for (int i = 0; i < board.getSquares().length; i++) {
            for (int j = 0; j < board.getSquares()[i].length; j++) {
                if (board.getSquares()[i][j].getType() == ShapeType.NEUTRAL) {
                    return false;
                }
            }
        }
        observable.firePropertyChange("isWin", null, "Congratulations! You have win!");
        return true;
    }

    /**
     * Reverts the last action performed in the game.
     */
    public void undo() {
        ShapeColor color = shapesPlacedDone.get(shapesPlacedDone.size() - 1).getColor();
        List<ShapeComponent> components = shapesPlacedDone;
        for (int i = 0; i < board.getSquares().length; i++) {
            for (int j = 0; j < board.getSquares()[i].length; j++) {
                for (int k = 0; k < components.size(); k++) {
                    Position pos = new Position(i, j);

                    if (pos.equals(components.get(k).getPos())) {
                        board.getSquares()[i][j].setColor(ShapeColor.WHITE);
                        board.getSquares()[i][j].setType(ShapeType.NEUTRAL);

                    }
                }

            }
        }

        Shape shape = shapes.get(color);
        List<ShapeComponent> shapeComponents = shape.getShapeComponents();
        /*for (int i = 0; i < shapeComponents.size(); i++) {
            shapeComponents.get(i).setColor(ShapeColor.WHITE);
        }*/
        observable.firePropertyChange("Undo", color, this);
        observable.firePropertyChange("message", color, "Command 'undo' successful!");
    }

   /**
 * Redoes the placement of a shape that was previously undone.
 * This method attempts to place the shape with the color and position
 * that were stored for redoing the last action.
 */
    public void redo() {
        placeShape(redoColor, redoPos);
    }
}
