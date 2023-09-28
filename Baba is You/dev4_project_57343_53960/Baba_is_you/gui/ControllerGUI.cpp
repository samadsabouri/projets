#include "ControllerGUI.h"

ControllerGUI::ControllerGUI(int currentLevel) : currentLevel{currentLevel}, boardLoader{currentLevel}, game{&boardLoader}, view{}{
    view = new MainWindow(game.getBoardLoader()->getBoardPtr()->getBoardRows(), game.getBoardLoader()->getBoardPtr()->getBoardCols(), game.getBoardLoader()->getBoard().getSquares() );
    game.Attach(view);
    this->connect(this->view, &MainWindow::sendSignalKey, this, &ControllerGUI::updateLevel);
}

void ControllerGUI::resetAttribute(){
    this->rPressed = false;
    this->sPressed = false;
    this->dir = Direction::STOP;
}

void ControllerGUI::play(){
    view->show();
    game.checkRule();
    while(currentLevel < 5){
        resetAttribute();
        QEventLoop loop;
        connect(view, &MainWindow::sendSignalKey, [&loop](int key) {
            if (key == Qt::Key_Up || key == Qt::Key_Down ||
                key == Qt::Key_Left || key == Qt::Key_Right ||
                key == Qt::Key_R || key == Qt::Key_S || key == Qt::Key_L) {
                loop.quit();
            }
        });
        loop.exec();
        game.move(this->dir);
        game.updateIsPos();
        game.disapplyRule();
        game.checkRule();
        if (game.isRestart()){
            if(this->rPressed){
                boardLoader = {currentLevel};
                game = {&boardLoader};
                game.Attach(this->view);
                view->displayBoard(&game);
            }
        }
        if (game.getBoardLoader()->getLevelNumber()> currentLevel){
            currentLevel+=1;
            std::ofstream file("../../Baba_is_you/metier/levels/level_-1.txt", std::ofstream::trunc);
            file.close();
            view->printMessage("Level done !\nPress any arrow to continue");
            if (currentLevel == 5){
                goto endGame;
            }
            boardLoader = {currentLevel};
            game = {&boardLoader};
            game.Attach(this->view);
        }
    }
endGame:
    view->printMessage("Congratulation you win the game, it's over !");

}

bool ControllerGUI::isFileEmpty(std::ifstream& file) {
    std::ostringstream oss;
    oss << file.rdbuf();
    return oss.str().empty();
}

bool ControllerGUI::updateLevel(int event){
    std::string always = "Press 'S' to save the state of the game.\n"
                         "Press 'L' to load the saved state of the game.";
    std::ifstream fichier("../../Baba_is_you/metier/levels/level_-1.txt");
    switch (event)
    {
    case Qt::Key_Up:
        this->dir = Direction::UP;
        view->clearTextEdit();
        view->printMessage(always);
        break;
    case Qt::Key_Down:
        this->dir = Direction::DOWN;
        view->clearTextEdit();
        view->printMessage(always);
        break;
    case Qt::Key_Left:
        this->dir = Direction::LEFT;
        view->clearTextEdit();
        view->printMessage(always);
        break;
    case Qt::Key_Right:
        this->dir = Direction::RIGHT;
        view->clearTextEdit();
        view->printMessage(always);
        break;
    case Qt::Key_R:
        this->dir = Direction::STOP;
        this->rPressed = true;
        view->clearTextEdit();
        view->printMessage(always);
        break;
    case Qt::Key_S:
        this->dir = Direction::STOP;
        game.saveLevel();
        view->printMessage(always);
        break;
    case Qt::Key_L:
        this->dir = Direction::STOP;
        if (!isFileEmpty(fichier)){
            this->currentLevel = boardLoader.getLevelNumber();
            boardLoader = {-1};
            game = {&boardLoader};
            game.Attach(this->view);
            game.getBoardLoader()->setLevel(this->currentLevel);
            view->displayBoard(&game);
            view->printMessage(always);
        } else {
            view->printMessage("There is no saved level");
        }
        break;
    default:
        return false;
        break;
    }
    return true;
}
