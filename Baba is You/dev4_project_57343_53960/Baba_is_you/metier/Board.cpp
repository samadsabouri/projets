#include "Board.h"

Board::Board(){}
Board::Board(int& boardRows, int& boardCols ,
             std::vector<std::vector<Square>> &squares,std::vector<Element> movableElements):
    boardRows{boardRows},boardCols{boardCols}, squares{squares},movableElements{movableElements}  {
}

Square* Board::getSquareAtPosition(Position pos) {
    for (auto& row : this->squares) {
        for (auto& square : row) {
            if (square.getPosition().getRow() == pos.getRow() && square.getPosition().getColumn() == pos.getColumn()) {
                return &square;
            }
        }
    }
    return nullptr;
}


std::vector<Element> Board::getMovableElements(){
    return this->movableElements;
}

std::vector<std::vector<Square*>> Board::getSquares(){
    std::vector<std::vector<Square*>> squaresPtr;
    for(auto& row : squares){
        std::vector<Square*> rowPtr;
        for(auto& square : row){
            rowPtr.push_back(&square);
        }
        squaresPtr.push_back(rowPtr);
    }
    return squaresPtr;
}

void Board::moveElement(Element* elem, Position youPos){
    elem->setPosition(youPos);
}

void Board::updateMovableElements(std::vector<Element> newMovableElement){
    movableElements.clear();
    this->movableElements = newMovableElement;
}


