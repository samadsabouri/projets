#include "Square.h"
Square::Square(Position pos):pos{pos}{
    elements = std::vector<Element>();
}

Square::Square( std::vector<Element>& elements, Position pos):pos{pos}{
    this->elements = elements;
}


void Square::addElement(Element* element) {
    if (this->pos == element->getPosition()) {
        elements.push_back((*element));
    }
    else {
        throw std::invalid_argument("On peut pas ajouter un element qui n'a pas la meme position du square");
    }
}

void Square::removeElement(Element* elem) {
    auto iter = elements.begin();
    while (iter != elements.end()) {
        if (*iter == *elem) {
            iter = elements.erase(iter);
        } else {
            ++iter;
        }
    }
}

std::vector<Element> Square::getElements(){
    return this->elements;
}

std::vector<Element*> Square::getElementsPtr(){
    std::vector<Element*> elems;
    for (unsigned i = 0;i<elements.size();i++){
        elems.push_back(&elements.at(i));
    }
    return elems;
}

std::vector<Element> Square::getElementsAt(Position pos){
    std::vector<Element> elemPos{};
    for (int i = 0; this->elements.size();i++){
        if (elements[i].getPosition() == pos){
            elemPos.push_back(elements[i]);
        }
    }
    return elemPos;
}
