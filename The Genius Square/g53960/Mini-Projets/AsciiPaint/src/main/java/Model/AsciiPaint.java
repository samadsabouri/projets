package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Asciipaint class which represents a drawing
 *
 * @author Samad 53960
 */
public class AsciiPaint {

    private final Drawing drawing;
    private int counterCircle = 0;
    private int counterRectangle = 0;
    private int counterSquare = 0;
    private int counterLine = 0;

    /**
     * Constructor without param creates a new AsciiPaint object with an empty
     * Drawing.
     */
    public AsciiPaint() {
        this.drawing = new Drawing();
    }

    /**
     * Constructor Creates a new AsciiPaint object with a Drawing of the
     * specified height and width.
     *
     * @param height
     * @param width
     */
    public AsciiPaint(int height, int width) {

        drawing = new Drawing(height, width);
    }

    /**
     * Adds a new Circle object to the current Drawing.
     *
     * @param x The x-coordinate of the center of the circle.
     * @param y The y-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param color The color of the circle.
     * @return counterCircle
     */
    public int newCircle(int x, int y, double radius, char color) {
        Point center = new Point(x, y);
        Circle circle = new Circle(radius, center, color);
        drawing.addShape(circle);
        return ++counterCircle;
    }

    /**
     * Adds a new Rectangle object to the current Drawing.
     *
     * @param x The x-coordinate of the upper left corner of the rectangle.
     * @param y The y-coordinate of the upper left corner of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     * @return counterRectangle
     */
    public int newRectangle(int x, int y, double width, double height, char color) {
        Point upperLeft = new Point(x, y);
        Rectangle rectangle = new Rectangle(upperLeft, width, height, color);
        drawing.addShape(rectangle);
        return ++counterRectangle;
    }

    /**
     * Adds a new Square object to the current Drawing.
     *
     * @param x The x-coordinate of the upper left corner of the square.
     * @param y The y-coordinate of the upper left corner of the square.
     * @param side The length of each side of the square.
     * @param color The color of the square.
     * @return counterSquare
     */
    public int newSquare(int x, int y, double side, char color) {
        Point upperLeft = new Point(x, y);
        Square square = new Square(upperLeft, side, color);
        drawing.addShape(square);
        return ++counterSquare;
    }

    /**
     * Crée une nouvelle ligne avec les points de début et de fin spécifiés, de
     * la couleur spécifiée.
     *
     * @param x1 la coordonnée x du point de début
     * @param y1 la coordonnée y du point de début
     * @param x2 la coordonnée x du point de fin
     * @param y2 la coordonnée y du point de fin
     * @param color la couleur de la ligne
     * @return l'identifiant de la ligne créée
     */
//    public int newLine(int x1, int y1, int x2, int y2, char color) {
//        Point beginPoint = new Point(x1, y1);
//        Point endPoint = new Point(x2, y2);
//        Line line = new Line(beginPoint, endPoint, color);
//        drawing.addShape(line);
//        return ++counterLine;
//    }
    /**
     * Adds a new line to the painting.
     *
     * @param x the x coordinate of the first point.
     * @param y the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     * @param color the color.
     * @return the number of lines
     */
    public int newLine(int x, int y, int x2, int y2, char color) {
        Line line = new Line(new Point(x, y), new Point(x2, y2), color);
        drawing.addShape(line);
        return ++counterLine;
    }

    /**
     * Returns the Drawing object associated with this AsciiPaint.
     *
     * @return The Drawing object associated with this AsciiPaint.
     */
    public Drawing getDrawing() {
        return drawing;
    }

    /**
     * Getter of shapes
     *
     * @return the sapes attribute.
     */
    public List<Shape> getAllShapes() {
        return drawing.getShapes();
    }

    /**
     * Gets the size of list shape.
     *
     * @return size of the list.
     */
    public int getShapeSizeList() {
        return getAllShapes().size();
    }

    /**
     * Deletes the shape at the specified index from the list of shapes in the
     * drawing.
     *
     * @param index the index of the shape to be deleted.
     */
    public void deleteShape(int index) {

        drawing.getShapes().remove(index);

    }

    /**
     * Moves a shape in the drawing to a new position.
     *
     * @param index The index of the shape to move.
     * @param newX The new x-coordinate of the shape.
     * @param newY The new y-coordinate of the shape.
     */
    public void moveShape(int index, int newX, int newY) {
        if (index >= 0 && index < drawing.getShapes().size()) {
            Shape shape = drawing.getShapes().get(index);
            shape.move(newX, newY);
        }
    }

    /**
     * Sets the color of a shape at the specified index.
     *
     * @param index the index of the shape in the drawing
     * @param color the new color of the shape, represented as a character
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void setColor(int index, char color) throws IndexOutOfBoundsException {
        if (index < 0 || index >= drawing.getShapes().size()) {
            throw new IndexOutOfBoundsException("Index invalide : " + index);
        }
        Shape shape = drawing.getShapes().get(index);
        shape.setColor(color);
    }

    /**
     * This method groups multiple shapes.
     *
     * @param indexes list of indices of shapes to be grouped.
     *
     */
    public void groupShape(List<Integer> indexes) {
        List<Shape> shapes = new ArrayList<>();
        char groupColor = '\0';
        for (int i : indexes) {
            Shape shape = drawing.getShapes().get(i);
            shapes.add(shape);
            if (groupColor == '\0') {
                groupColor = shape.getColor();
            }
        }
        ShapeGroup group = new ShapeGroup(shapes);
        group.setColor(groupColor);
        drawing.getShapes().removeAll(shapes);
        drawing.getShapes().add(group);
    }

    /**
     * this method will ungroup the group.
     *
     * @param index of the group.
     */
    public void ungroupShape(int index) {
        ShapeGroup compo = (ShapeGroup) drawing.getShapes().get(index);
        for (int i = 0; i < compo.getShapes().size(); i++) {
            drawing.getShapes().add(compo.getShapes().get(i));

        }
        drawing.getShapes().remove(compo);
    }
}
