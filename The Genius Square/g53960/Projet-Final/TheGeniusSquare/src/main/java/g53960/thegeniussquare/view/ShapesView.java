package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.Position;
import g53960.thegeniussquare.model.Shape;
import g53960.thegeniussquare.model.ShapeColor;
import g53960.thegeniussquare.model.ShapeComponent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * The ShapesView class represents a grid of shape views, each displaying a
 * specific shape. It manages the display and updates of the shapes in the view.
 *
 * @author Samad
 */
public class ShapesView extends GridPane {

    private final Map<ShapeColor, ShapeView> shapesView;

    /**
     * Creates a new instance of ShapesView.
     */
    public ShapesView() {
        this.shapesView = new HashMap<>();
        // Création des cases pour les shapes
        for (int i = 0; i < 9; i++) {
            ShapeView shapeView = new ShapeView();
            ShapeColor color = switchColor(i);
            shapesView.put(color, shapeView);
            setAlignment(Pos.CENTER);
            add(shapeView, i % 3, i / 3);
            setDisable(true);
        }
        setHgap(10);
        setVgap(10);
    }

    /**
     * Prints the shapes on the shapes view based on the current state of the
     * game. Each shape is represented by a set of shape components.
     *
     * @param game the game object containing the shapes and their positions
     */
    public void printShapes(Game game) {
        setDisable(false);
        Map<ShapeColor, Shape> shapes = game.getShapes();
        for (int i = 0; i < 9; i++) {
            ShapeColor color = switchColor(i);
            ShapeView shape = shapesView.get(color);

            for (int j = 0; j < shape.getRectangles().length; j++) {
                for (int k = 0; k < shape.getRectangles()[j].length; k++) {

                    Shape shapeForm = shapes.get(color);
                    Color colorFx = toFXColor(color);
                    for (int l = 0; l < shapeForm.getShapeComponents().size(); l++) {
                        if ((j - shapeForm.getShapeComponents().get(l).getRow()) == 0 && (k - shapeForm.getShapeComponents().get(l).getCol()) == 0) {

                            shape.getRectangles()[j][k].getRectangle().setFill(colorFx);
                            break;
                        }
                    }

                }
            }

        }
    }

    /**
     * Clears the display of a specific shape in the shapes view by setting its
     * rectangles to white.
     *
     * @param color the color of the shape to clear
     */
    public void clearShapeView(ShapeColor color) {
        ShapeView shape = shapesView.get(color);
        for (int i = 0; i < shape.getRectangles().length; i++) {
            for (int j = 0; j < shape.getRectangles()[j].length; j++) {
                shape.getRectangles()[i][j].getRectangle().setFill(Color.WHITE);
            }
        }
    }

    public void disableShapeView(ShapeColor color, boolean disable) {
        ShapeView shape = shapesView.get(color);
        shape.setDisable(disable);
    }

    /**
     * Converts a ShapeColor enum value to the corresponding JavaFX Color
     * object.
     *
     * @param color the ShapeColor value to convert
     * @return the corresponding JavaFX Color object
     */
    private Color toFXColor(ShapeColor color) {
        switch (color) {
            case RED:
                return Color.RED;
            case LIGHTBLUE:
                return Color.LIGHTBLUE;
            case GREY:
                return Color.GREY;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case ORANGE:
                return Color.ORANGE;
            case BROWN:
                return Color.BROWN;
            case PURPLE:
                return Color.PURPLE;
            default:
                return Color.YELLOW;
        }
    }

    /**
     * Maps an index to the corresponding ShapeColor enum value.
     *
     * @param index the index value to map
     * @return the corresponding ShapeColor value
     */
    private ShapeColor switchColor(int index) {
        switch (index) {
            case 1:
                return ShapeColor.RED;
            case 2:
                return ShapeColor.LIGHTBLUE;
            case 3:
                return ShapeColor.GREY;
            case 4:
                return ShapeColor.GREEN;
            case 5:
                return ShapeColor.BLUE;
            case 6:
                return ShapeColor.ORANGE;
            case 7:
                return ShapeColor.BROWN;
            case 8:
                return ShapeColor.PURPLE;
            default:
                return ShapeColor.YELLOW;
        }

    }

    /**
     * Retrieves the map of shape views, where each shape color is mapped to its
     * corresponding shape view.
     *
     * @return the map of shape views
     */
    public Map<ShapeColor, ShapeView> getShapesView() {
        return shapesView;
    }

    /**
     * Updates the view of a specific shape color by setting the corresponding
     * shape components to their color.
     *
     * @param color the color of the shape to update
     * @param game the game object containing the shapes and their positions
     */
    public void updateViewShapeRotation(ShapeColor color, Game game) {
        //  clearShapeView(color);
        ShapeView shape = shapesView.get(color);
        List<ShapeComponent> components = game.getShapes().get(color).getShapeComponents();
        for (int i = 0; i < components.size(); i++) {
            Position pos = components.get(i).getPos();
            Color fxColor = toFXColor(color);
            shape.getRectangles()[pos.getRow()][pos.getCol()].getRectangle().setFill(fxColor);

        }

    }
}
