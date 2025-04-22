package Model;

/**
 * A class representing a line in 2D space.
 *
 * The line is defined by its starting point and ending point.
 *
 * @author Samad
 */
public class Line extends ColoredShape {

    private Point pointA;
    private Point pointB;

    public Line(Point pointA, Point pointB, char color) {
        super(color);
        if (pointA.getX() < 0 || pointA.getY() < 0 || pointB.getX() < 0 || pointB.getY() < 0) {
            throw new IllegalArgumentException("The coordinate must be positif");
        } else {
            this.pointA = pointA;
            this.pointB = pointB;
        }
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    @Override
    public boolean isInside(Point point) {
        return distanceTo(point) == 0;

    }

    @Override
    public void setColor(char c) {
        super.setColor(c);
    }

    public double distanceTo(Point point) {
        double m = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());
        return Math.abs(m * point.getX() - point.getY() - m * pointA.getX() + pointA.getY()) / Math.sqrt(Math.pow(m, 2) + 1);
    }

    @Override
    public void move(double dx, double dy) {
        this.pointA.move(dx, dy);
        this.pointB.move(dx, dy);
    }

    /**
     *
     * Returns a string representation of the line shape, including its color.
     *
     * @return a string representation of the line shape
     */
    @Override
    public String toString() {
        return "Line " + getColor();
    }
}
