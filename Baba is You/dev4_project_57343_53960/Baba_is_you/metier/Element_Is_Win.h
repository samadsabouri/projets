#ifndef ELEMENT_IS_WIN_H
#define ELEMENT_IS_WIN_H
#include "GameRule.h"

/**
 * @brief The Element_Is_Win class  is a concrete implementation of the GameRule class that represents the "win" rule for certain elements in the game.
 */
class Element_Is_Win : public GameRule{

public:
    /**
     * @brief This method applies the "win" rule to an element and updates the board loader accordingly.
     * @param elem The element to apply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void apply(Element elem, BoardLoader* boardLoader) override {
        std::vector<Element> temp ;
        std::vector<Element> cpUpdate;
        std::string name = elem.getName();
        Position elemPos;
        if (name == "text_baba"){
            elemPos = boardLoader->getBabaPtr().getPosition();
            for (auto baba :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (baba->getName()=="baba"){
                    baba->setWin(true);
                    boardLoader->setBabaPtr(*baba);
                    temp.push_back(*baba);
                }
            }
        } else if (name == "text_flag"){
            elemPos = boardLoader->getFlagPtr().getPosition();
            for (auto flag :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (flag->getName()=="flag"){
                    flag->setWin(true);
                    boardLoader->setFlagPtr(*flag);
                    temp.push_back(*flag);
                }
            }
        } else if (name == "text_grass"){
            for (auto vtrGrass : boardLoader->getGrassPtr()){
                elemPos = vtrGrass.getPosition();
                for (auto grass :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (grass->getName()=="grass"){
                        grass->setWin(true);
                        cpUpdate.push_back(*grass);
                        temp.push_back(*grass);
                    }
                }
                boardLoader->setGrassPtr(cpUpdate);
            }

        } else if (name =="text_lava"){
            for (auto vtrLava : boardLoader->getLavaPtr()){
                elemPos = vtrLava.getPosition();
                for (auto lava :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (lava->getName()=="lava"){
                        lava->setWin(true);
                        cpUpdate.push_back(*lava);
                        temp.push_back(*lava);
                    }
                }
                boardLoader->setLavaPtr(cpUpdate);
            }
        } else if (name == "text_metal"){
            for (auto vtrMetal : boardLoader->getMetalPtr()){
                elemPos = vtrMetal.getPosition();
                for (auto metal :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (metal->getName()=="metal"){
                        metal->setWin(true);
                        cpUpdate.push_back(*metal);
                        temp.push_back(*metal);
                    }
                }
                boardLoader->setMetalPtr(cpUpdate);
            }
        } else if (name == "text_rock"){
            for (auto vtrRock : boardLoader->getRockPtr()){
                elemPos = vtrRock.getPosition();
                for (auto rock :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (rock->getName()=="rock"){
                        rock->setWin(true);
                        cpUpdate.push_back(*rock);
                        temp.push_back(*rock);
                    }
                }
                boardLoader->setRockPtr(cpUpdate);
            }
        } else if (name == "text_wall"){
            for (auto vtrWall : boardLoader->getWallPtr()){
                elemPos = vtrWall.getPosition();
                for (auto wall :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (wall->getName()=="wall"){
                        wall->setWin(true);
                        cpUpdate.push_back(*wall);
                        temp.push_back(*wall);
                    }
                }
                boardLoader->setWallPtr(cpUpdate);
            }
        } else if (name == "text_water"){
            for (auto vtrWater : boardLoader->getWaterPtr()){
                elemPos = vtrWater.getPosition();
                for (auto water :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (water->getName()=="water"){
                        water->setWin(true);
                        cpUpdate.push_back(*water);
                        temp.push_back(*water);
                    }
                }
                boardLoader->setWaterPtr(cpUpdate);
            }
        }
        std::vector<Element> currentElem = boardLoader->getBoardPtr()->getMovableElements();
        for (auto cur : currentElem){
            for (auto tem : temp){
                if (cur.getPosition() == tem.getPosition()){
                    boardLoader->setLevel(boardLoader->getLevelNumber()+1);
                }
            }
        }
    }

    /**
     * @brief This method  disapply the "win" rule.
     * @param elem The element to disapply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void disapply(Element elem, BoardLoader* boardLoader) override{
        Element element1 = elem;
        BoardLoader *b =  boardLoader;
        b->getBoardPtr();
    }

};

#endif // ELEMENT_IS_WIN_H
