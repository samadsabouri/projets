
package Model;

/**
 *Interface representing a geometric shape.
 * @author Samad 53960
 */
public interface Shape {
  
    /**
     * Checks if a given point is inside the shape.
     * @param p  the point to check
     * @return  true if the point is inside the shape, false otherwise
     */
boolean isInside(Point p);

/**
 * Moves the shape by a given amount along the x and y axes.
 * @param dx the amount to move along the x axis
 * @param dy the amount to move along the y axis
 */
void move(double dx, double dy);

/**
 * Gets the color of the shape.
 * @return  the color of the shape
 */
char getColor();

    /**
     * Setter of color
     *
     * @param color the color to change
     */
    public void setColor(char color);

}