package Model;

/**
 * A Square class that extends Rectangle and represents a square shape in a 2D
 * plane.
 *
 * @author Samad 53960
 */
public class Square extends Rectangle {

    /**
     * Constructs a Square object with a given upper left point, side length,
     * and color.
     *
     * @param upperLeft the upper left point of the square
     * @param side the length of the square's sides
     * @param color the color of the square
     */
    public Square(Point upperLeft, double side, char color) {
        super(upperLeft, side, side, color);
    }

    /**
     * Returns a string representation of the square, in the format: "Square " +
     * the color of the square.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Square " + getColor();
    }
}
