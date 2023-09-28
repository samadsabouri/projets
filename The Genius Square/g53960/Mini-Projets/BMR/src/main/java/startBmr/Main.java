package startBmr;

import controlleur.Controlleur;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Model;
import view.View;

/**
 * * The main class that starts the BMR calculator application. This class
 * extends javafx.application.Application and sets up the view and controller
 * objects and connects the view's "calculate" and "clear" buttons to the
 * corresponding methods in the controller.
 *
 * @author Samad
 */
public class Main extends Application {

    public static void execute(String[] args) {
        launch(args);
    }

    /**
     *
     * The start method that sets up the view and controller objects and
     * connects the view's "calculate" and "clear" buttons to the corresponding
     * methods in the controller.
     *
     * @param stage The main stage of the application
     * @throws Exception if an error occurs during application startup
     */
    @Override
    public void start(Stage stage) throws Exception {
        View view = new View(stage);
        Controlleur controlleur = new Controlleur(new Model(), view);
        controlleur.start();

        view.setCalculateAction((ActionEvent t) -> {
            controlleur.calculate();
        });

        view.setClearButtonAction((ActionEvent t) -> {
            controlleur.clearDataResult();
        });
    }

}
