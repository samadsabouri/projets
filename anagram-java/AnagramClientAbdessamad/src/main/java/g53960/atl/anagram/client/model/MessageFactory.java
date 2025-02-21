package g53960.atl.anagram.client.model;

import g53960.atl.anagram.common.message.Message;
import g53960.atl.anagram.common.message.Type;
import static g53960.atl.anagram.common.message.Type.GAMESTATE;
import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.User;
import java.io.IOException;

/**
 *
 * @author Samad g53960
 */
public class MessageFactory {

    static void build(Message message, Anagram anagramClient) throws IOException {
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                anagramClient.setMySelf((User) message.getContent());
                break;
            case GAMESTATE:
                anagramClient.setGameState((GameState) message.getContent());
                anagramClient.notifyObservers();
                break;

            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }
}
