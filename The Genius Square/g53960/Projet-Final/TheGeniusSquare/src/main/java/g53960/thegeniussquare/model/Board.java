package g53960.thegeniussquare.model;

/**
 * Represents the game board for The Genius Square game. The board consists of
 * squares with different components. Each square can contain a shape or a
 * blocker.
 *
 * @author Samad
 */
public class Board {

    private final ShapeComponent[][] squares;

    /**
     * Constructs a new board with the specified positions for blockers.
     *
     * @param blockersPositions the positions of the blockers on the board
     */
    public Board(Position[] blockersPositions) {
        this.squares = new ShapeComponent[6][6];
        createSquares();
        putBlockers(blockersPositions);

    }

    /**
     * Creates the squares of the board and initializes them with neutral shape
     * components.
     */
    private void createSquares() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                this.squares[i][j] = new ShapeComponent(new Position(i, j), ShapeType.NEUTRAL, ShapeColor.WHITE);
            }
        }
    }

    /**
     * Puts blockers on the board at the specified positions.
     *
     * @param blockersPositions the positions of the blockers
     */
    private void putBlockers(Position[] blockersPositions) {

        for (Position blockerPos : blockersPositions) {
            squares[blockerPos.getRow()][blockerPos.getCol()] = new ShapeComponent(blockerPos, ShapeType.BLOCKER, ShapeColor.BLACK);
        }
    }

    /**
     * Returns the squares of the board.
     *
     * @return the squares of the board
     */
    public ShapeComponent[][] getSquares() {
        return squares;
    }

    public void setSquares(ShapeComponent[][] savedBoard) {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = savedBoard[i][j];
            }
        }

    }

}
