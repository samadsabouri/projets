package g53960.luckynumbers.model;

/**
 * Represents the position of the tile on the board.
 *
 * @author Samad(53960)
 */
public class Position {

    private int row;
    private int column;

    /**
     * Simple constructor of Position.
     *
     * @param row the given row.
     * @param column the given column.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Simple getter of row.
     *
     * @return the row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Simple getter of column.
     *
     * @return the column.
     */
    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "( " + this.row + " , " + this.column + " )";
    }

}
