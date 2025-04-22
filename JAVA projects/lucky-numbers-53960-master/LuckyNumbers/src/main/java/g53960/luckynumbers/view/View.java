package g53960.luckynumbers.view;

import g53960.luckynumbers.model.Position;
import g53960.luckynumbers.model.Tile;
import java.util.List;

/**
 * Interface for the View.
 *
 * @author Samad(53960)
 */
public interface View {

    /**
     * Allows to display welcome to all the players before starting the game.
     */
    void displayWelcome();

    /**
     * Allows to display board of the current Player.
     */
    void displayGame();

    /**
     * Allows to display the players who have win the game.
     */
    void displayWinners();

    /**
     * Allows to ask the numbers of Players
     *
     * @return the numbers of players.
     */
    int askPlayerCount();

    /**
     * allows to ask a position of the player.
     *
     * @return the position.
     */
    Position askPosition();

    /**
     * Allows to display the error given message.
     *
     * @param Message the given message.
     */
    void displayError(String Message);

    /**
     * Allows to display the picked tile.
     */
    void displayPickedTile();

    /**
     * Allows to display the current player.
     */
    void displayCurrentPlayer();

    /**
     * Asks if the player want to replay another game.
     *
     * @return 1 otherwise return 0.
     */
    int askReplayGame();

    /**
     * Asks the player to choice which tile he want to pick.
     *
     * @return 0 if he want to pick a face down tile , 1 if he want to pick a
     * face up tile.
     */
    int askChoiceToPickTile();

    /**
     * Display the differnt tiles which user can choice one of them.
     *
     * @param CountFaceDownTiles the given number of the face down tiles.
     * @param faceUpTiles the given face up tiles list.
     */
    void DisplayTilesToChoice(int CountFaceDownTiles, List<Tile> faceUpTiles);

    /**
     * Ask the Player the value of the face up tile to pick.
     *
     * @return the value choiced by the user.
     */
    int askTileValueToPick();

    /**
     * Asks the player if he want to drop the picked face up tile.
     *
     * @return 0 if yes , otherwise return false.
     */
    int askDropTile();
}
