
package g53960.thegeniussquare.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class PositionTest {
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Position instance = new Position(0, 0);
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCol() {
        System.out.println("getCol");
        Position instance = new Position(0, 1);
        int expResult = 1;
        int result = instance.getCol();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        int row = 0;
        int col = 0;
        Position instance = new Position(1, 1);
        instance.setPosition(row, col);
    }

@Test
public void testToString() {
    System.out.println("toString");
    Position instance = new Position(0, 0);
    String expResult = "(0,0)";
    String result = instance.toString();
    assertEquals(expResult, result);
}
    
}
