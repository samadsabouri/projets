package Model;

import java.util.Objects;

/**
 * The Circle class represents a circle shape with a specified radius, center
 * point, and color.
 *
 * This class extends the ColoredShape class.
 *
 * @author Samad 53960
 */
public class Circle extends ColoredShape {

    private double radius;
    private Point center;

    /**
     * Creates a new Circle object with the specified radius, center point, and
     * color.
     *
     * @param radius The radius of the circle.
     * @param center The center point of the circle.
     * @param color The color of the circle.
     * @throws IllegalArgumentException if the radius is less than or equal to
     * 0.
     * @throws NullPointerException if the center point is null.
     */
    public Circle(double radius, Point center, char color) {
        super(color);
        if (radius <= 0) {
            throw new IllegalArgumentException("Le rayon doit etre strictement positifs");
        }
        this.radius = radius;
        this.center = Objects.requireNonNull(center, " Le centre doit etre strictement positif");
    }

    /**
     * Returns whether a given point is inside the circle.
     *
     * @param p The point to check.
     * @return true if the point is inside the circle, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;

    }

    /**
     * Moves the circle by the specified amount in the x and y directions.
     *
     * @param dx The amount to move the circle in the x direction.
     * @param dy The amount to move the circle in the y direction.
     */
    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    /**
     * Returns a string representation of the Circle object.
     *
     * @return a string in the format "Circle color"
     */
    @Override
    public String toString() {
        return "Circle " + getColor();
    }
}
