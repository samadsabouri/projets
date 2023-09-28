#ifndef GAMERULE_H
#define GAMERULE_H
#include <string>
#include "BoardLoader.h"
/**
 * @brief The GameRule class.
 */
class GameRule {

public :
    /**
     * @brief Applies a rule based on the different elements.
     * @param elem The element to apply the rule on.
     * @param boardLoader The board loader object to modify the board state.
     */
    virtual void apply(Element elem, BoardLoader* boardLoader) = 0;

    /**
     * @brief Disapplies a rule based on the different elements.
     * @param elem The element to disapply the rule on.
     * @param boardLoader The board loader object to modify the board state.

     */
    virtual void disapply(Element elem, BoardLoader* boardLoader);
};

#endif // GAMERULE_H
