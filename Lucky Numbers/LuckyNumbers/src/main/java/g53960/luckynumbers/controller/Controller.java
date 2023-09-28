package g53960.luckynumbers.controller;

import g53960.luckynumbers.model.Model;
import g53960.luckynumbers.model.Position;
import g53960.luckynumbers.model.Tile;
import g53960.luckynumbers.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Controler which related the view and the model.
 *
 * @author Samad(53960)
 */
public class Controller {

    private Model game;
    private View view;
    private List<Integer> AllBoardsTilesPutted = new ArrayList<>();
    /**
     * Simple constructor of controller.
     *
     * @param game the given model.
     * @param view the given view.
     */
    public Controller(Model game, View view) {
        this.game = game;
        this.view = view;
    }

    public void play() {
        Position pos;
        int playerCount = 0;
        view.displayWelcome();
        while (true) {
            switch (game.getState()) {
                case NOT_STARTED:
                    playerCount = view.askPlayerCount();
                    game.start(playerCount);
                    break;
                case PICK_TILE:
                   
                    while( this.AllBoardsTilesPutted.isEmpty() || this.AllBoardsTilesPutted.size()<playerCount){
                        this.game.putDiagonalRandomTile();
                        this.game.nextPlayer();
                        AllBoardsTilesPutted.add(1);
                    }

                    view.displayCurrentPlayer();
                    view.displayGame();
                    view.DisplayTilesToChoice(game.faceDownTileCount(), game.getAllfaceUpTiles());
                    if (game.faceUpTileCount() == 0) {
                        this.game.pickFaceDownTile();
                    } else {
                        int choice = view.askChoiceToPickTile();
                        if (choice == 0) {
                            this.game.pickFaceDownTile();
                        } else {
                            Tile tileToPick;
                            tileToPick = new Tile(view.askTileValueToPick());
                            this.game.pickFaceUpTile(tileToPick);
                        }
                    }

                    break;
                case PLACE_TILE:
                    view.displayPickedTile();
                    view.displayGame();
                    boolean checked = false;
                    while (!checked) {
                        try {
                            pos = view.askPosition();
                            game.putTile(pos);
                            checked = true;
                        } catch (Exception e) {
                            view.displayError("your position does not respect the rule of the game !");
                        }
                    }
                    view.displayCurrentPlayer();
                    view.displayGame();
                    view.DisplayTilesToChoice(game.faceDownTileCount(), game.getAllfaceUpTiles());
                    break;
                case PLACE_OR_DROP_TILE:
                    view.displayPickedTile();
                    view.displayGame();
                    int choiceDrop = this.view.askDropTile();
                    if (choiceDrop == 0) {
                        this.game.dropTile();
                        break;
                    } else {
                        game.ChangeToPLACE_TILEState();
                    }
                    break;
                case GAME_OVER:
                    view.displayWinners();
                    if (view.askReplayGame() == 0) {
                        System.exit(0);
                    } else {
                        play();
                    }
                    break;
                case TURN_END:
                    game.nextPlayer();
                    break;

            }
        }
    }

}
