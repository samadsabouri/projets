package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Position;
import javafx.scene.layout.GridPane;

/**
 ** The ShapeView class represents a grid of clickable rectangles used for
 * displaying a shape. Each rectangle in the grid can be clicked and associated
 * with a specific position.
 *
 * @author Samad
 */
public class ShapeView extends GridPane {

    private final ClickableRectangle[][] rectangles;

    /**
     * Creates a new ShapeView instance.
     */
    public ShapeView() {

        int rows = 8;
        int columns = 7;
        this.rectangles = new ClickableRectangle[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Position pos = new Position(i, j);
                ClickableRectangle clickableRectangle = new ClickableRectangle(pos);
                rectangles[i][j] = clickableRectangle;
                add(clickableRectangle, j, i);
            }
        }

    }

    /**
     * Gets the array of clickable rectangles in the shape view.
     *
     * @return the array of clickable rectangles
     */
    public ClickableRectangle[][] getRectangles() {
        return rectangles;
    }

}
