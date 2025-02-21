package g53960.atl.anagram.client.model;

import g53960.atl.anagram.client.client.AbstractClient;
import g53960.atl.anagram.common.message.Message;
import g53960.atl.anagram.common.message.MessageCheck;
import g53960.atl.anagram.common.message.MessageNewGame;
import g53960.atl.anagram.common.message.MessageProfile;
import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.User;
import g53960.atl.anagram.common.model.Word;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samad g53960
 */
public class Anagram extends AbstractClient {

    private User mySelf;
    private GameState gameState;

    public Anagram(String host, int port, String name) throws IOException {
        super(host, port);
        openConnection();
        updateName(name);

    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        try {
            MessageFactory.build(message, this);
        } catch (IOException ex) {
            Logger.getLogger(Anagram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void quit() throws IOException {
        closeConnection();
    }

    void showMessage(Message message) {
        notifyChange(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new MessageProfile(0, name));
    }

    public void askForNewGame() throws IOException {
        sendToServer(new MessageNewGame(getMySelf()));
    }

    public void sendCheck(User user, String propose) throws IOException {
        sendToServer(new MessageCheck(user, new Word(propose)));
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    private void notifyChange(Message message) {
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void connectionEstablished() {
        System.out.println("Client connecté");
    }

    public User getMySelf() {
        return mySelf;
    }

    void setMySelf(User user) {
        this.mySelf = user;
    }

    public GameState getGameState() {
        return gameState;
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyChange();
    }

}
