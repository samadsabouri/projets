package g53960.thegeniussquare.application;

import g53960.thegeniussquare.controller.Controller;
import g53960.thegeniussquare.model.Game;
import g53960.thegeniussquare.model.Position;
import g53960.thegeniussquare.model.ShapeColor;
import g53960.thegeniussquare.view.SquareBoardView;
import g53960.thegeniussquare.view.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Samad //
 */
//Main pour lancer la view fx ::
public class Main extends Application {

    public static void execute(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        View view = new View(stage);
        Controller controller = new Controller(view, new Game());

        //Play le jeux
        view.setPlayAction((ActionEvent t) -> {
            controller.start();

        });

        //Rotate une shape
        for (ShapeColor color : ShapeColor.values()) {
            if (color != ShapeColor.BLACK && color != ShapeColor.WHITE) {
                view.getShapesView().get(color).setOnMouseClicked((eh -> {
                    if (!controller.getClickPlay() && controller.shapeIsPlaced()) {
                        eh.consume(); //enleve le click
                    } else {
                        controller.rotate(color);
                   }

                }));
            }
        }

        //Les buttton pour placer les shapes 
        view.setBouttonsActions((ActionEvent event) -> {
            Button clickedButton = (Button) event.getSource();
            if (!controller.getClickShape()) {
                controller.setShapeToPlaced(clickedButton.getText());
            }
        });

        //Placer une Shape dans Board
        SquareBoardView[][] rectangles = view.getBoard();
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                Position pos = new Position(finalI, finalJ);
                rectangles[i][j].setOnMouseClicked((eh -> {
                    controller.placeShape(pos);
                }));
            }
        }

        //Undo Command
        view.setUndoAction((ActionEvent t) -> {
            controller.Undo();

        });

        //Redo command
        view.setRedoAction((ActionEvent t) -> {
            controller.Redo();

        });

    }

}
