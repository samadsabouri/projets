#include "ViewGUI.h"

ViewGUI::ViewGUI(){}

void ViewGUI::displayBoard(Game *g){
    system("cls");
    int f =0;

    for(int k{}; k<g->getBoardLoader()->getBoardPtr()->getBoardRows(); k++){
        for(int j{}; j<g->getBoardLoader()->getBoardPtr()->getBoardCols(); j++){
            if(g->getBoardLoader()->getBoardPtr()->getSquares()[k][j]->getElements().empty()){
                std::cout<<"     " ;
            }else{
                std::cout<< g->getBoardLoader()->getBoardPtr()->getSquares()[k][j]->getElements().back().getSymbol();
            }
            f++;
            if(f == g->getBoardLoader()->getBoardPtr()->getBoardRows()){
                std::cout << std::endl;
                f = 0;
            }
        }
    }
    std::cout<<"\n"<<  std::endl;
}

void ViewGUI::printMessage(std::string message){
    std::cout<< message << std::endl;
}

void ViewGUI::Update(Subject * game){
    Game * g  = dynamic_cast< Game *>(game);
    if (g->getBoardLoader()->getBoardPtr()->getMovableElements().empty()){
        printMessage("There is no element 'you'");
        printMessage("Do you want to restart the level ?");
        printMessage("If yes, press 'R', otherwise press any key and the game will stop");
        printMessage("Then press enter");
        printMessage("The action won't happen until you press 'R' followed by 'Enter'.");
    } else {
        displayBoard(g);
    }

}


