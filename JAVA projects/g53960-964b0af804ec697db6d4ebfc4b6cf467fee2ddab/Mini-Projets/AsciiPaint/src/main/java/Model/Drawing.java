package Model;

import java.util.*;

/**
 *
 * @author Samad 53960
 */
public class Drawing {

    private List<Shape> shapes;
    private int height;
    private int width;

    /**
     * Creates a new Drawing object with default height and width values.
     */
    public Drawing() {
        this(40, 20);
    }

    /**
     * Creates a new Drawing object with the specified height and width values.
     *
     * @param height The height of the drawing.
     * @param width The width of the drawing.
     * @throws IllegalArgumentException if the height or width is less than or
     * equal to 0.
     */
    public Drawing(int height, int width) {
        if (height <= 0) {
            throw new IllegalArgumentException("La hauteur doit etre strictement positif");
        }

        if (width <= 0) {
            throw new IllegalArgumentException("La largeure doit etre strictement positif");
        }
        this.height = height;
        this.width = width;
        this.shapes = new ArrayList<>();
    }

    /**
     * Adds a shape to the drawing.
     *
     * @param shape The shape to add.
     * @throws NullPointerException if the shape is null.
     */
    public void addShape(Shape shape) {
        Objects.requireNonNull(shape, " une forme ne peut pas etre null");
        shapes.add(shape); //ajout un shape à la liste des shapes
    }

    /**
     * Returns the shape at the specified point, or null if there is no shape at
     * that point.
     *
     * @param point The point to check.
     * @return The shape at the specified point, or null if there is no shape at
     * that point.
     */
    public Shape getShapeAt(Point point) {
        for (Shape shape : shapes) {
            if (shape.isInside(point)) {
                return shape;
            }
        }
        return null;

    }

    /**
     * Returns the height of the drawing.
     *
     * @return The height of the drawing.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the drawing.
     *
     * @return The width of the drawing.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns a list of all shapes currently stored in the AsciiPaint object.
     *
     * @return A list of Shape objects.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * Prints an ASCII representation of the drawing to the console, where each
     * character represents a shape's color and empty spaces are represented by
     * blank spaces.
     */
    public void asAscii() {

        StringBuilder result = new StringBuilder();
        for (int row = 0; row < getHeight(); row++) {
            for (int column = 0; column < getWidth(); column++) {
                Point point = new Point(row, column);
                Shape shape = getShapeAt(point);

                if (shape != null) {
                    result.append(shape.getColor());
                } else {
                    result.append(".");
                }

            }
            result.append("\n");

        }
        System.out.println(result);
    }

    /**
     * Sets the list of shapes to the specified list of shapes.
     *
     * @param shapes The new list of shapes.
     * @throws NullPointerException if the new list of shapes is null.
     */
    public void setShapes(List<Shape> shapes) {
        Objects.requireNonNull(shapes, "La nouvelle liste de formes ne peut pas etre null");
        this.shapes = shapes;
    }

}
