#include "Position.h"

Position::Position(){}

Position::Position(int row, int column):row{row}, column{column}{}

 Position Position::getPosition()
{
    return *this;
}



void Position::nextPosition(Direction dir){

    switch(dir) {
        case  Direction::UP:
            row--;
            break;
        case  Direction::DOWN:
            row++;
            break;
        case  Direction::LEFT:
            column--;
            break;
        case  Direction::RIGHT:
            column++;
            break;
        case Direction::STOP :
            break;
    }

}

void Position::setPosition(int &row, int &col){
    this->row = row;
    this->column = col;
}
std::ostream& operator<<(std::ostream& os, const Position& pos) {
    os << "(" << pos.getRow() << ", " << pos.getColumn() << ")";
    return os;
}

