
package Model;

/**
 * The ColoredShape class is an abstract class that represents a shape with a color.

 This class implements the Shape interface
 * @author Samad 53960
 */
public abstract class ColoredShape implements Shape{
    private char color;

    /**
     * Constructor Creates a new ColoredShape object with the specified color.
     * @param color 
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * Returns the color of the shape.
     * @return The color of the shape.
     */
    @Override
    public char getColor() {
        return color;
    }

    /**
     * Sets the color of the shape.
     * @param color  The new color of the shape.
     */
    @Override
    public void setColor(char color) {
        this.color = color;
    }
    
    
}
