
package g53960.thegeniussquare.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samad
 */
public class BoardTest {
    


    @Test
    public void testGetSquares() {
        System.out.println("getSquares");
                Position[] blockerPositions = {
                                       new Position(0,0), 
                                       new Position(0,1),
                                       new Position(2,2),
                                       new Position(4,0),
                                       new Position(0,3),
                                       new Position(4,3),
                                       new Position(5,0)};
        Board instance = new Board(blockerPositions);
        
            ShapeComponent[][] expResult = new ShapeComponent[6][6];
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            if(i==0 && (j==1|| j ==0 || j==3 ) ){
                      expResult[i][j] = new ShapeComponent(new Position(i, j), ShapeType.BLOCKER, ShapeColor.BLACK); 
            }else if(i==2 && j==2 ){
                                  expResult[i][j] = new ShapeComponent(new Position(i, j), ShapeType.BLOCKER, ShapeColor.BLACK); 
            }else if(i==4 && (j==0 || j==3) ){
                                  expResult[i][j] = new ShapeComponent(new Position(i, j), ShapeType.BLOCKER, ShapeColor.BLACK); 
            }else if(i==5 && j==0  ){
                                  expResult[i][j] = new ShapeComponent(new Position(i, j), ShapeType.BLOCKER, ShapeColor.BLACK); 
            }else{
            expResult[i][j] = new ShapeComponent(new Position(i, j), ShapeType.NEUTRAL, ShapeColor.WHITE);
            }
        }
    }
        ShapeComponent[][] result = instance.getSquares();
        assertArrayEquals(expResult, result);
    }
    
}
