package g53960.atl.anagram.client.viewConsole;

import g53960.atl.anagram.client.model.Anagram;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Samad g53960
 */
public class FXMLDocumentController implements Initializable {

    private static final String TITLE = "ANAGRAMME";
    private Stage stage;
    private Anagram anagram;

    private Scene scene;

    @FXML
    TextField hostname;

    @FXML
    TextField name;

    @FXML
    TextField port;

    @FXML
    Label consigne;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hostname.setText("localhost");
        this.hostname.setDisable(true);
        this.port.setText("12345");
        this.port.setDisable(true);
    }

    @FXML
    public void connect() throws Exception {
        String host = hostname.getText().toLowerCase().trim();
        int port1 = 0;
        if (!port.getText().isEmpty()) {
            port1 = Integer.parseInt(port.getText().trim());
        }
        String name1;
        if (name.getText().trim().isEmpty()) {
            name1 = "ANONYM";
        } else {
            name1 = name.getText().toUpperCase().trim();
        }

        String password = "mdp";
        anagram = new Anagram(host, port1, name1);

        //ajouter l'affichage de la vue FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConsoleFXML.fxml"));
        Parent root = loader.load();
        ConsoleFXMLController fxmlDoc = loader.getController();
        AnagramView angramView = new AnagramView(fxmlDoc, anagram);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
