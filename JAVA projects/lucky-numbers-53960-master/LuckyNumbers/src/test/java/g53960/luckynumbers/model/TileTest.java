
package g53960.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class TileTest {

    public TileTest() {
    }

    @Test
    public void flipFaceUp_when_not_is_faceUp() {
        Tile t = new Tile(5);
        t.flipFaceUp();
        assertTrue(t.isFaceUp());

    }

    @Test
    public void flipFaceUp_when_is_faceUp() {
        Tile t = new Tile(6);
        t.flipFaceUp(); // on met la faceup à true
        t.flipFaceUp(); // on essaye de refaire la meme chose
        assertTrue(t.isFaceUp());
    }
}
