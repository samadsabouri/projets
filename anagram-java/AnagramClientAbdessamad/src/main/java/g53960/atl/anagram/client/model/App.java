package g53960.atl.anagram.client.model;

import g53960.atl.anagram.client.viewConsole.FXMLDocumentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samad g53960
 */
public class App extends Application {

    private static final String TITLE = "Client Anagram";
    private Anagram anagram;

    /**
     *
     * @param args
     */
   /* public static void main(String[] args) {
        App.launch(args);
        
    }*/
    
     public static void execute(String[] args) {
        App.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //anagram = new Anagram("localhost", 12345, "g53960", "mdp");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLDocument.fxml"));
            Parent root = loader.load();
            FXMLDocumentController fxmlDoc = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            fxmlDoc.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
            anagram.quit();
        }
    }

}
