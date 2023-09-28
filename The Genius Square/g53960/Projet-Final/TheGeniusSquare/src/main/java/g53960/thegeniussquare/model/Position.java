package g53960.thegeniussquare.model;

/**
 * Represents a position on the game board.
 *
 * @author Samad
 */
public class Position {

    private int row;
    private int col;

    /**
     *
     * Constructs a new instance of the Position class with the specified row
     * and column.
     *
     * @param row the row of the position
     * @param col the column of the position
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row of the position.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column of the position.
     *
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the position to the specified row and column.
     *
     * @param row the new row value
     * @param col the new column value
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns a string representation of the position.
     *
     * @return the string representation of the position in the format
     * "(row,col)"
     */
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    /**
     * Generates a hash code for the position.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.row;
        hash = 59 * hash + this.col;
        return hash;
    }

    /**
     * Checks if the position is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }

}
