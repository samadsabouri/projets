package g53960.luckynumbers.model;

/**
 * The board which contain the tiles of the player who will the play the game.
 *
 * @author Samad(53960)
 */
public class Board {

    private Tile[][] tiles;

    /**
     * Simple constructor of Board.
     */
    public Board() {
        this.tiles = new Tile[4][4];
    }

    /**
     * Simple getter of size of the board.
     *
     * @return the size.
     */
    public int getSize() {
        return this.tiles.length;
    }

    /**
     * Checks if the given position is inside the board.
     *
     * @param pos the given position.
     * @return true otherwise return false.
     */
    public boolean isInside(Position pos) {
        return !(pos.getRow() < 0 || pos.getRow() > getSize() - 1 || pos.getColumn() < 0 || pos.getColumn() > getSize() - 1);
    }

    /**
     * Simple getter of tile according to the given position.
     *
     * @param pos the given position.
     * @return the tile.
     */
    public Tile getTile(Position pos) {
        return this.tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * Check if the given tile can be putted in the given position.
     *
     * @param tile the given tile.
     * @param pos the given position.
     * @return true if the put is authorized otherwise return false.
     */
    public boolean canBePut(Tile tile, Position pos) {
        int index = 0;

        while (index < getSize() && index < getSize()) {
            if (!(checkVerticalTiles(index, tile, pos) && checkHorizontalTiles(index, tile, pos))) {
                return false;
            }
            index++;

        }
        return true;
    }

    /**
     * Checks the avaibility of the column tiles.
     *
     * @param index the given index of column.
     * @param tile the given tile.
     * @param pos the given position.
     * @return true otherwise return false.
     */
    private boolean checkVerticalTiles(int index, Tile tile, Position pos) {
        if (isInside(pos)) {
            if (this.tiles[index][pos.getColumn()] != null && index != pos.getRow()) {
                if (index < pos.getRow()) {

                    if (tile.getValue() <= this.tiles[index][pos.getColumn()].getValue()) {
                        return false;
                    }
                }
                if (index > pos.getRow()) {
                    if (tile.getValue() >= this.tiles[index][pos.getColumn()].getValue()) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * Checks the avaibility of the row tiles.
     *
     * @param index the given index of row.
     * @param tile the given tile.
     * @param pos the given position.
     * @return true otherwise return false.
     */
    private boolean checkHorizontalTiles(int index, Tile tile, Position pos) {
        if (isInside(pos)) {
            if (this.tiles[pos.getRow()][index] != null && index != pos.getColumn()) {
                if (index < pos.getColumn()) {
                    if (tile.getValue() <= this.tiles[pos.getRow()][index].getValue()) {
                        return false;
                    }
                }

                if (index > pos.getColumn()) {
                    if (tile.getValue() > this.tiles[pos.getRow()][index].getValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Puts the given tile in the given position.
     *
     * @param tile the given tile.
     * @param pos the given position.
     */
    public void put(Tile tile, Position pos) {
        this.tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * Checks if the board is full.
     *
     * @return true otherwise return false.
     */
    public boolean isFull() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (this.tiles[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
