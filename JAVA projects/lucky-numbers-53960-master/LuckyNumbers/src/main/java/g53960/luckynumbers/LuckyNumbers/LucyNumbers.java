
package g53960.luckynumbers.LuckyNumbers;

import g53960.luckynumbers.controller.Controller;
import g53960.luckynumbers.model.Game;
import g53960.luckynumbers.model.Model;
import g53960.luckynumbers.view.MyView;

/**
 * Simple main class which allows to run the game.
 * 
 * @author Samad(53960)
 */
public class LucyNumbers {

    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();  
}
}
