#ifndef CONTROLLERGUI_H
#define CONTROLLERGUI_H

#include "../metier/Game.h"
#include "Mainwindow.h"
#include <fstream>
#include <sstream>
#include <QKeyEvent>
#include <QApplication>
#include <QCoreApplication>
#include <QGuiApplication>

/**
 * @brief The Controller class represents the game controller.
 */
class ControllerGUI : public QWidget{
    Q_OBJECT
private:
    int  currentLevel;
    BoardLoader  boardLoader;
    Game  game ;
    MainWindow*  view;
    Direction dir = Direction::STOP;
    bool rPressed = false;
    bool sPressed = false;
    bool lPressed = false;

public:
    /**
     * @brief Constructor for the Controller class.
     * @param currentLevel The current level of the game.
     */
    ControllerGUI (int currentLevel);

    /**
     * @brief Plays the game.
     */
    void play();

    /**
     * @brief resetAttribute to reset rPressed, sPressed, lPressed and dir attribute.
     */
    void resetAttribute();

    /**
     * @brief isFileEmpty to check if a file is empty.
     * @param filename the file to check.
     * @return true if the file is empty.
     */
    bool isFileEmpty(std::ifstream& file);

    /**
     * @brief getController getter of the controller.
     * @return the controller.
     */
    ControllerGUI* getController(){
        return this;
    }

    /**
     * @brief getGame getter of the game.
     * @return the game.
     */
    Game* getGame(){
        return &game;
    }

    /**
     * @brief keyPressEvent to catch an event and do things depending on that.
     * @param event the event to catch.
     */
    void keyPressEvent(QKeyEvent *event) override {

        // Déplacer la case en fonction de la touche pressée
        switch (event->key()) {
        case Qt::Key_Up:
            this->dir = Direction::UP;
            break;
        case Qt::Key_Down:
            this->dir = Direction::DOWN;
            break;
        case Qt::Key_Left:
            this->dir = Direction::LEFT;
            break;
        case Qt::Key_Right:
            this->dir = Direction::RIGHT;
            break;
        }


    }

private slots:
    /**
     * @brief updateLevel to update the level and do things depending on the event.
     * @param event to do things
     * @return true if an action is performed.
     */
    bool updateLevel(int event );
};


#endif // CONTROLLERGUI_H
