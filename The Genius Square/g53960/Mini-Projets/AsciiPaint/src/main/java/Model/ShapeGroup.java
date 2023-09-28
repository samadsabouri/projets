package Model;

import java.util.List;

/**
 * The ShapeGroup class represents a group of shapes.
 *
 * It implements the Shape interface and provides methods to manipulate a
 * collection of Shape objects as a single entity.
 *
 * @author Samad
 */
public class ShapeGroup implements Shape {

    private final List<Shape> shapes;
    private char color;

    /**
     * Constructs a new ShapeGroup object with the specified list of shapes.
     *
     * @param shapes the list of Shape objects to be contained in this
     * ShapeGroup.
     */
    public ShapeGroup(List<Shape> shapes) {
        this.shapes = shapes;
        if (shapes.size() > 0) {
            this.color = shapes.get(0).getColor();
        } else {
            this.color = '\0';
        }
    }

    /**
     *
     * Returns the list of Shape objects contained in this ShapeGroup.
     *
     * @return the list of Shape objects contained in this ShapeGroup.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     *
     * Sets the color of this ShapeGroup.
     *
     * @param color the color to be set.
     */
    @Override
    public void setColor(char color) {
        this.color = color;
    }

    /**
     *
     *
     * Returns true if the specified point is inside any of the Shape objects
     * contained in this ShapeGroup, otherwise false.
     *
     * @param p the point to be tested.
     * @return true if the specified point is inside any of the Shape objects
     * contained in this ShapeGroup, otherwise false.
     */
    @Override
    public boolean isInside(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     *
     * Moves all the Shape objects contained in this ShapeGroup by the specified
     * distances dx and dy.
     *
     * @param dx the distance to move along the x-axis.
     * @param dy the distance to move along the y-axis.
     */
    @Override
    public void move(double dx, double dy) {
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    /**
     *
     * Returns the color of this ShapeGroup.
     *
     * @return the color of this ShapeGroup.
     */
    @Override
    public char getColor() {
        return color;
    }

    /**
     *
     * Returns a string representation of this ShapeGroup.
     *
     * @return a string representation of this ShapeGroup.
     */
    @Override
    public String toString() {
        return "ShapeGroup " + getColor();
    }

    /**
     * Checks if this shape is a group of shapes.
     *
     * @return true if this shape is a group, false otherwise.
     */
    public boolean isGroup() {
        return (this instanceof ShapeGroup);
    }
}
