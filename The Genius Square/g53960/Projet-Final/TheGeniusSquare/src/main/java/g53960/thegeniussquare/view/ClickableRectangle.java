package g53960.thegeniussquare.view;

import g53960.thegeniussquare.model.Position;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a clickable rectangle in the game view. Extends the Pane class and
 * contains a Rectangle object.
 *
 * @author Samad
 */
public class ClickableRectangle extends Pane {

    private final Rectangle rectangle;

    /**
     * Constructs a new ClickableRectangle object with the specified position.
     *
     * @param pos The position of the clickable rectangle.
     */
    public ClickableRectangle(Position pos) {
        rectangle = new Rectangle(25, 25);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
    }

    /**
     * Retrieves the underlying Rectangle object.
     *
     * @return The Rectangle object.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

}
