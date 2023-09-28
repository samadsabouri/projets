#include "Catch.hpp"
#include <stdexcept>
#include "../metier/Element.h"
#include "../metier/elements/Baba.h"
#include "../metier/elements/Is.h"
#include "../metier/elements/Stop.h"
#include "../metier/elements/Wall_text.h"
#include "../metier/Square.h"

SCENARIO("test the Square class")
{
    GIVEN("4 squares which contain all types of elements")
    {     Baba  babaBlock = {{5,8}, Direction::UP};
          Wall_Text wallText = {{4,4}, Direction::UP};
          Is isConnector = {{4,5}, Direction::UP};
          Stop   stopRule = {{4,6}, Direction::UP};
          Element baba {babaBlock};
          Element textWall = {wallText};
          Element is = {isConnector};
          Element stop ={stopRule};

          Square squareBaba{{5,8}};
          Square squareWallText{{4,4}};
          Square squareIsConnector{{4,5}};
          Square squareStopRule{{4,6}};

          squareBaba.addElement(&baba);
          REQUIRE(squareBaba.getElements()[0].getName()== "baba");


        WHEN("the square are created and elements also created by inheritance")
        {
            THEN("checks the ability to add an element into invalid square")
            {

                REQUIRE_THROWS(squareBaba.addElement(&is));
                REQUIRE_THROWS_AS(squareBaba.addElement(&is),std::invalid_argument);
                REQUIRE(squareBaba.getElements()[0].getName() == "baba");

            }
        }
        WHEN("try to remove element from a square")
        {
            THEN("checks if the element is well removed")
            {
                squareBaba.removeElement(&baba);

                REQUIRE((babaBlock.getSymbol()==("(#_#)")));
                REQUIRE(squareBaba.getElements().size() == 0);


            }
        }

    }


}
