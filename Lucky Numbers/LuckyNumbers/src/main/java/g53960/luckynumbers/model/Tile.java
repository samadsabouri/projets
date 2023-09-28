package g53960.luckynumbers.model;

/**
 * Represents the tile which contain the random value to be putted on the board.
 *
 * @author Samad(53960)
 */
public class Tile {

    private int value;
    private boolean faceUp; 

    /**
     * Simple constructor of Tile.
     *
     * @param value the given value.
     */
    public Tile(int value) {

        this.value = value;
        this.faceUp = false;

    }

    /**
     * Simple getter of value.
     *
     * @return the value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Simple getter of faceUp.
     *
     * @return the faceUp.
     */
    public boolean isFaceUp() {
        return this.faceUp;
    }

    /**
     * Makes the Tile visible
     */
    public void flipFaceUp() {
        if (this.faceUp != true) {
            this.faceUp = true;
        }
    }     
}
