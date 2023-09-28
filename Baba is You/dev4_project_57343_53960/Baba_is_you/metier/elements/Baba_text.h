#ifndef BABA_TEXT_H
#define BABA_TEXT_H
#include "../Element.h"

class Baba_Text : public Element {
public:
    Baba_Text(Position pos, Direction dir): Element(pos, dir, ElementType::BLOCK_TEXT, "baba_text", false, "baba ") {
    }

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
#endif // BABA_TEXT_H
