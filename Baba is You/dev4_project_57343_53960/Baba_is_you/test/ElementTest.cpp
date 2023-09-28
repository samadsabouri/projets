#include "Catch.hpp"
#include <stdexcept>
#include "../metier/elements/Baba.h"
#include "../metier/elements/Is.h"
#include "../metier/elements/Stop.h"
#include "../metier/elements/Wall_text.h"


SCENARIO("test the Element class")
{
    GIVEN("elements with different types with different position like the first level")
    {
        Baba  babaBlock = {{5,8}, Direction::UP};
            Wall_Text wallText = {{4,4}, Direction::UP};
            Is isConnector = {{4,5}, Direction::UP};
            Stop   stopRule = {{4,6}, Direction::UP};
            REQUIRE(babaBlock.getName()==("baba"));
            REQUIRE(wallText.getName() ==("wall_text"));
            REQUIRE(isConnector.getName()==("is"));
            REQUIRE(stopRule.getName() == ("stop"));

        WHEN("the elements are created by inheritance")
        {
            THEN("checks if they have the same type")
            {

                    REQUIRE(babaBlock.getElementType()==(ElementType::BLOCK));
                    REQUIRE(wallText.getElementType() ==(ElementType::BLOCK_TEXT));
                    REQUIRE(isConnector.getElementType()==(ElementType::CONNECTOR));
                    REQUIRE(stopRule.getElementType() == (ElementType::RULE));

            }
        }
        WHEN("the elements are created by inheritance ")
        {
            THEN("checks if they have the same symbol")
            {
                    REQUIRE((babaBlock.getSymbol()==("(#_#)")));
                    REQUIRE(wallText.getSymbol() ==("wall "));
                    REQUIRE(isConnector.getSymbol()==("_is_ "));
                    REQUIRE(stopRule.getSymbol() == ("stop "));


            }
        }
        WHEN("try to change element's position ")
        {
            THEN("checks if the position is changed")
            {
                Position pos {4,8};
                babaBlock.setPosition(pos);

                REQUIRE(babaBlock.getPosition() == pos);



            }
        }
        WHEN("the element type is : connector, rule or block_text")
        {
            THEN("checks if the default movable is false")
            {

                REQUIRE(!isConnector.isMoveble());
                REQUIRE(!stopRule.isMoveble());
                REQUIRE(!wallText.isMoveble());
            }
        }
        WHEN("the element type is : connector, rule or block_text")
        {
            THEN("checks if the default movable is false")
            {
                REQUIRE(!isConnector.isMoveble());
                REQUIRE(!stopRule.isMoveble());
                REQUIRE(!wallText.isMoveble());
            }
        }
        WHEN("the element is block")
        {
            THEN("checks if we can change the state to movable to true")
            {
                babaBlock.setMoveble(true);
                REQUIRE(babaBlock.isMoveble());
            }
        }

        WHEN("the element is not block")
        {
            THEN("checks if we can change the state to movable to false")
            {
                babaBlock.setMoveble(true);
                babaBlock.setMoveble(false);
                REQUIRE(!babaBlock.isMoveble());
            }
        }
    }


}
