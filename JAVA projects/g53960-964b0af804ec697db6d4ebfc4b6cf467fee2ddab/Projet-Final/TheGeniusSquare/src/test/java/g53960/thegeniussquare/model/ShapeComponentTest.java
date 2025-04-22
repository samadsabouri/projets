package g53960.thegeniussquare.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samad
 */
public class ShapeComponentTest {

    @Test
    public void testGetColorBLACK() {
        System.out.println("getColor Black");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.BLOCKER, ShapeColor.BLACK);
        ShapeColor expResult = ShapeColor.BLACK;
        ShapeColor result = instance.getColor();
        assertEquals(expResult, result);

    }
    
        @Test
    public void testGetColorBLUE() {
        System.out.println("getColor Blue");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.BLUE);
        ShapeColor expResult = ShapeColor.BLUE;
        ShapeColor result = instance.getColor();
        assertEquals(expResult, result);

    }
    
          @Test
    public void testGetColorWHITE() {
        System.out.println("getColor White");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.NEUTRAL, ShapeColor.WHITE);
        ShapeColor expResult = ShapeColor.WHITE;
        ShapeColor result = instance.getColor();
        assertEquals(expResult, result);
    }
    

    @Test
    public void testGetRow() {
        System.out.println("getRow");
        ShapeComponent instance = new ShapeComponent(new Position(1, 0), ShapeType.BLOCKER, ShapeColor.BLACK);
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCol() {
        System.out.println("getCol");
        ShapeComponent instance = new ShapeComponent(new Position(3, 2), ShapeType.BLOCKER, ShapeColor.BLACK);;
        int expResult = 2;
        int result = instance.getCol();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTypeBlocker() {
        System.out.println("getType Blocker");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.BLOCKER, ShapeColor.BLACK);
        ShapeType expResult = ShapeType.BLOCKER;
        ShapeType result = instance.getType();
        assertEquals(expResult, result);
    }
        @Test
    public void testGetTypeMobile() {
        System.out.println("getType Mobile");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.ORANGE);
        ShapeType expResult = ShapeType.MOBILE;
        ShapeType result = instance.getType();
        assertEquals(expResult, result);
    }
    
           @Test
    public void testGetTypeNeutral() {
        System.out.println("getType Neutral");
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.NEUTRAL, ShapeColor.WHITE);
        ShapeType expResult = ShapeType.NEUTRAL;
        ShapeType result = instance.getType();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsHead() {
        System.out.println("isHead");
        ShapeComponent instance1 = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED, true);
        ShapeComponent instance2 = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED, false);
        Boolean expResult1 = true;
        Boolean expResult2 = false;
        Boolean result1 = instance1.isHead();
        Boolean result2 = instance2.isHead();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }
   @Test
    public void testIsNotHead() {
        System.out.println("isNotHead");
        ShapeComponent instance1 = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED, false);
        ShapeComponent instance2 = new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED, false);
        Boolean expResult1 = false;
        Boolean expResult2 = false;
        Boolean result1 = instance1.isHead();
        Boolean result2 = instance2.isHead();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testGetPos() {
        System.out.println("getPos");
        Position pos = new Position(2, 3);
        ShapeComponent instance = new ShapeComponent(pos, ShapeType.BLOCKER, ShapeColor.BLACK);
        Position expResult = pos;
        Position result = instance.getPos();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPos() {
        System.out.println("setPos");
        Position pos = new Position(2, 3);
        ShapeComponent instance = new ShapeComponent(new Position(0, 0), ShapeType.BLOCKER, ShapeColor.BLACK);
        instance.setPos(pos);
        Position expResult = pos;
        Position result = instance.getPos();
        assertEquals(expResult, result);
    }
  @Test
    public void testSetPos2() {
        System.out.println("setPos");
        Position pos = new Position(5, 2);
        ShapeComponent instance = new ShapeComponent(new Position(1, 3), ShapeType.BLOCKER, ShapeColor.BLACK);
        instance.setPos(pos);
        Position expResult = pos;
        Position result = instance.getPos();
        assertEquals(expResult, result);
    }

}
