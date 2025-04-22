package g53960.thegeniussquare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * * Factory class for creating shapes based on the specified color.
 *
 * @author Samad
 */
public class ShapeFactory {

    /**
     *
     * Builds a shape of the specified color.
     *
     * @param color the color of the shape to build
     * @return the constructed shape
     */
    public static Shape build(ShapeColor color) {
        Shape shape = null;
        switch (color) {
            case YELLOW:
                ShapeComponent yelloComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.YELLOW, true);
                ShapeComponent yelloComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.YELLOW, false);
                ShapeComponent yelloComponent3 = new ShapeComponent(new Position(4, 4), ShapeType.MOBILE, ShapeColor.YELLOW, false);
                ShapeComponent yelloComponent4 = new ShapeComponent(new Position(5, 3), ShapeType.MOBILE, ShapeColor.YELLOW, false);
                List<ShapeComponent> yellowComponents = new ArrayList<>();
                yellowComponents.add(yelloComponent1);
                yellowComponents.add(yelloComponent2);
                yellowComponents.add(yelloComponent3);
                yellowComponents.add(yelloComponent4);
                shape = new Shape(yellowComponents);
                break;
            case ORANGE:
                ShapeComponent orangeComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.ORANGE, true);
                ShapeComponent orangeComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.ORANGE, false);
                ShapeComponent orangeComponent3 = new ShapeComponent(new Position(5, 3), ShapeType.MOBILE, ShapeColor.ORANGE, false);
                List<ShapeComponent> orangeComponents = new ArrayList<>();
                orangeComponents.add(orangeComponent1);
                orangeComponents.add(orangeComponent2);
                orangeComponents.add(orangeComponent3);
                shape = new Shape(orangeComponents);
                break;
            case BLUE:
                ShapeComponent blueComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.BLUE, true);
                List<ShapeComponent> blueComponents = new ArrayList<>();
                blueComponents.add(blueComponent1);
                shape = new Shape(blueComponents);
                break;
            case GREY:
                ShapeComponent greyComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.GREY, true);
                ShapeComponent greyComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.GREY, false);
                ShapeComponent greyComponent3 = new ShapeComponent(new Position(5, 3), ShapeType.MOBILE, ShapeColor.GREY, false);
                ShapeComponent greyComponent4 = new ShapeComponent(new Position(6, 3), ShapeType.MOBILE, ShapeColor.GREY, false);
                List<ShapeComponent> greyComponents = new ArrayList<>();
                greyComponents.add(greyComponent1);
                greyComponents.add(greyComponent2);
                greyComponents.add(greyComponent3);
                greyComponents.add(greyComponent4);
                shape = new Shape(greyComponents);
                break;
            case BROWN:
                ShapeComponent brownComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.BROWN, true);
                ShapeComponent brownComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.BROWN, false);
                List<ShapeComponent> brownComponents = new ArrayList<>();
                brownComponents.add(brownComponent1);
                brownComponents.add(brownComponent2);
                shape = new Shape(brownComponents);
                break;
            case RED:
                ShapeComponent redComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.RED, true);
                ShapeComponent redComponent2 = new ShapeComponent(new Position(3, 4), ShapeType.MOBILE, ShapeColor.RED, false);
                ShapeComponent redComponent3 = new ShapeComponent(new Position(4, 4), ShapeType.MOBILE, ShapeColor.RED, false);
                ShapeComponent redComponent4 = new ShapeComponent(new Position(4, 5), ShapeType.MOBILE, ShapeColor.RED, false);
                List<ShapeComponent> redComponents = new ArrayList<>();
                redComponents.add(redComponent1);
                redComponents.add(redComponent2);
                redComponents.add(redComponent3);
                redComponents.add(redComponent4);
                shape = new Shape(redComponents);
                break;
            case LIGHTBLUE:
                ShapeComponent lightBlueComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.LIGHTBLUE, true);
                ShapeComponent lightBlueComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.LIGHTBLUE, false);
                ShapeComponent lightBlueComponent3 = new ShapeComponent(new Position(4, 4), ShapeType.MOBILE, ShapeColor.LIGHTBLUE, false);
                ShapeComponent lightBlueComponent4 = new ShapeComponent(new Position(4, 5), ShapeType.MOBILE, ShapeColor.LIGHTBLUE, false);
                List<ShapeComponent> lightBlueComponents = new ArrayList<>();
                lightBlueComponents.add(lightBlueComponent1);
                lightBlueComponents.add(lightBlueComponent2);
                lightBlueComponents.add(lightBlueComponent3);
                lightBlueComponents.add(lightBlueComponent4);
                shape = new Shape(lightBlueComponents);
                break;
            case PURPLE:
                ShapeComponent purpleComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.PURPLE, true);
                ShapeComponent purpleComponent2 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.PURPLE, false);
                ShapeComponent purpleComponent3 = new ShapeComponent(new Position(4, 4), ShapeType.MOBILE, ShapeColor.PURPLE, false);
                List<ShapeComponent> purpleComponents = new ArrayList<>();
                purpleComponents.add(purpleComponent1);
                purpleComponents.add(purpleComponent2);
                purpleComponents.add(purpleComponent3);
                shape = new Shape(purpleComponents);
                break;
            case GREEN:
                ShapeComponent greenComponent1 = new ShapeComponent(new Position(3, 3), ShapeType.MOBILE, ShapeColor.GREEN, true);
                ShapeComponent greenComponent2 = new ShapeComponent(new Position(3, 4), ShapeType.MOBILE, ShapeColor.GREEN, false);
                ShapeComponent greenComponent3 = new ShapeComponent(new Position(4, 3), ShapeType.MOBILE, ShapeColor.GREEN, false);
                ShapeComponent greenComponent4 = new ShapeComponent(new Position(4, 4), ShapeType.MOBILE, ShapeColor.GREEN, false);
                List<ShapeComponent> greenComponents = new ArrayList<>();
                greenComponents.add(greenComponent1);
                greenComponents.add(greenComponent2);
                greenComponents.add(greenComponent3);
                greenComponents.add(greenComponent4);
                shape = new Shape(greenComponents);
                break;

        }
        return shape;

    }
}
