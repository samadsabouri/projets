#ifndef BEST_TEXT_H
#define BEST_TEXT_H
#include "../Element.h"

class Best_Text : public Element {
public:
    Best_Text(Position pos, Direction dir): Element(pos, dir, ElementType::BLOCK_TEXT, "best_text", false, "best ") {}

    inline virtual std::string getName() override{
        return Element::getName();
    };
    inline virtual Direction getDirection() override{
        return Element::getDirection();
    };
    inline virtual ElementType getElementType() override{
        return Element::getElementType();
    };
    inline virtual Position getPosition()override{
        return Element::getPosition();
    };
    inline virtual void setPosition(Position position) override{
        Element::setPosition(position);
    };
    inline virtual void setDirection(Direction dir) override{
        Element::setDirection(dir);
    };
    inline virtual void setMoveble(bool movable) override{
        Element::setMoveble(movable);
    };
    inline virtual bool isMoveble() override{
        return Element::isMoveble();
    };
    inline virtual std::string getSymbol() override{
        return Element::getSymbol();
    };



};
#endif // BEST_TEXT_H
