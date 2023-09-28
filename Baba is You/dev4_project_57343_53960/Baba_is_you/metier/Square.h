#ifndef SQUARE_H
#define SQUARE_H
#include <vector>
#include <algorithm>
#include "Element.h"

/**
 * @brief Class representing a square.
 */
class Square {
private:
    std::vector<Element> elements;
    Position pos;
public:

    /**
     * @brief Square class default constructor.
     *
     */
    Square(){}

    /**
     * @brief Square class constructor.
     * @param pos the position of the square.
     */
    Square(Position pos);

    /**
     * @brief Square class constructor.
     * @param elements the vector of elements on the square.
     * @param pos the position of the square.
     */
    Square( std::vector<Element>& elements, Position pos);

    /**
     * @brief To add an element on the Square.
     * @param element the element.
     */
    void addElement(Element *element);

    /**
     * @brief To remove an element on the Square.
     * @param elem the element to remove.
     */
    void removeElement(Element* elem);

    /**
      * @brief Gets the vector of elements on the square.
      * @return a vector of elements.
      */
     std::vector<Element> getElements();

     /**
      * @brief Gets the vector of pointers to the elements on the square
      * @return a vector of pointers to elements
      */
     std::vector<Element*> getElementsPtr();

     /**
     * @brief Gets the elements at a given position on the square.
     * @param pos the position to check for elements.
     * @return a vector of elements.
     */
    std::vector<Element> getElementsAt(Position pos);


    /**
      * @brief Gets the position of the square.
      * @return the position of the square.
      */
     inline Position getPosition()const{
         return this->pos;
     }

     /**
      * @brief operator < less-than comparison operator for squares based on their positions.
      * @param other the other square to compare to.
      * @return true if this square's position is less than the other's, false otherwise.
      */
     bool operator<(const Square& other) const {
          return this->getPosition() < other.getPosition();
     }

};
#endif // SQUARE_H
