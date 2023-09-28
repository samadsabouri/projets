#ifndef VIEWGUI_H
#define VIEWGUI_H
#include "../metier/Game.h"
#include "../metier/Observer.h"

/**
 * @brief The View class represents the game view.
 */
class ViewGUI :public Observer{

public:
    /**
     * @brief Constructor for the View class.
     */
    ViewGUI();

    /**
      * @brief Displays the game board.
      * @param game The game instance.
      */
    void displayBoard(Game* game);

    /**
      * @brief Prints a message to the console.
      * @param The message to be printed.
      */
    void printMessage(std::string message);

    void clear();


    /**
     * @brief The update method for the Observer pattern.
     * @param subject The subject instance.
     */

    virtual void Update(Subject *) override;

};

#endif // VIEWGUI_H
