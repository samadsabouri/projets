package g53960.atl.anagram.client.viewConsole;

import g53960.atl.anagram.client.model.Anagram;
import g53960.atl.anagram.common.message.MessageQuit;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samad g53960
 */
public class AnagramView implements Observer {

    private final ConsoleFXMLController fxml;

    private final Anagram anagram;

    public AnagramView(ConsoleFXMLController fxml, Anagram anagram) throws IOException {
        this.fxml = fxml;
        this.anagram = anagram;
        this.fxml.setAnagram(anagram);
        anagram.askForNewGame();
        anagram.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        fxml.setNomJoueur(anagram.getMySelf().getName());
        fxml.setId(anagram.getMySelf().getId());
        if (anagram.getGameState().getNbRemainingsWords() == 0) {
            fxml.setMessage("La partie est fini!\n Mots trouvé(s) : " + anagram.getGameState().getNbSolvedWords() + "\n Mots échoué(s) : "
                    + anagram.getGameState().getNbUnsolvedWords());
            fxml.setDisables();
            fxml.setProposition(anagram.getGameState().getNbProposal());
            fxml.setTotal(anagram.getGameState().getNbRemainingsWords());
            fxml.setTrouve(anagram.getGameState().getNbSolvedWords(), anagram.getGameState().getNbUnsolvedWords());
            try {
                anagram.sendToServer(new MessageQuit(anagram.getMySelf()));
            } catch (IOException ex) {
                Logger.getLogger(AnagramView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fxml.setMot(anagram.getGameState().getAnswer());
            fxml.setProposition(anagram.getGameState().getNbProposal());
            fxml.setTotal(anagram.getGameState().getNbRemainingsWords());
            fxml.setTrouve(anagram.getGameState().getNbSolvedWords(), anagram.getGameState().getNbUnsolvedWords());
            fxml.clearProposition();
        }
    }

}
