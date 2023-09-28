
package g53960.thegeniussquare.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class ShapeTest {

    @Test
    public void testGetComponentsNumber() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(0, 1), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(1, 1), ShapeType.MOBILE, ShapeColor.RED));
        Shape instance = new Shape(components);
        int expResult = 4;
        int result = instance.getComponentsNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetShapeComponents() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(0, 1), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.RED));
        components.add(new ShapeComponent(new Position(1, 1), ShapeType.MOBILE, ShapeColor.RED));
        Shape instance = new Shape(components);
        List<ShapeComponent> expResult = components;
        List<ShapeComponent> result = instance.getShapeComponents();
        assertEquals(expResult, result);
    }

    @Test
    public void testRotate() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.ORANGE, true));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        components.add(new ShapeComponent(new Position(2, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        Shape instance = new Shape(components);

        // Effectuer une rotation dans le sens des aiguilles d'une montre
        instance.rotate();

        // Vérifier que les composants ont été correctement rotés
        Position[] expResult = {new Position(0, 0), new Position(0, 1), new Position(0, 2)};
        Position[] result = new Position[3];
        for (int i = 0; i < 3; i++) {
            result[i] = instance.getShapeComponents().get(i).getPos();
        }
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testRotateTwice() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.ORANGE, true));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        components.add(new ShapeComponent(new Position(2, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        Shape instance = new Shape(components);
        instance.rotate();
        instance.rotate();
        Position[] expResult = {new Position(-2, 0), new Position(-1, 0), new Position(0, 0) };
        Position[] result = new Position[3];
        for (int i = 0; i < 3; i++) {
            result[i] = instance.getShapeComponents().get(i).getPos();
        }
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testRotateThreeTimes() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.ORANGE, true));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        components.add(new ShapeComponent(new Position(2, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        Shape instance = new Shape(components);
        instance.rotate();
        instance.rotate();
        instance.rotate();
        Position[] expResult = {new Position(0, -2), new Position(0, -1),new Position(0, 0) };
        Position[] result = new Position[3];
        for (int i = 0; i < 3; i++) {
            result[i] = instance.getShapeComponents().get(i).getPos();
        }
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testRotateFourTimes() {
        // Créer une liste de ShapeComponents
        List<ShapeComponent> components = new ArrayList<>();
        components.add(new ShapeComponent(new Position(0, 0), ShapeType.MOBILE, ShapeColor.ORANGE, true));
        components.add(new ShapeComponent(new Position(1, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        components.add(new ShapeComponent(new Position(2, 0), ShapeType.MOBILE, ShapeColor.ORANGE, false));
        Shape instance = new Shape(components);
        instance.rotate();
        instance.rotate();
        instance.rotate();
        instance.rotate();
        Position[] expResult = {new Position(0, 0), new Position(1, 0), new Position(2, 0)};
        Position[] result = new Position[3];
        for (int i = 0; i < 3; i++) {
            result[i] = instance.getShapeComponents().get(i).getPos();
        }
        assertArrayEquals(expResult, result);
    }
}
