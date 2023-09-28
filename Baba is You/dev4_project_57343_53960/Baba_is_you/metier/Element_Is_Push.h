#ifndef ELEMENT_IS_PUSH_H
#define ELEMENT_IS_PUSH_H
#include "GameRule.h"

/**
 * @brief The Element_Is_Push class  is a concrete implementation of the GameRule class that represents the "Push" rule for certain elements in the game.
 */
class Element_Is_Push : public GameRule{

public:
    /**
     * @brief This method applies the "push" rule to an element and updates the board loader accordingly.
     * @param elem The element to apply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void apply(Element elem, BoardLoader* boardLoader) override{
        std::string name = elem.getName();
        std::vector<Element> cpUpdate;
        Position elemPos;
        if (name == "text_baba"){
            elemPos = boardLoader->getBabaPtr().getPosition();
            for (auto baba :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (baba->getName()=="baba"){
                    baba->setPush(true);
                    boardLoader->setBabaPtr(*baba);
                }
            }
        } else if (name == "text_flag"){
            elemPos = boardLoader->getFlagPtr().getPosition();
            for (auto flag :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (flag->getName()=="flag"){
                    flag->setPush(true);
                    boardLoader->setFlagPtr(*flag);
                }
            }
        } else if (name == "text_grass"){
            for (auto vtrGrass : boardLoader->getGrassPtr()){
                elemPos = vtrGrass.getPosition();
                for (auto grass :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (grass->getName()=="grass"){
                        grass->setPush(true);
                        cpUpdate.push_back(*grass);
                    }
                }

            }
            boardLoader->setGrassPtr(cpUpdate);

        } else if (name =="text_lava"){
            for (auto vtrLava : boardLoader->getLavaPtr()){
                elemPos = vtrLava.getPosition();
                for (auto lava :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (lava->getName()=="lava"){
                        lava->setPush(true);
                        cpUpdate.push_back(*lava);
                    }
                }

            }
            boardLoader->setLavaPtr(cpUpdate);
        } else if (name == "text_metal"){
            for (auto vtrMetal : boardLoader->getMetalPtr()){
                elemPos = vtrMetal.getPosition();
                for (auto metal :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (metal->getName()=="metal"){
                        metal->setPush(true);
                        cpUpdate.push_back(*metal);
                    }
                }

            }
            boardLoader->setMetalPtr(cpUpdate);
        } else if (name == "text_rock"){
            for (auto vtrRock : boardLoader->getRockPtr()){
                elemPos = vtrRock.getPosition();
                for (auto rock :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (rock->getName()=="rock"){
                        rock->setPush(true);
                        cpUpdate.push_back(*rock);
                    }
                }

            }
            boardLoader->setRockPtr(cpUpdate);
        } else if (name == "text_wall"){
            for (auto vtrWall : boardLoader->getWallPtr()){
                elemPos = vtrWall.getPosition();
                for (auto wall :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (wall->getName()=="wall"){
                        wall->setPush(true);
                        cpUpdate.push_back(*wall);
                    }
                }

            }
            boardLoader->setWallPtr(cpUpdate);
        } else if (name == "text_water"){
            for (auto vtrWater : boardLoader->getWaterPtr()){
                elemPos = vtrWater.getPosition();
                for (auto water :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (water->getName()=="water"){
                        water->setPush(true);
                        cpUpdate.push_back(*water);
                    }
                }

            }
            boardLoader->setWaterPtr(cpUpdate);
        }

    }

    /**
     * @brief This method  disapply the "push" rule.
     * @param elem The element to disapply the rule to.
     * @param boardLoader The board loader object representing the current state of the game board.
     */
    inline void disapply(Element elem, BoardLoader* boardLoader) override{
        std::string name = elem.getName();
        std::vector<Element> cpUpdate;
        Position elemPos;
        if (name == "text_baba"){
            elemPos = boardLoader->getBabaPtr().getPosition();
            for (auto baba :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (baba->getName()=="baba"){
                    baba->setPush(false);
                    boardLoader->setBabaPtr(*baba);
                }
            }
        } else if (name == "text_flag"){
            elemPos = boardLoader->getFlagPtr().getPosition();
            for (auto flag :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                if (flag->getName()=="flag"){
                    flag->setPush(false);
                    boardLoader->setFlagPtr(*flag);
                }
            }
        } else if (name == "text_grass"){
            for (auto vtrGrass : boardLoader->getGrassPtr()){
                elemPos = vtrGrass.getPosition();
                for (auto grass :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (grass->getName()=="grass"){
                        grass->setPush(false);
                        cpUpdate.push_back(*grass);
                    }
                }

            }
            boardLoader->setGrassPtr(cpUpdate);

        } else if (name =="text_lava"){
            for (auto vtrLava : boardLoader->getLavaPtr()){
                elemPos = vtrLava.getPosition();
                for (auto lava :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (lava->getName()=="lava"){
                        lava->setPush(false);
                        cpUpdate.push_back(*lava);
                    }
                }

            }
            boardLoader->setLavaPtr(cpUpdate);
        } else if (name == "text_metal"){
            for (auto vtrMetal : boardLoader->getMetalPtr()){
                elemPos = vtrMetal.getPosition();
                for (auto metal :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (metal->getName()=="metal"){
                        metal->setPush(false);
                        cpUpdate.push_back(*metal);
                    }
                }

            }
            boardLoader->setMetalPtr(cpUpdate);
        } else if (name == "text_rock"){
            for (auto vtrRock : boardLoader->getRockPtr()){
                elemPos = vtrRock.getPosition();
                for (auto rock :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (rock->getName()=="rock"){
                        rock->setPush(false);
                        cpUpdate.push_back(*rock);
                    }
                }

            }
            boardLoader->setRockPtr(cpUpdate);
        } else if (name == "text_wall"){
            for (auto vtrWall : boardLoader->getWallPtr()){
                elemPos = vtrWall.getPosition();
                for (auto wall :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (wall->getName()=="wall"){
                        wall->setPush(false);
                        cpUpdate.push_back(*wall);
                    }
                }

            }
            boardLoader->setWallPtr(cpUpdate);
        } else if (name == "text_water"){
            for (auto vtrWater : boardLoader->getWaterPtr()){
                elemPos = vtrWater.getPosition();
                for (auto water :boardLoader->getBoardPtr()->getSquares()[elemPos.getRow()][elemPos.getColumn()]->getElementsPtr()){
                    if (water->getName()=="water"){
                        water->setPush(false);
                        cpUpdate.push_back(*water);
                    }
                }

            }
            boardLoader->setWaterPtr(cpUpdate);
        }
    }



};
#endif // ELEMENT_IS_PUSH_H

