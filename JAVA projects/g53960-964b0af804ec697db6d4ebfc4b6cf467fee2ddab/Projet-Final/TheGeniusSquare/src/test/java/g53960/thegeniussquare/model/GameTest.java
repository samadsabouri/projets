/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.thegeniussquare.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class GameTest {

    @Test
    public void testPlaceShape() {
        System.out.println("placeShape");

        ShapeColor color = ShapeColor.BLUE;
        Position pos = new Position(1, 1);
        Position[] blockerPositions = {new Position(0, 0),
            new Position(0, 1),
            new Position(2, 2),
            new Position(4, 0),
            new Position(0, 3),
            new Position(4, 3),
            new Position(5, 0)};
        Board board = new Board(blockerPositions);
        Game instance = new Game(board);

        Boolean expResult = true;
        Boolean result = instance.placeShape(color, pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsWinWhenAllSquaresFilled() {
        Position[] blockerPositions = {new Position(0, 0),
            new Position(0, 2),
            new Position(0, 5),
            new Position(3, 2),
            new Position(3, 4),
            new Position(5, 1),
            new Position(5, 5)};
        Board board = new Board(blockerPositions);
        Game instance = new Game(board);
        Boolean expResult = true;
        for (int i = 0; i < board.getSquares().length; i++) {
            for (int j = 0; j < board.getSquares()[i].length; j++) {
                instance.placeShape(ShapeColor.BLUE, new Position(i, j));
            }

        }

        assertEquals(expResult, instance.isWin());
    }

    @Test
    public void testIsWinWhenNotAllSquaresFilled() {
        Position[] blockerPositions = {new Position(0, 0),
            new Position(0, 2),
            new Position(0, 5),
            new Position(3, 2),
            new Position(3, 4),
            new Position(5, 1),
            new Position(5, 5)};
        Board board = new Board(blockerPositions);
        Game instance = new Game(board);
        Boolean expResult = false;
        instance.placeShape(ShapeColor.BLUE, new Position(3, 0));

        assertEquals(expResult, instance.isWin());
    }

}
