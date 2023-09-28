#ifndef ELEMENT_IS_YOU_H
#define ELEMENT_IS_YOU_H
#include "GameRule.h"
/**
 * @brief The Element_Is_You class  is a concrete implementation of the GameRule class that represents the "you" rule for certain elements in the game.
 */
class Element_Is_You : public GameRule{

public:
    /**
     * @brief This method applies the "you" rule to an element and updates the board loader accordingly.
     * @param elem The element to apply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void apply(Element elem, BoardLoader* boardLoader) override {
        std::string name = elem.getName();
        std::vector<Element> newMovable ;
        if (name == "text_baba"){
            newMovable.push_back(boardLoader->getBabaPtr());
        } else if (name == "text_flag"){
            newMovable.push_back(boardLoader->getFlagPtr());
        } else if (name == "text_grass"){
            newMovable = boardLoader->getGrassPtr();
        } else if (name=="text_lava"){
            newMovable = boardLoader->getLavaPtr();
        } else if (name == "text_metal"){
            newMovable = boardLoader->getMetalPtr();
        } else if (name == "text_rock"){
            newMovable = boardLoader->getRockPtr();
        } else if (name == "text_wall"){
            newMovable = boardLoader->getWallPtr();
        } else if (name == "text_water"){
            newMovable = boardLoader->getWaterPtr();
        }

        boardLoader->getBoardPtr()->updateMovableElements(newMovable);
    }
    /**
     * @brief This method  disapply the "you" rule.
     * @param elem The element to disapply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void disapply(Element elem, BoardLoader* boardLoader) override{
        std::vector<Element> notYou{};
        boardLoader->getBoardPtr()->updateMovableElements(notYou);
        Element element1 = elem;
    }
};

#endif // ELEMENT_IS_YOU_H

