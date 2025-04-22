package g53960.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The game which contain all the methods to manipulate tiles on the boards of
 * all the players.
 *
 * @author Samad(53960)
 */
public class Game implements Model {

    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private State state;
    private Deck deck;

    public Game() {
        this.state = State.NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {

        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            throw new IllegalStateException("The state must be Not_STARTED or  GAME_OVER and it is " + this.state);
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("The count player must be include 2 and 4 ");
        }
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];

        for (int i = 0; i < playerCount; i++) {
            boards[i] = new Board();

        }
        this.deck = new Deck(playerCount);
        this.currentPlayerNumber = 0;
        state = State.PICK_TILE;

    }

    @Override
    public int getBoardSize() {
        return this.boards[currentPlayerNumber].getSize();
    }

    public Tile pickTile() {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("the state is not pick_Tile");
        }
        int value = (int) ((Math.random() * 20) + 1);
        this.pickedTile = new Tile(value);
        this.state = State.PLACE_TILE;
        return this.pickedTile;
    }

    public void putTileV1(Position pos) {
        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("the state is not Place_Tile");
        }
        if (!this.isInside(pos) || !this.canTileBePut(pos)) {
            throw new IllegalArgumentException("the given position is not on "
                    + "the current player's board or the given position"
                    + " doesn't respect the rules of the game.");
        }
        this.boards[currentPlayerNumber].put(pickedTile, pos);
        if (!boards[currentPlayerNumber].isFull() && this.faceDownTileCount() > 0) {
            this.state = State.TURN_END;
        } else {
            this.state = State.GAME_OVER;
        }

    }

    @Override
    public void nextPlayer() {
        if (state != State.TURN_END) {
            throw new IllegalArgumentException("the state is not TURN_END.");
        }
        this.currentPlayerNumber += 1;
        this.currentPlayerNumber %= playerCount;
        this.state = State.PICK_TILE;
    }

    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("state is not NOT_STARTED");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == State.NOT_STARTED || state == State.GAME_OVER) {
            throw new IllegalStateException("state is NOT_STARTED or"
                    + " state is GAME_OVER");
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("state is not PLACE_TILE and is not PLACE_OR_DROP_TILE");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return this.boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("state is not PLACE_TILE");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("position is outside the board.");
        }
        return this.boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (this.state == State.NOT_STARTED) {
            throw new IllegalArgumentException("state is NOT_STARTED");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("the given position is outside the board");
        }
        if (playerNumber > playerCount || playerNumber < 0) {
            throw new IllegalArgumentException("the currentPlayer is out of range.");
        }

        if (this.boards[playerNumber] == null) {
            return null;
        }
        return this.boards[playerNumber].getTile(pos);
    }

    /**
     * Simple Setter of state used only for test.
     *
     * @param state the given state de update
     */
    protected void setState(State state) {
        this.state = state;
    }

    /**
     * Simple getter of deck used for test.
     *
     * @return the deck.
     */
    protected Deck getDeck() {
        return deck;
    }

    /**
     * Counts the numbers of empty tiles of the given player.
     *
     * @param player the given player index
     * @return the number of empty tiles of the board of the given player.
     */
    private List<Integer> countEmptyTiles() {
        List<Integer> countEmptyTiles = new ArrayList<Integer>();
        for (int playerIndex = 0; playerIndex < this.playerCount; playerIndex++) {
            int count = 0;
            for (int i = 0; i < getBoardSize(); i++) {
                for (int j = 0; j < getBoardSize(); j++) {
                    if (this.boards[playerIndex].getTile(new Position(i, j)) == null) {
                        count++;
                    }
                }
            }
            countEmptyTiles.add(count);
        }
        return countEmptyTiles;
    }

    /**
     * Simple getter Of players with the minimum number of empty tiles.
     *
     * @return the list of players indexes.
     */
    private List<Integer> getPlayersWithMinimumEmptyTiles(List<Integer> countEmptyTiles) {
        int index = 0;
        List<Integer> playersMinEmptyTiles = new ArrayList<>();
        int minimum = countEmptyTiles.get(0);
        for (int i = 0; i < playerCount; i++) {

            index = i + 1;
            while (index < playerCount && minimum > countEmptyTiles.get(index)) {
                minimum = countEmptyTiles.get(index);
                index++;
            }
        }
        for (int i = 0; i < countEmptyTiles.size(); i++) {
            if (countEmptyTiles.get(i) == minimum) {
                playersMinEmptyTiles.add(i);
            }
        }
        return playersMinEmptyTiles;
    }

    @Override
    public List<Integer> getWinners() {
        if (this.state != State.GAME_OVER) {
            throw new IllegalArgumentException("state is not GAME_OVER");
        }
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < this.playerCount; i++) {
            if (this.boards[i].isFull()) {
                winners.add(i);
                return winners;
            }
        }
        if (this.faceDownTileCount() == 0) {
            winners = getPlayersWithMinimumEmptyTiles(countEmptyTiles());
        }
        return winners;
    }

    /**
     * Allows to compare Two elements in the list.
     */
    private class TileValues implements Comparator<Tile> { //https://stackoverflow.com/questions/1206073/sorting-a-collection-of-objects

        @Override
        public int compare(Tile firstTile, Tile secondTile) {
            return firstTile.getValue() - secondTile.getValue();

        }
    }

    /**
     * Simple getter of board used for test.
     *
     * @return the game current player board.
     */
    protected Board getBoard() {
        return this.boards[currentPlayerNumber];
    }

    @Override
    public void putDiagonalRandomTile() {
        List<Tile> tilesToPut = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tilesToPut.add(deck.getTilesFaceDown().remove(this.faceDownTileCount() - 1));
        }
        Collections.sort(tilesToPut, new TileValues());
        int index = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (i == j) {
                    this.boards[currentPlayerNumber].put(tilesToPut.get(index), new Position(i, j));
                    index++;
                }
            }

        }
        this.state = State.TURN_END;
    }

    /**
     * Pick a tile with the given value.Should be used only for the JUnit tests.
     *
     * @param value
     * @return the picked tile.
     */
    public Tile pickTile(int value) {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("the state is not pick_Tile");
        }
        this.pickedTile = new Tile(value);
        this.state = State.PLACE_TILE;
        return this.pickedTile;
    }

    @Override
    public void putTile(Position pos) {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("the state is not PLACE_TILE and not PLACE_OR_DROP_TILE");
        }
        if (!this.isInside(pos) || !this.canTileBePut(pos)) {
            throw new IllegalArgumentException("the given position is not on "
                    + "the current player's board or the given position"
                    + " doesn't respect the rules of the game.");
        }
        if (this.boards[currentPlayerNumber].getTile(pos) != null
                && this.pickedTile.getValue() == this.boards[currentPlayerNumber].getTile(pos).getValue()) {
            this.dropTile();
        }
        this.boards[currentPlayerNumber].put(pickedTile, pos);

        if (!boards[currentPlayerNumber].isFull() && deck.faceDownCount() != 0) {
            this.state = State.TURN_END;
        } else {
            this.state = State.GAME_OVER;
        }

    }

    @Override
    public void pickFaceUpTile(Tile tile) {

        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("the state is not pick_Tile");
        }

        if (this.deck.hasFaceUp(tile)) {
            this.pickedTile = this.deck.pickFaceUp(tile);
            this.state = State.PLACE_TILE;
        }
    }

    @Override
    public Tile pickFaceDownTile() {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("the state is not pick_Tile");
        }
        this.pickedTile = this.deck.pickFaceDown();
        this.state = State.PLACE_OR_DROP_TILE;
        return this.pickedTile;
    }

    @Override
    public void dropTile() {
        this.deck.putBack(this.pickedTile);
        this.state = State.TURN_END;
    }

    @Override
    public void ChangeToPLACE_TILEState() {
        this.state = State.PLACE_TILE;
    }

    @Override
    public int faceDownTileCount() {
        return this.deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        return this.deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        return this.deck.getAllFaceUp();
    }
}
