#ifndef ELEMENT_H
#define ELEMENT_H
#include "ElementType.h"
#include "Position.h"
#include "Direction.h"
#include <string>
#include <vector>

/**
 * @brief Class representing an element of the game.
 */
class Element{


private:
    Position pos;
    Direction dir;
    ElementType type;
    std::string name;
    bool isMovable;
    bool isCrossable;
    std::string symbol;

    bool you;
    bool kill;
    bool push;
    bool sink;
    bool win;
    bool flag;
    bool stop;


public:
    /**
     * @brief Default constructor for the Element class.
     */
    Element ();
    /**
     * @brief Constructor for the Element class.
     * @param pos The position of the element.
     * @param dir The direction of the element.
     * @param type The type of the element.
     * @param name The name of the element.
     * @param isMovable Whether the element is movable or not
     * @param symbol The symbol representing the element.
     */
    Element(Position pos, Direction dir, ElementType type, std::string name, bool isMovable, std::string symbol);

    /**
     * @brief Gets the "you" tag of the element.
     * @return Whether the element is tagged as "you".
     */
    bool getYou() {
        return you;
    }

    /**
     * @brief Sets the "you" tag of the element.
     * @param value The value to set the tag to.
     */
    void setYou(bool value) {
        you = value;
    }

    /**
     * @brief Gets the "kill" tag of the element.
     * @return Whether the element is tagged as "kill".
     */
    bool getKill() {
        return kill;
    }

    /**
     * @brief Sets the "kill" tag of the element.
     * @param value The value to set the tag to.
     */
    void setKill(bool value) {
        kill = value;
    }
    /**
     * @brief Gets the "push" tag of the element.
     * @return Whether the element is tagged as "push".
     */
    bool getPush() {
        return push;
    }
    /**
     * @brief Sets the "push" tag of the element.
     * @param value The value to set the tag to.
     */
    void setPush(bool value) {
        push = value;
    }

    /**
     * @brief Gets the "sink" tag of the element.
     * @return Whether the element is tagged as "sink".
     */
    bool getSink() {
        return sink;
    }

    /**
     * @brief Sets the "sink" tag of the element.
     * @param value The value to set the tag to.
     */
    void setSink(bool value) {
        sink = value;
    }

    /**
     * @brief Gets the "win" tag of the element.
     * @return Whether the element is tagged as "win".
     */
    bool getWin() {
        return win;
    }
    /**
     * @brief Sets the "win" tag of the element.
     * @param value The value to set the tag to.

     */
    void setWin(bool value) {
        win = value;
    }

    /**
     * @brief Gets the "flag" tag of the element.
     * @return Whether the element is tagged as "flag".
     */
    bool getFlag() {
        return flag;
    }
    /**
     * @brief Sets the flag value.
     * @param value The new value for the flag.
     */
    void setFlag(bool value) {
        flag = value;
    }

    /**
     * @brief Gets the value of the stop variable.
     * @return The current value of stop.
     */
    bool getStop(){
        return stop;
    }

    /**
     * @brief Sets the value of the stop variable.
     * @param value The new value for stop
     */
    void setStop(bool value){
        stop = value;
    }
    /**
     * @brief To get the ElementType.
     * @return the type of the element.
     */
    virtual ElementType getElementType();

    /**
     * @brief To get the name of the element.
     * @return the name of the element.
     */
    virtual std::string getName();

    /**
     * @brief To get the position of the element.
     * @return the position of the element.
     */
    virtual Position getPosition();

    /**
     * @brief To set the position of the element.
     * @param position the position to set.
     */
    virtual void setPosition (Position position);

    /**
     * @brief To set the direction of the element.
     * @param dir the direction needed to set.
     */
    virtual void setDirection (Direction dir);

    /**
     * @brief To get the direction of the element.
     * @return the direction of the element.
     */
    virtual Direction getDirection();

    /**
     * @brief To set an element on movable or not.
     * @param movable the true or false parameter for the element.
     */
    virtual void setMoveble(bool movable);

    /**
     * @brief Determines if the object is moveable.
     * @return true if the object is moveable, false otherwise.
     */
    virtual bool isMoveble();

    /**
     * @brief Sets whether or not the object can be crossed.
     * @param crossable true if the object is crossable, false otherwise.
     */
    virtual void setCrossable(bool crossable);

    /**
     * @brief  Determines if the object can be crossed.

     * @return true if the object is crossable, false otherwise
     */
    virtual bool crossable();

    /**
     * @brief Returns the symbol of the element.
     * @return The symbol of the element as a string.
     */
    virtual std::string getSymbol();

    /**
     * @brief operator == Checks if this element is equal to another element.
     * @param other The element to compare to.
     * @return True if the elements are equal, false otherwise.
     */
    bool operator==(const Element& other) const {
       if(this->type == other.type && this->pos == other.pos && this->name ==other.name){
           return true;
       }else{
           return false;
       }
    };

};

#endif // ELEMENT_H

