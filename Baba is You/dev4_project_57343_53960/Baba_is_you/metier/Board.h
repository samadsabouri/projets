#ifndef BOARD_H
#define BOARD_H
#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <string>
#include <memory>
#include "Square.h"
#include <vector>

/**
 * @brief The class Board is a class representing a game board.
 */
class Board{

private :

     int boardRows;
     int boardCols;
     std::vector<std::vector<Square>> squares;
     std::vector<Element> movableElements;

public:
     /**
      * @brief Board  default constructor.
      */
     Board();


     /**
      * @brief Board class constructor with parameters.
      * @param boardCols The number of columns on the board.
      * @param boardRows The number of rows on the board.
      * @param squares A vector of vectors of Squares representing the game board.
      * @param movableElements A vector of Elements representing the movable elements on the board.
      */
     Board(int& boardCols, int& boardRows,
     std::vector<std::vector<Square>> &squares,std::vector<Element> movableElements);

     /**
     * @brief Getter method to get the number of rows on the board.
     * @return The number of rows on the board.
     */
    inline int getBoardRows()const{
     return this->boardRows;
    }

    /**
     * @brief Getter method to get the number of columns on the board.
     * @return The number of columns on the board.
     */
    inline int getBoardCols()const{
     return this->boardCols;
    }

    /**
     * @brief Getter method to get the vector of vector of Squares representing the game board.
     * @return A vector of vectors of Squares representing the game board.
     */
    std::vector<std::vector<Square*>> getSquares();


     /**
      * @brief Checks if elements can be moved.
      * @return True if elements can be moved, false otherwise.
      */
     std::vector<Element> getMovableElements();

     /**
      * @brief To set the vector of movable elements.
      * @param newMovableElement A vector of Elements representing the new movable elements.
      */
     void updateMovableElements(std::vector<Element> newMovableElement);

     /**
      * @brief To get the Square at a specific position on the game board.
      * @param pos The position to get the Square from.
      * @return A pointer to the Square at the given position.
      */
     Square* getSquareAtPosition(Position pos);

     /**
      * @brief Moves an element to a new position on the game board.
      * @param elem The element to be moved.
      * @param youPos The position to move the element to.
      */
     void moveElement(Element* elem,Position youPos);

};
#endif // BOARD_H

