package g53960.thegeniussquare.view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The SquareBoardView class represents a square-shaped board view. It extends
 * the Button class and contains a rectangle shape.
 *
 * @author Samad
 */
public class SquareBoardView extends Button {

    private final Rectangle rectangle;

    /**
     *  * Creates a new instance of SquareBoardView. Initializes the rectangle
     * shape with a default size of 43x43 pixels, white fill, and black stroke.
     */
    public SquareBoardView() {
        this.rectangle = new Rectangle(43, 43);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

    }

    /**
     * Retrieves the rectangle shape associated with the square board view.
     *
     * @return the rectangle shape
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

}
