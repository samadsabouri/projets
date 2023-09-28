package g53960.luckynumbers.model;

import g53960.luckynumbers.view.MyView;
import g53960.luckynumbers.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Represents all the tiles available in the center of the table, whether they
 * are face visible or not.
 *
 * @author Samad (G53960)
 */
public class Deck {

    private List<Tile> tilesFaceDown;
    private List<Tile> tilesFaceUp;

    /**
     * Simple constructor of Deck.
     *
     * @param playerCount the given player count.
     */
    public Deck(int playerCount) {
        this.tilesFaceDown = new ArrayList<>(); // on crée liste qui contient les tiles cachés
        this.tilesFaceUp = new ArrayList<>();// on crée une liste vide de tiles visibles
        int playerIndex;
        for (int i = 0; i < 20; i++) {
            playerIndex = 0;
            while (playerIndex < playerCount) {
                this.tilesFaceDown.add(new Tile(i + 1));
                playerIndex++;

            }
        }

        Collections.shuffle(this.tilesFaceDown); 
        

    }

    /**
     * Simple getter of down face tiles size.
     *
     * @return the number of face down tiles.
     */
    public int faceDownCount() {
        return this.tilesFaceDown.size();
    }

    /**
     * Simple getter of up face tiles size.
     *
     * @return the number of face up tiles.
     */
    public int faceUpCount() {
        return this.tilesFaceUp.size();
    }

    /**
     * Allows to pick one face down tile.
     *
     * @return a random face down tile if the list is not empty, otherwise
     * return null.
     */
    public Tile pickFaceDown() {
        if (!this.tilesFaceDown.isEmpty()) {
            return this.tilesFaceDown.remove(this.tilesFaceDown.size() - 1);
        }
        return null;
    }

    /**
     * Simple getter of face up tiles 
     *
     * @return a list of face up tiles
     */
    public List<Tile> getAllFaceUp() {
        return this.tilesFaceUp;
    }

    /**
     * Checks if the given tuile exist in the tiles face up list.
     *
     * @param tile the given tile.
     * @return true if the given tile exist , otherwise return false.
     */
    public boolean hasFaceUp(Tile tile) {
        if (!tilesFaceUp.isEmpty()) {
            for (Tile tileToCheck : this.tilesFaceUp) {
                if (tileToCheck.getValue() == tile.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove from the list of face up tile the tile given in parameter.
     *
     * @param tile the given tile.
     * @return the tile given tile if it exist in the list.
     */
    public Tile pickFaceUp(Tile tile) {

        for (Tile tileToPick : this.tilesFaceUp) {
            if (tileToPick.getValue() == tile.getValue()) {
                this.tilesFaceUp.remove(tileToPick);
                return tileToPick;
            }
        }
        return null;
    }

    /**
     * Simple getter of tiles face down list. it is used for testing
     *
     * @return return a list of face down tiles.
     */
    protected List<Tile> getTilesFaceDown() {
        return this.tilesFaceDown;
    }

    /**
     * Allows to clear the face down tiles list , used only for test.
     */
    protected void clearTilesFaceDown() {
        this.tilesFaceDown.clear();
    }

    /**
     * Allows to make a tile visible and move it to the face up list and remove
     * it from the face down tiles list.
     *
     * @param tile the given tile.
     */
    public void putBack(Tile tile) {
        for (Tile tileToReplace : this.tilesFaceDown) {
            if (tileToReplace.getValue() == tile.getValue()) {
                tileToReplace.flipFaceUp();
                this.tilesFaceUp.add(tile);
                break;
            }
        }
    }

}
