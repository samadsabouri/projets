package g53960.atl.anagram.client.viewConsole;

import g53960.atl.anagram.client.model.Anagram;
import g53960.atl.anagram.common.message.MessagePass;
import g53960.atl.anagram.common.message.MessageQuit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Samad g53960
 */
public class ConsoleFXMLController implements Initializable {

    Anagram anagram;

    @FXML
    private Button buttonCheck;

    @FXML
    private TextArea messageView;

    @FXML
    private MenuItem passerMenuItem;

    @FXML
    private TextField proposition;

    @FXML
    private MenuItem quitterMenuItem;

    @FXML
    private Text total;

    @FXML
    private Text trouve;

    @FXML
    private Text rate;

    @FXML
    private Text mot;

    @FXML
    private Text message;

    @FXML
    private Text nbpropo;

    @FXML
    private Text id;

    @FXML
    private Text nomJoueur;

    @FXML
    void check(ActionEvent event) throws IOException {
        if (proposition.getText().trim().isEmpty()) {
            setMessage("Veuillez entrer une proposition correcte !!!");
        } else {
            anagram.sendCheck(anagram.getMySelf(), proposition.getText());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setEnterAction();
    }

    public void setDisables() {
        this.buttonCheck.setDisable(true);
        this.proposition.setDisable(true);
        this.passerMenuItem.setDisable(true);
    }

    public void setEnterAction() {
        proposition.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        check(null);
                        proposition.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void quitPartie() throws IOException {
        anagram.sendToServer(new MessageQuit(anagram.getMySelf()));
        System.exit(0);
    }

    public void passPartie() throws IOException {
        setMessage("La reponse était \n " + anagram.getGameState().getWordShuffle().getText());
        anagram.sendToServer(new MessagePass(anagram.getMySelf()));
    }

    public void setAnagram(Anagram anagram) {
        this.anagram = anagram;
    }

    public void setTotal(int total) {
        this.total.setText("" + total);
    }

    public void setProposition(int proposition) {
        this.nbpropo.setText(proposition + " propositions");
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setRate(int rate) {
        this.rate.setText("Vous vous êtes tromper sur:  " + rate + " mots");
    }

    public void setTrouve(int trouve, int rate) {
        this.trouve.setText(trouve + "                                  " + rate);
    }

    public void setMot(String mot) {
        this.mot.setText(mot);
    }

    public void setId(int id) {
        this.id.setText("" + id);
    }

    public void setNomJoueur(String nom) {
        this.nomJoueur.setText(nom);
    }
    
    public void clearProposition(){
        proposition.clear();
    }

}
