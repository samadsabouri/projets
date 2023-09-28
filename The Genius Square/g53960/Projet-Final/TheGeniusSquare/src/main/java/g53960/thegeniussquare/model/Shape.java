package g53960.thegeniussquare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shape composed of multiple shape components.
 *
 * @author Samad
 */
public class Shape {

    private final List<ShapeComponent> shape;
    private final int componentsNumber;

    /**
     * Constructs a new instance of the Shape class with the specified shape
     * components.
     *
     * @param givenShape the list of shape components that make up the shape
     */
    public Shape(List<ShapeComponent> givenShape) {
        this.shape = new ArrayList<>();
        addShape(givenShape);
        componentsNumber = shape.size();

    }

    /**
     * Gets the number of components in the shape.
     *
     * @return the number of components
     */
    public int getComponentsNumber() {
        return componentsNumber;
    }

    /**
     * Adds the given list of shape components to the shape.
     *
     * @param shape the list of shape components to add
     */
    private void addShape(List<ShapeComponent> shape) {

        for (ShapeComponent shapeComponent : shape) {
            this.shape.add(shapeComponent);
        }

    }

    /**
     * Gets the shape components that make up the shape.
     *
     * @return the list of shape components
     */
    public List<ShapeComponent> getShapeComponents() {
        return shape;
    }

    /**
     * Rotates the shape around its head component.
     */
    public void rotate() {
        ShapeComponent head = shape.get(0);
        if (!head.isHead()) {
            for (int i = 1; i < shape.size(); i++) {
                if (shape.get(i).isHead()) {
                    head = shape.get(i);
                }
            }
        }
        int headRow = head.getPos().getRow();
        int headCol = head.getPos().getCol();

        for (ShapeComponent component : shape) {
            int newRow = headRow + headCol - component.getPos().getCol();
            int newCol = headCol - headRow + component.getPos().getRow();
            Position newPos = new Position(newRow, newCol);
            component.setPos(newPos);
        }

        sortShapeComponents();

    }

    /**
     * * Sorts the shape components in ascending order based on their
     * positions. The sorting is first done by row, and then by column within
     * the same row.
     */
    public void sortShapeComponents() {
        shape.sort((component1, component2) -> {
            int rowDiff = component1.getPos().getRow() - component2.getPos().getRow();
            if (rowDiff != 0) {
                return rowDiff;
            } else {
                return component1.getPos().getCol() - component2.getPos().getCol();
            }
        });
    }
}
