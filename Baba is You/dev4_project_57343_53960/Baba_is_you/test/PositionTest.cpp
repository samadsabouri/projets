#include "Catch.hpp"
#include <stdexcept>
#include "../metier/Position.h"

SCENARIO("test the class position")
{
    GIVEN("a position with specific row and column")
    {
        Position pos{1,1};
        REQUIRE(pos.getRow() == 1);
        REQUIRE(pos.getColumn() == 1);



        WHEN("try to change the position")
        {
            THEN("the position should be updated")
            {
                int row {5};
                int col{6};
                Position changedPosition{row,col};
                pos.setPosition(row, col);

                REQUIRE(pos == changedPosition);

            }
        }
        WHEN("try to change the position to the next position using Direction")
        {
            THEN("the position should be updated")
            {
                int row {0};
                int col{1};
                Position changedPosition{row,col};
                pos.nextPosition(Direction::UP);

                REQUIRE(pos == changedPosition);

            }
        }
    }
}
