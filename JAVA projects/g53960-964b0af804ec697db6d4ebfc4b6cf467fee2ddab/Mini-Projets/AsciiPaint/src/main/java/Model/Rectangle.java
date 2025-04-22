package Model;

import java.util.Objects;

/**
 * The Rectangle class represents a colored rectangle shape defined by an
 * upper-left point, a width and a height.
 *
 * It extends the ColoredShape abstract class and implements the Shape
 * interface.
 *
 * @author Samad 53960
 */
public class Rectangle extends ColoredShape {

    private Point upperLeft; //coin haut a gauche 
    private double width; //longueur
    private double height; //largeur

    /**
     * Constructs a new Rectangle with the given upper-left point, width, height
     * and color.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @throws IllegalArgumentException if the width or height is not strictly
     * positive
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);
        Objects.requireNonNull(upperLeft, "Le point doit être spécifié");
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("La longueur et la largeur doivent être strictement positives");
        }
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;

    }

    /**
     * Checks if the given point is inside the rectangle.
     *
     * @param p the point to check
     * @return true if the point is inside the rectangle, false otherwise
     */
    @Override
    public boolean isInside(Point p) {
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return upperLeft.isLeftTo(p) && bottomRight.isRightTo(p) && upperLeft.isLowerThan(p)
                && bottomRight.isUpperThan(p);
    }

    /**
     * Moves the rectangle by the given amounts in the x and y directions.
     *
     * @param dx the amount to move the rectangle in the x direction
     * @param dy the amount to move the rectangle in the y direction
     */
    @Override
    public void move(double dx, double dy) {
        upperLeft.move(dx, dy);

    }

    /**
     * Returns a string representation of the rectangle in the format "Rectangle
     * color".
     *
     * @return A string representation of the rectangle.
     *
     */
    @Override
    public String toString() {
        return "Rectangle " + getColor();
    }

}
