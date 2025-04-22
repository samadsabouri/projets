package g53960.thegeniussquare.model;

/**
 * * Represents a dice used in The Genius Square game. The dice has different
 * sides with positions.
 *
 * @author Samad
 */
public class Dice {

    private final Position[][] diceSides;

    /**
     * Constructs a new dice with predefined positions for each side.
     *
     */
    public Dice() {
        this.diceSides = new Position[][]{
            {new Position(0, 0), new Position(2, 0), new Position(3, 0), new Position(3, 1), new Position(4, 1), new Position(5, 2)},
            {new Position(0, 1), new Position(1, 1), new Position(2, 1), new Position(0, 2), new Position(1, 0), new Position(1, 2)},
            {new Position(2, 2), new Position(3, 2), new Position(4, 2), new Position(1, 3), new Position(2, 3), new Position(3, 3)},
            {new Position(4, 0), new Position(5, 1), new Position(5, 1), new Position(1, 5), new Position(0, 4), new Position(0, 4)},
            {new Position(0, 3), new Position(1, 4), new Position(2, 5), new Position(2, 4), new Position(3, 5), new Position(3, 5)},
            {new Position(4, 3), new Position(5, 3), new Position(4, 4), new Position(5, 4), new Position(3, 4), new Position(4, 5)},
            {new Position(5, 0), new Position(5, 0), new Position(5, 0), new Position(0, 5), new Position(0, 5), new Position(0, 5)}

        };
    }

    /**
     * Generates a random number between 0 and 5 (inclusive).
     *
     * @return the randomly generated number
     */
    private int generateRandom() {
        final int max = 6;
        return (int) (Math.random() * max);
    }

    /**
     * Rolls the dice and returns an array of positions corresponding to the
     * dice sides.
     *
     * @return an array of positions representing the results of the dice roll
     */
    public Position[] getRandom() {
        Position[] dicesResults = new Position[7];

        for (int i = 0; i < diceSides.length; i++) {
            int random = generateRandom();
            dicesResults[i] = diceSides[i][random];
        }
        return dicesResults;
    }
}
