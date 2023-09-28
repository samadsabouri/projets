#ifndef GAME_H
#define GAME_H
#include "BoardLoader.h"
#include "GameRule.h"
#include "Element_Is_Kill.h"
#include "Element_Is_Push.h"
#include "Element_Is_Sink.h"
#include "Element_Is_Stop.h"
#include "Element_Is_Win.h"
#include "Element_Is_You.h"
#include "Subject.h"
#include <string>
#include <memory>
#include <fstream>

/**
 * @brief Class representing the game, it contains the essential methods for the progress of the game.
 */
class Game :public Subject{
private:
    int Level;
    BoardLoader* board;
    std::vector<Element> currentElement;
    int z = 0;

    /**
     * @brief list_of_observer, a vector with all my Observers.
     */
    std::vector<Observer *> list_of_observer;
public:

    /**
     * @brief Default constructor of the Game class.
     */
    Game(BoardLoader* b):board{b}{}

    /**
     * @brief Saves the current level.
     */
    void saveLevel();

    /**
     * @brief Checks if the game should be restarted.
     * @return True if the game should be restarted, false otherwise.
     */
    bool isRestart();

    /**
     * @brief Returns the z-coordinate
     * @return The z-coordinate
     */
    int getZ(){
        return z;
    }

    /**
     * @brief Sets the z-coordinate
     * @param newZ The new z-coordinate
     */
    void setZ(int newZ){
        z = newZ;
    }

    /**
     * @brief Checks if a position is not inside the game board.
     * @param pos The position to check.
     * @return True if the position is not inside the game board, false otherwise.
     */
    bool checkPositionNotInBoard(Position pos);


    /**
     * @brief Applies a rule to the current element.
     * @param rule The name of the rule to apply.
     * @param elem The element to apply the rule to.
     */
    void applyRule(std::string rule, Element elem);

    /**
     * @brief isRule to check if a element is a rule or not.
     * @param elemsToCheck element to check.
     * @return true if the element type is 'rule'.
     */
    bool isRule(std::vector<Element*> elemsToCheck);

    /**
     * @brief isConnector to check if a element is a connector or not.
     * @param elemsToCheck element to check.
     * @return true if the element type is 'connector'.
     */
    bool isConnector(std::vector<Element*> elemsToCheck);

    /**
     * @brief whichRule to disapply a specific rule.
     * @param a the element where we disapply the rule.
     * @param rule the specific rule to disapply.
     */
    void whichRule(Element a,Element rule);

    /**
     * @brief DisAllRules to disapply all the rules of an element.
     * @param a the element to disapply all the rules.
     */
    void DisAllRules(Element a);

    /**
     * @brief changeIsPtr to change the order of the 'is' pointer.
     * @param pos1 the position of the first 'is'.
     * @param pos2 the position of the 'is' next to the 'you' rule.
     */
    void changeIsPtr(int pos1, int pos2);

    /**
     * @brief searchYou to find the rule 'you'.
     * @return the index of the 'is' next to 'you'.
     */
    int searchYou();

    /**
     * @brief disapplyRule to disapply the rules aren't written or broken.
     */

    void disapplyRule();

    /**
     * @brief Checks if a square respects the rules of the game.
     */
    void checkRule();

    /**
     * @brief Returns the BoardLoader instance.
     * @return  The BoardLoader instance.
     */
    BoardLoader* getBoardLoader();

    /**
     * @brief Sets the BoardLoader .
     */
    void setBoardLoader();

    /**
     * @brief Checks if a vector of elements can be pushed in a certain direction.
     * @param elemsToCheck The vector of elements to check.
     * @return True if the vector of elements can be pushed, false otherwise.
     */
    bool checkElemPush(std::vector<Element*> elemsToCheck);


    /**
     * @brief Checks if a square can be pushed in a certain direction.
     * @param square The square to check.
     * @param dir The direction to push the square in.
     * @return True if the square can be pushed, false otherwise.
     */
    bool checkPush(Square* square, Direction dir);

    /**
     * @brief Moves all pushable elements in a square in a certain direction.
     * @param square The square to move pushable elements from.
     * @param dir The direction to move the pushable elements in.
     */
    void movePushableElems(Square* square, Direction dir);


    /**
     * @brief Checks if a vector of elements can be stopped.
     * @param elemsToCheck The vector of elements to check.
     * @return True if the vector of elements can be stopped, false otherwise.
     */
    bool checkElemStop(std::vector<Element*> elemsToCheck);

    /**
     * @brief Sets pointers to all elements in a vector.
     * @param  test The vector of elements to set pointers for.
     */
    void setAllPtr(std::vector<Element> test);

    /**
     * @brief Returns the position of the first gap in a certain direction from a square.
     * @param currentSquare The square to start from.
     * @param dir The direction to look in.
     * @param dir The direction to look in.
     * @return The position of the first gap in the given direction and gap size.
     */
    Position gapInDirection(Square* currentSquare,Direction dir, int gap);

    /**
     * @brief updateIsPos to update the position of the element 'is'.
     */
    void updateIsPos();

    /**
     * @brief updateElemPos to update the position of an element.
     * @param elem the element to update his position.
     */
    void updateElemPos(Element elem);



    /**
     * @brief Moves the player according to the current orientation and the current element.
     */
    void move(Direction &dir);

    /**
     * @brief Attach, add the given observer of the list.
     * @param observer, the given observer to add.
     */
    void Attach(Observer * observer) override;

    /**
     * @brief Detach, delete the given observer of the list.
     * @param observer, the given observer to delete.
     */
    void Detach(Observer * observer) override;

    /**
     * @brief Notify, Call the method Update of the view.
     */
    void Notify() override;


};

#endif // GAME_H

