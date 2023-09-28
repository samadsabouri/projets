#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "../metier/Game.h"
#include "View.h"

/**
 * @brief The Controller class represents the game controller.
 */
class Controller {
private:
    int  currentLevel;
    BoardLoader  boardLoader;
    Game  game ;
    View  view;


public:
    /**
     * @brief Constructor for the Controller class.
     * @param currentLevel The current level of the game.
     */
    Controller (int currentLevel);

    /**
     * @brief Plays the game.
     */
    void play();

    /**
     * @brief orientation
     * Determines the direction based on the given key input.
     * @param key The key input.
     * @return The corresponding Direction enum value.
     */
    Direction orientation(int &key);

    /**
     * @brief updateLevelState
     * Updates the state of the current level.
     * @return The direction of the level update (e.g., next level, previous level, etc.).
     */
    Direction updateLevelState();

    /**
     * @brief getController
     * Returns a pointer to the current Controller instance.
     * @return A pointer to the current Controller instance.
     */
    Controller* getController(){
        return this;
    }
    /**
     * @brief getGame
     * Returns a pointer to the Game instance.
     * @return A pointer to the Game instance.
     */
    Game* getGame(){
        return &game;
    }
};


#endif // CONTROLLER_H
