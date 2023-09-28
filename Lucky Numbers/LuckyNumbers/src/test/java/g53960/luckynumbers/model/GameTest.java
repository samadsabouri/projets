package g53960.luckynumbers.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be>
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTileV1(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTileV1(new Position(line, col));
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* === À vous de compléter... === */
    @Test
    public void TestBoardSizeIsFour() {
        fullPlay();
        int result = this.game.getBoardSize();
        int expectedResult = 4;
        assertEquals(result, expectedResult);
    }

    @Test
    public void Test_pickTileOk() {
        this.game.start(2);
        int value = 4;
        this.game.pickTile(value);
        assertEquals(this.game.getPickedTile().getValue(), value);

    }

    @Test
    public void Test_pickTileSteChangedToPLACE_TILE() {
        this.game.start(2);
        int value = 4;
        this.game.pickTile(value);
        assertTrue(this.game.getState() == State.PLACE_TILE);
    }

    @Test
    public void Test_putTileStateNotPick_Tile() {

        assertThrows(IllegalStateException.class,
                () -> game.putTileV1(new Position(0, 0)));
    }

    @Test
    public void Test_putTilePostionNotInside() {
        this.game.start(2);
        this.game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.putTileV1(new Position(-1, 0)));
    }

    @Test
    public void Test_putTileDoNotRespectGameRules() {
        this.game.start(2);
        this.game.pickTile(5);
        game.putTileV1(new Position(0, 1));
        this.game.nextPlayer();
        this.game.pickTile(4);
        this.game.putTileV1(new Position(2, 3));
        this.game.nextPlayer();
        this.game.pickTile(8);
        assertThrows(IllegalArgumentException.class,
                () -> game.putTileV1(new Position(0, 0)));

    }

    @Test
    public void Test_nextPlayer() {
        this.game.start(2);
        this.game.pickTile(4);
        this.game.putTileV1(new Position(0, 1));
        this.game.nextPlayer();
        int currentPlayerExpected = 1;
        int result = this.game.getCurrentPlayerNumber();
        assertEquals(result, currentPlayerExpected);
    }

    @Test
    public void Test_nextPlayerAfterTheRound() {
        this.game.start(2);
        this.game.pickTile(4);
        this.game.putTileV1(new Position(0, 1));
        this.game.nextPlayer();
        this.game.pickTile(8);
        this.game.putTileV1(new Position(2, 1));
        this.game.nextPlayer();
        int currentPlayerExpected = 0;
        int result = this.game.getCurrentPlayerNumber();
        assertEquals(result, currentPlayerExpected);
    }

    @Test
    public void Test_nextPlayerStateNotTURN_END() {
        assertThrows(IllegalArgumentException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void Test_getPlayerCountStateNotNOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getPlayerCount());
    }

    @Test
    public void Test_getPlayerCountOk() {
        this.game.start(4);
        int result = this.game.getPlayerCount();
        int expectedResult = 4;
        assertEquals(expectedResult, result);
    }

    @Test
    public void Test_getStateNOT_STARTED() {
        State result = this.game.getState();
        State expectedResult = State.NOT_STARTED;
        assertEquals(result, expectedResult);
    }

    @Test
    public void Test_getState() {
        State result = this.game.getState();
        State expectedResult = State.NOT_STARTED;
        assertEquals(result, expectedResult);
    }

    @Test
    public void Test_getStateGAME_OVER() {
        fullPlay();
        State result = this.game.getState();
        State expectedResult = State.GAME_OVER;
        assertEquals(result, expectedResult);
    }

    @Test
    public void Test_getCurrentPlayerNumberStateNOT_START() {
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber());
    }

    @Test
    public void Test_getCurrentPlayerNumberOK() {
        game.start(2);
        int result = this.game.getCurrentPlayerNumber();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

    @Test
    public void Test_isInsideOk() {
        game.start(2);
        boolean result = this.game.isInside(new Position(3, 3));
        assertTrue(result);
    }

    @Test
    public void Test_isInsideNotOk() {
        game.start(4);
        boolean result = this.game.isInside(new Position(-1, 4));
        assertFalse(result);
    }

    @Test
    public void Test_canTileBePutStateNotPLACE_TILE() {
        this.game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(0, 0)));

    }

    @Test
    public void Test_canTileBePut() {
        this.game.start(2);
        this.game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(new Position(-1, 0)));

    }

    @Test
    public void Test_getTileStateNOT_STARTED() {
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(0, new Position(0, 0)));
    }

    @Test
    public void Test_getTileStatePositionNotInside() {
        this.game.start(4);
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(0, new Position(-1, 0)));
    }

    @Test
    public void Test_getTilePlayerNotInTheRange() {
        this.game.start(4);
        this.game.pickTile(4);
        this.game.putTileV1(new Position(0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(5, new Position(0, 0)));
    }

    @Test
    public void Test_getWinnerStateNotGAME_OVER() {
        assertThrows(IllegalArgumentException.class,
                () -> game.getWinners());
    }

    @Test
    public void Test_PutTileStateDifferentToPlaceOrDrop() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 0)));
    }

    @Test
    void Test_PickFaceDownWhenStateDifferntePICK_TILE() {
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile());
    }

    @Test
    void Test_PickFaceDownChangeState() {
        this.game.start(4);
        this.game.pickFaceDownTile();
        State expected = State.PLACE_OR_DROP_TILE;
        State result = game.getState();
        assertEquals(expected, result);
    }

    @Test
    void Test_faceDownTileCountBeforePick() {
        game.start(2);
        int expect = 40;
        int result = game.faceDownTileCount();
        assertEquals(expect, result);

    }

    @Test
    void Test_faceDownTileCountAfterPick() {
        game.start(2);
        game.pickFaceDownTile();
        int expect = 39;
        int result = game.faceDownTileCount();
        assertEquals(expect, result);
    }

    @Test
    void Test_faceUpTileCountBeforePick() {
        game.start(2);
        int expect = 0;
        int result = game.faceUpTileCount();
        assertEquals(expect, result);
    }

    @Test
    void Test_faceUpTileCountAfterDrop() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        int expect = 1;
        int result = game.faceUpTileCount();
        assertEquals(expect, result);
    }

    @Test
    void TestgetWinners_EmptyFaceDownListWithOneWinners() {
        game.start(4);
        game.pickFaceDownTile();
        game.setState(State.PLACE_TILE);
        this.game.putTile(new Position(0, 0));
        game.getDeck().clearTilesFaceDown();
        this.game.setState(State.GAME_OVER);
        List<Integer> winners = game.getWinners();
        int expected = 1;
        int result = winners.size();
        assertEquals(expected, result);
    }

    @Test
    void TestgetWinners_EmptyFaceDownList() {
        game.start(4);
        game.getDeck().clearTilesFaceDown();
        this.game.setState(State.GAME_OVER);
        List<Integer> winners = game.getWinners();
        System.out.println(winners.size());
        int expected = 4;
        int result = winners.size();
        assertEquals(expected, result);
    }

    @Test
    void TestputDiagonalRandomTile() {
        game.start(2);
        game.putDiagonalRandomTile();
        Board currentPlayerBoard = game.getBoard();
        int expected = 36;
        int result = game.faceDownTileCount();
        assertEquals(result, expected);

        int expectedCountTilesInBoard = 4;
        int resultCountTilesInBoard = 0;

        for (int i = 0; i < currentPlayerBoard.getSize(); i++) {
            for (int j = 0; j < currentPlayerBoard.getSize(); j++) {
                if (currentPlayerBoard.getTile(new Position(i, j)) != null) {
                    resultCountTilesInBoard++;
                }
            }
        }
        assertEquals(resultCountTilesInBoard, expectedCountTilesInBoard);
        int[] diagonalTiles = new int[resultCountTilesInBoard];
        for (int i = 0; i < currentPlayerBoard.getSize(); i++) {
            for (int j = 0; j < currentPlayerBoard.getSize(); j++) {
                if (i == j && currentPlayerBoard.getTile(new Position(i, j)) != null) {
                    Position p = new Position(i, j);
                    diagonalTiles[i] = currentPlayerBoard.getTile(p).getValue();
                }
            }
        }

        boolean expectedCroissantTiles = true;
        boolean resultCroissantTiles = true;

        int firstElement;
        int secondElement;
        int index = 0;
        while (index < diagonalTiles.length) {
            firstElement = diagonalTiles[index];
            if ((index + 1< diagonalTiles.length)) {
                secondElement = diagonalTiles[index + 1];

                if (firstElement > secondElement) {
                    resultCroissantTiles = false;
                    break;
                }
            }
            resultCroissantTiles = true;
            index++;
        }

        assertEquals(resultCroissantTiles, expectedCroissantTiles);

    }

}
