package g53960.thegeniussquare.model;

import java.util.Objects;

/**
 * Represents a component of a shape on the game board. Each shape component has
 * a position, type, color, and a flag indicating if it's the head of the shape.
 *
 * @author Samad
 */
public class ShapeComponent {

    private Position pos;
    private ShapeType type;
    private ShapeColor color;
    private Boolean isHead;

    /**
     * Constructs a ShapeComponent with the specified position, type, and color.
     *
     * @param pos the position of the shape component on the board
     * @param type the type of the shape component
     * @param color the color of the shape component
     */
    public ShapeComponent(Position pos, ShapeType type, ShapeColor color) {
        this.pos = pos;
        this.type = type;
        this.color = color;
    }

    /**
     * Constructs a ShapeComponent with the specified position, type, color, and
     * head flag.
     *
     * @param pos the position of the shape component on the board
     * @param type the type of the shape component
     * @param color the color of the shape component
     * @param isHead flag indicating if it's the head of the shape
     */
    public ShapeComponent(Position pos, ShapeType type, ShapeColor color, Boolean isHead) {
        this.pos = pos;
        this.type = type;
        this.color = color;
        this.isHead = isHead;
    }

    /**
     * Returns the color of the shape component.
     *
     * @return the color of the shape component
     */
    public ShapeColor getColor() {
        return color;
    }

    /**
     * Returns the row index of the shape component's position.
     *
     * @return the row index of the shape component's position
     */
    public int getRow() {
        return pos.getRow();
    }

    /**
     * Returns the column index of the shape component's position.
     *
     * @return the column index of the shape component's position
     */
    public int getCol() {
        return pos.getCol();
    }

    /**
     * Returns the type of the shape component.
     *
     * @return the type of the shape component
     */
    public ShapeType getType() {
        return type;
    }

    /**
     * Returns whether the shape component is the head of the shape.
     *
     * @return true if the shape component is the head, false otherwise
     */
    public Boolean isHead() {
        return isHead;
    }

    /**
     * Returns the position of the shape component.
     *
     * @return the position of the shape component
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Sets the position of the shape component.
     *
     * @param pos the new position of the shape component
     */
    public void setPos(Position pos) {
        this.pos.setPosition(pos.getRow(), pos.getCol());
    }

    /**
     * Generates the hash code for the shape component.
     *
     * @return the hash code of the shape component
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.pos);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.color);
        hash = 41 * hash + Objects.hashCode(this.isHead);
        return hash;
    }

    /**
     *
     * Checks if the shape component is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the shape component is equal to another object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShapeComponent other = (ShapeComponent) obj;
        if (!Objects.equals(this.pos, other.pos)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        if (!Objects.equals(this.isHead, other.isHead)) {
            return false;
        }
        return true;
    }

    /**
     * Sets the type of the shape.
     *
     * @param type The type of the shape.
     */
    public void setType(ShapeType type) {
        this.type = type;
    }

    /**
     * Sets the color of the shape.
     *
     * @param color The color of the shape.
     */
    public void setColor(ShapeColor color) {
        this.color = color;
    }

}
