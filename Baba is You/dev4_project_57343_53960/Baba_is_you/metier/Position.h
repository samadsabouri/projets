#ifndef POSITION_H
#define POSITION_H
#include <iostream>
#include <string>
#include "Direction.h"
/**
 * @brief Class representing a position
 */
class Position {
private:
    int row;
    int column;
public:
    Position();
    /**
     * @brief Position class constructor.
     * @param column The column of the position.
     * @param row The row of the position.
     */
    Position(int row, int column);

    /**
     * @brief Get the current position.
     * @return The current position.
     */
    Position getPosition();

    /**
     * @brief Set the next position.
     */
    void nextPosition(Direction dir);

    /**
     * @brief Get the row of the position.
     * @return The row of the position.
     */
    inline int getRow() const{
        return this->row;
    }

    /**
     * @brief Get the column of the position.
     * @return The column of the position.
     */
    inline int getColumn() const{
        return this->column;
    }
    /**
     * @brief Set the position of the square.
     * @param row The row position of the square.
     * @param col The column position of the square.
     */
    void setPosition(int &row, int &col);


    /**
     * @brief Operator == to compare two positions.
     * @param other The other position to compare with.
     * @return true if the two positions are equal, false otherwise.
     */
    bool operator==(const Position& other) const {
        return (this->row == other.row) && (this->column == other.column);
    }

    /**
     * @brief operator != overload of the "not equal" operator for Position objects.
     * @param other The other Position object to compare to.
     * @return  true if the two Position objects are different, false otherwise.
     */
    bool operator!=(const Position& other) const {
        return (this->row != other.row) || (this->column != other.column);
    }

    /**
     * @brief operator << overload of the output stream operator for Position objects.
     * @param  os The output stream object.
     * @param pos The Position object to output.
     * @return The output stream object.
     */
    friend std::ostream& operator<<(std::ostream& os, const Position& pos);
    bool operator<(const Position& other) const {
        if (row < other.row) {
            return true;
        } else if (row > other.row) {
            return false;
        } else {
            return column < other.column;
        }
    }

    /**
     * @brief operator + overload of the addition operator for a Position object and a Direction object.
     * @param dir The Direction object to add.
     * @return A new Position object with the same row and column
     *  as this object but shifted in the direction of the input Direction object.

     */
    Position operator+(const Direction& dir) const {
        switch (dir) {
        case Direction::UP:
            return Position(row - 1, column);
        case  Direction::DOWN:
            return Position(row + 1, column);
        case  Direction::LEFT:
            return Position(row, column - 1);
        case  Direction::RIGHT:
            return Position(row, column + 1);
        default:
            return Position(row, column);
        }
    }

    /**
     * @brief operator - overload of the subtraction operator for a Position object and a Direction object.
     * @param dir The Direction object to subtract.
     * @return A new Position object with the same row and column as this object but shifted in the opposite direction
     *  of the input Direction object.

     */
    Position operator-(const Direction& dir) const {
        switch (dir) {
        case  Direction::UP:
            return Position(row + 1, column);
        case  Direction::DOWN:
            return Position(row - 1, column);
        case  Direction::LEFT:
            return Position(row, column + 1);
        case  Direction::RIGHT:
            return Position(row, column - 1);
        default:
            return Position(row, column);
        }
    }



};




#endif // POSITION_H
