#include "Element.h"

Element::Element(){}
Element::Element(Position pos, Direction dir, ElementType type, std::string name, bool isMovable, std::string symbol):
    pos{pos},dir{dir},type{type},name{name},isMovable{isMovable},symbol{symbol}{}

Direction Element:: getDirection() {
    return this->dir;
}

ElementType Element::getElementType(){
   return this->type;
}

std::string Element::getName(){
    return this->name;
}

Position Element::getPosition(){
    return this->pos.getPosition();
}

void Element::setPosition(Position position){
    this->pos = position;
}

void Element::setMoveble(bool movable){
    this->isMovable = movable;
}

void Element::setDirection(Direction dir){
    this->dir = dir;
}

bool Element::isMoveble(){
    return this->isMovable;
}

std::string Element::getSymbol(){
    return symbol;
}

bool Element::crossable(){
    return this->isCrossable;
}
void Element::setCrossable(bool crossable){
    this->isCrossable = crossable;
}



