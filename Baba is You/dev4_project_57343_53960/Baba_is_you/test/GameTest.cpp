#include "Catch.hpp"
#include <stdexcept>
#include "../metier/Game.h"
#include "../console/View.h"


SCENARIO("test the class Game")
{
    GIVEN("a game with a specific level")
    {
        BoardLoader boardLoader{0};
        Game game (&boardLoader);
        BoardLoader* ptr ;
        ptr = &boardLoader;
        REQUIRE(ptr==game.getBoardLoader());




        WHEN("check if a position is in the board")
        {
            THEN("the position should be in the board")
            {
                int row {5};
                int col{6};
                Position pos{row,col};

                REQUIRE(!game.checkPositionNotInBoard(pos));

            }
        }

        WHEN("check if a position is not in the board because of the row")
        {
            THEN("the position should not be in the board")
            {
                int row {500};
                int col{6};
                Position pos{row,col};

                REQUIRE(game.checkPositionNotInBoard(pos));

            }
        }

        WHEN("check if a position is not in the board because of the row")
        {
            THEN("the position should not be in the board")
            {
                int row {-500};
                int col{6};
                Position pos{row,col};

                REQUIRE(game.checkPositionNotInBoard(pos));

            }
        }

        WHEN("check if a position is not in the board because of the column")
        {
            THEN("the position should not be in the board")
            {
                int row {10};
                int col{500};
                Position pos{row,col};

                REQUIRE(game.checkPositionNotInBoard(pos));

            }
        }

        WHEN("check if a position is not in the board because of the column")
        {
            THEN("the position should not be in the board")
            {
                int row {10};
                int col{-500};
                Position pos{row,col};

                REQUIRE(game.checkPositionNotInBoard(pos));

            }
        }

        WHEN("check if an element is a rule")
        {
            THEN("the element should be a rule")
            {
                REQUIRE(game.isRule(game.getBoardLoader()->getBoardPtr()->getSquares()[4][6]->getElementsPtr()));
            }
        }

        WHEN("check if an element is not a rule")
        {
            THEN("the element should not be a rule")
            {
                REQUIRE(!game.isRule(game.getBoardLoader()->getBoardPtr()->getSquares()[4][7]->getElementsPtr()));
            }
        }

        WHEN("check if an element is a connector")
        {
            THEN("the element shoud be a connector")
            {
                REQUIRE(game.isConnector(game.getBoardLoader()->getBoardPtr()->getSquares()[4][12]->getElementsPtr()));
            }
        }

        WHEN("check if an element is not a connector")
        {
            THEN("the element shoud not be a connector")
            {
                REQUIRE(!game.isConnector(game.getBoardLoader()->getBoardPtr()->getSquares()[4][1]->getElementsPtr()));
            }
        }

        WHEN("check if two elements 'is' are correctly switched")
        {
            THEN("the two 'is' should be inverted")
            {
                std::vector<Element> is = boardLoader.getIsPtr();
                Element * firstIs;
                firstIs = &(is[0]);
                Element * secondIs ;
                secondIs = &(is[1]);
                game.changeIsPtr(0,1);
                std::vector<Element> newIs = boardLoader.getIsPtr();
                Element * checkFirstIs ;
                checkFirstIs = &(newIs[0]);
                Element * checkSecondIs ;
                checkSecondIs = &(newIs[1]);
                REQUIRE((firstIs->getPosition() == checkSecondIs->getPosition() && secondIs->getPosition() == checkFirstIs->getPosition()));
            }
        }

        WHEN("check where is the 'is' next to the 'you'")
        {
            THEN("it should be the third in the first level")
            {
                REQUIRE(game.searchYou()==2);
            }
        }


        WHEN("check if an element is pushable after apply the rules")
        {
            THEN("the element should be pushable")
            {
                game.checkRule();
                REQUIRE(game.checkElemPush(game.getBoardLoader()->getBoardPtr()->getSquares()[8][8]->getElementsPtr()));
            }
        }

        WHEN("check if an element is not pushable after apply the rules")
        {
            THEN("the element should not be pushable")
            {
                game.checkRule();
                REQUIRE(!game.checkElemPush(game.getBoardLoader()->getBoardPtr()->getSquares()[9][13]->getElementsPtr()));
            }
        }

        WHEN("check if nothing is not pushable after apply the rules")
        {
            THEN("it should not be pushable")
            {
                game.checkRule();
                REQUIRE(!game.checkElemPush(game.getBoardLoader()->getBoardPtr()->getSquares()[1][1]->getElementsPtr()));
            }
        }

        WHEN("check if an element is stopable after apply the rules")
        {
            THEN("the element should be stopable")
            {
                game.checkRule();
                REQUIRE(game.checkElemStop(game.getBoardLoader()->getBoardPtr()->getSquares()[6][3]->getElementsPtr()));
            }
        }

        WHEN("check if an element is not stopable after apply the rules")
        {
            THEN("the element should not be stopable")
            {
                game.checkRule();
                REQUIRE(!game.checkElemStop(game.getBoardLoader()->getBoardPtr()->getSquares()[9][13]->getElementsPtr()));
            }
        }

        WHEN("check if nothing is not stopable after apply the rules")
        {
            THEN("it should not be stopable")
            {
                game.checkRule();
                REQUIRE(!game.checkElemStop(game.getBoardLoader()->getBoardPtr()->getSquares()[1][1]->getElementsPtr()));
            }
        }

        WHEN("check if a position is correct when we have a gap and a square, down")
        {
            THEN("the position should be correct")
            {
                Square *currentSquare = game.getBoardLoader()->getBoardPtr()->getSquares()[1][1];
                Position newPos = game.gapInDirection(currentSquare,Direction::DOWN,3);
                Position toCheck {currentSquare->getPosition().getRow()+4,currentSquare->getPosition().getColumn()};
                REQUIRE(newPos == toCheck);
            }
        }

        WHEN("check if a position is correct when we have a gap and a square, up")
        {
            THEN("the position should be correct")
            {
                Square *currentSquare = game.getBoardLoader()->getBoardPtr()->getSquares()[10][1];
                Position newPos = game.gapInDirection(currentSquare,Direction::UP,3);
                Position toCheck {currentSquare->getPosition().getRow()-4,currentSquare->getPosition().getColumn()};
                REQUIRE(newPos == toCheck);
            }
        }

        WHEN("check if a position is correct when we have a gap and a square, right")
        {
            THEN("the position should be correct")
            {
                Square *currentSquare = game.getBoardLoader()->getBoardPtr()->getSquares()[1][1];
                Position newPos = game.gapInDirection(currentSquare,Direction::RIGHT,3);
                Position toCheck {currentSquare->getPosition().getRow(),currentSquare->getPosition().getColumn()+4};
                REQUIRE(newPos == toCheck);
            }
        }

        WHEN("check if a position is correct when we have a gap and a square, left")
        {
            THEN("the position should be correct")
            {
                Square *currentSquare = game.getBoardLoader()->getBoardPtr()->getSquares()[1][10];
                Position newPos = game.gapInDirection(currentSquare,Direction::LEFT,3);
                Position toCheck {currentSquare->getPosition().getRow(),currentSquare->getPosition().getColumn()-4};
                REQUIRE(newPos == toCheck);
            }
        }

        WHEN("check if a position is correct when we have a gap and a square, false when it's not in the board")
        {
            THEN("the position should not be correct")
            {
                Square *currentSquare = game.getBoardLoader()->getBoardPtr()->getSquares()[1][1];
                Position newPos = game.gapInDirection(currentSquare,Direction::UP,3);
                REQUIRE(game.checkPositionNotInBoard(newPos));
            }
        }


        WHEN("check if 'isPtr' is correctly update")
        {
            THEN("the update should be correctly done")
            {
                Direction left {Direction::LEFT};
                Direction down {Direction::DOWN};
                Direction right {Direction::RIGHT};
                Position firstPosIs = game.getBoardLoader()->getIsPtr()[2].getPosition();
                game.move(left);
                game.move(left);
                game.move(left);
                game.move(down);
                game.move(down);
                game.move(down);
                game.move(right);
                game.move(right);
                game.move(right);
                game.move(down);
                game.updateIsPos();
                Position newPosIs = game.getBoardLoader()->getIsPtr()[3].getPosition();
                REQUIRE(firstPosIs.getRow()+1==newPosIs.getRow());
            }
        }

        WHEN("check if an element is correctly moved")
        {
            THEN("baba should move on the right")
            {
                Direction right {Direction::RIGHT};
                Position firstPosBaba = game.getBoardLoader()->getBabaPtr().getPosition();
                game.move(right);
                Position newPosBaba = game.getBoardLoader()->getBoardPtr()->getSquares()[8][6]->getElementsPtr().back()->getPosition();
                REQUIRE(firstPosBaba.getColumn()+1==newPosBaba.getColumn());
            }
        }

        WHEN("check if an element is correctly stopped by an element 'stop'")
        {
            THEN("baba should stay on the same position next to an element stop")
            {
                Direction up {Direction::UP};
                Position firstPosBaba = game.getBoardLoader()->getBabaPtr().getPosition();
                for (unsigned i = 0; i<100;i++){
                    game.move(up);
                }
                Position newPosBaba = game.getBoardLoader()->getBoardPtr()->getSquares()[7][5]->getElementsPtr().back()->getPosition();
                REQUIRE(firstPosBaba.getRow()-1==newPosBaba.getRow());
            }
        }

        WHEN("check if an element is correctly stopped by a edge")
        {
            THEN("baba should stay on the same position next to a edge")
            {
                Direction left {Direction::LEFT};
                Position firstPosBaba = game.getBoardLoader()->getBabaPtr().getPosition();
                for (unsigned i = 0; i<100;i++){
                    game.move(left);
                }
                Position newPosBaba = game.getBoardLoader()->getBoardPtr()->getSquares()[8][0]->getElementsPtr().back()->getPosition();
                REQUIRE(firstPosBaba.getColumn()-5==newPosBaba.getColumn());
            }
        }

        WHEN("check if an element is correctly moved next to an element push")
        {
            THEN("baba should move")
            {
                Direction right {Direction::RIGHT};
                Position firstPosBaba = game.getBoardLoader()->getBabaPtr().getPosition();
                for (unsigned i = 0; i<3;i++){
                    game.move(right);
                }
                Position newPosBaba = game.getBoardLoader()->getBoardPtr()->getSquares()[8][8]->getElementsPtr().back()->getPosition();
                REQUIRE(firstPosBaba.getColumn()+3==newPosBaba.getColumn());
            }
        }

    }
}
