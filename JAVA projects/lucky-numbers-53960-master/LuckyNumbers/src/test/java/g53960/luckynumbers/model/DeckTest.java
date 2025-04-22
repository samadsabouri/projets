/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class DeckTest {

    @Test
    public void testConstructor_CreateTilesFaceDown_TwoPlayers() {
        Deck deck = new Deck(2);
        int expectedResults = 40;
        int result = deck.faceDownCount();
        assertEquals(result, expectedResults);
    }

    @Test
    public void testConstructor_CreateTilesFaceDown_ThreePlayers() {
        Deck deck = new Deck(3);
        int expectedResults = 60;
        int result = deck.faceDownCount();
        assertEquals(result, expectedResults);
    }

    @Test
    public void testConstructor_CreateTilesFaceDown_FourPlayers() {
        Deck deck = new Deck(4);
        int expectedResults = 80;
        int result = deck.faceDownCount();
        assertEquals(result, expectedResults);
    }

    @Test
    public void testSizeTilesFaceUpBeforeStart() {
        Deck deck = new Deck(4);
        int expectedResults = 0;
        int result = deck.faceUpCount();
        assertEquals(result, expectedResults);
    }

    @Test
    public void testPickFaceDown() {
        Deck deck = new Deck(4);
        int expectedResults = 79;
        Tile tile = deck.pickFaceDown();
        int result = deck.faceDownCount();
        assertEquals(result, expectedResults);

    }

    @Test
    public void testPickFaceDown_WhenEmpty() {
        Deck deck = new Deck(4);
        deck.getTilesFaceDown().clear();
        int expectedResults = 0;
        Tile tile = deck.pickFaceDown();
        int result = deck.faceDownCount();
        assertEquals(result, expectedResults);
        assertNull(tile);
    }

    @Test
    public void testPutBack() {
        Deck deck = new Deck(4);
        Tile tile = deck.getTilesFaceDown().get(deck.faceDownCount() - 1);
        //longueur de la liste des tuiles visible avant
        int expectedResultSizeBeforePutBack = 0;
        int resultSizeBefore = deck.faceUpCount();
        assertEquals(expectedResultSizeBeforePutBack, resultSizeBefore);
        //longueur de la liste des tuiles caché avant
        int expectedResultFaceDownCountBefore = 80;
        int resultSizeTilesFacecDownBefore = deck.faceDownCount();
        assertEquals(expectedResultFaceDownCountBefore, resultSizeTilesFacecDownBefore);

        deck.putBack(tile);

        //longueur de la liste des tuiles visible après
        int expectedResultsizeAfter = 1;
        int resultSizeAfter = deck.faceUpCount();
        assertEquals(expectedResultsizeAfter, resultSizeAfter);
        //longueur de la liste des tuiles caché après

        int expectedResultFaceDownCountAfter = 80;
        int resultSizeTilesFacecDownAfter = deck.faceDownCount();
        assertEquals(expectedResultFaceDownCountAfter, resultSizeTilesFacecDownAfter);
      
    }
    @Test
    public void testPutBack_WhenIsEmpty(){
        Deck deck = new Deck(3);
        deck.getTilesFaceDown().clear();
        Tile tileTpPutBack = new Tile(6);
        deck.putBack(tileTpPutBack);
        int expectResult = 0;
        int resultListFactUPCount = deck.faceUpCount();
        assertEquals(expectResult,resultListFactUPCount);
    }
    
    @Test
    public void testHasFaceUp_WhenIsEmpty(){
        Deck deck = new Deck(4);
        Tile tileToCheck = new Tile(9);
        int expectedResult = 0;
        int resultCountListFaceUp = 0;
        assertEquals(expectedResult,resultCountListFaceUp );
        assertFalse(deck.hasFaceUp(tileToCheck));
                
    }
    
     @Test
    public void testHasFaceUp(){
        Deck deck = new Deck(4);
        Tile tileToCheck = new Tile(9);
        deck.getAllFaceUp().add(tileToCheck);
        int expectedResult = 1;
        int resultCountListFaceUp = 1;
        assertEquals(expectedResult,resultCountListFaceUp );
        assertTrue(deck.hasFaceUp(tileToCheck));
                
    }

}
