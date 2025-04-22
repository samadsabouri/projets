package Model;

import java.util.Objects;

/**
 * A class representing a 2D point with x and y coordinates.
 *
 * @author Samad 53960
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a Point object with the same coordinates as the given point.
     *
     * @param p the point to be copied.
     * @throws NullPointerException if the given point is null.
     */
    public Point(Point p) { //constructeur par copie
        //this(p.x,p.y); aussi ok
        Objects.requireNonNull(p, "Le point doit etre strictement positif"); //exeptio if == null throw exep..
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Constructs a Point object with the given x and y coordinates.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        //ajout exeption que positifs x et y
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the point by the given amounts in the x and y directions.
     *
     * @param dx the amount to move the point in the x direction.
     * @param dy dy the amount to move the point in the y direction.
     */
    public void move(double dx, double dy) {
//poser tjrs la question si on aura besoin d'exeptions (on accepte les positifs? negatifs?
//pas le cas ici on accepte tout, donc je mets pas d'exeption
        x += dx;
        y += dy;
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return the x coordinate of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return the y coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Computes the distance between this point and the given point.
     *
     * @param other the other point to compute the distance to.
     * @return the distance between this point and the given point.
     * @throws NullPointerException if the given point is null.
     */
    public double distanceTo(Point other) {
        //formule de math pour calcul distance de deux points
        Objects.requireNonNull(other, "La point other doit etre strictement positif");
        double resultX = Math.pow(other.x - x, 2);
        double resultY = Math.pow(other.y - y, 2);
        return Math.sqrt(resultX + resultY);
    }

    /**
     * Checks if this point is to the left of the given point.
     *
     * @param p the other point to compare to.
     * @return true if this point is to the left of the given
     */
    boolean isLeftTo(Point p) {
        return x <= p.x;
    }

    /**
     * Checks if this point is to the right of the given point.
     *
     * @param p the other point to compare to.
     * @return true if this point is to the right of the given point, false
     * otherwise.
     */
    boolean isRightTo(Point p) {
        return p.x < x;
    }

    /**
     * Checks if this point is below the given point.
     *
     * @param p the other point to compare to.
     * @return true if this point is below the given point, false otherwise.
     */
    boolean isLowerThan(Point p) {
        return y <= p.y;
    }

    /**
     * Checks if this point is above the given point.
     *
     * @param p the other point to compare to.
     * @return true if this point is above the given point, false otherwise.
     */
    boolean isUpperThan(Point p) {
        return p.y < y;
    }
}
