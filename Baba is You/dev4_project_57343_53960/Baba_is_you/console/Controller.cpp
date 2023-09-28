
#include "Controller.h"

Controller::Controller(int currentLevel) : currentLevel{currentLevel}, boardLoader{currentLevel}, game{&boardLoader}, view{}{

    game.Attach(&view);
}

Direction Controller::updateLevelState() {
    int key = _getwch();
    Direction dir {Direction::STOP};
    if (key == 0 || key == 224){
        key = _getwch();
        switch(key) {
        case 72: // Up arrow
            dir = Direction::UP;
            break;
        case 80: // Down arrow
            dir = Direction::DOWN;
            break;
        case 75: // Left arrow
            dir = Direction::LEFT;
            break;
        case 77: // Right arrow
            dir = Direction::RIGHT;
            break;
        default:
            dir = Direction::STOP;
            break;
        }
    }
    if(key ==  115){
        game.saveLevel();
    }
    if(key == 108){
        boardLoader = {-1};
        game = {&boardLoader};
        game.Attach(&this->view);
    }
    return dir;
}

void Controller::play(){
    Direction dir{Direction::STOP};
    view.displayBoard(&game);
    game.checkRule();
    while(currentLevel < 5){
        dir = updateLevelState();
        game.move(dir);
        game.updateIsPos();
        game.disapplyRule();
        game.checkRule();
        int key{};
        if (game.isRestart()){
            while (key!=114){
                key = _getwch();
            }
            if(key == 114){
                boardLoader = {currentLevel};
                game = {&boardLoader};
                game.Attach(&this->view);
            }
        }
        if (game.getBoardLoader()->getLevelNumber()> currentLevel){
            currentLevel+=1;
            if (currentLevel == 5){
                goto endGame;
            }
            boardLoader = {currentLevel};
            game = {&boardLoader};
            game.Attach(&this->view);
        }
    }
endGame:
    view.printMessage("félicitation vous avez gagnez, le jeu est fini !");
        exit(0);
}


