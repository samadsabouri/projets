
package g53960.thegeniussquare.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class DiceTest {
 

@Test
public void testGetRandom() {
    System.out.println("getRandom");
    Dice instance = new Dice();
    Position[] result = instance.getRandom();
    assertEquals(7, result.length); // Vérifie que le tableau a une taille de 7
}
    


}
