package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.GameState;
import g53960.atl.anagram.common.model.User;

/**
 *
 * @author Samad g53960
 */
public class MessageGameState implements Message {


    private final User player;
    private final GameState gameState;

    public MessageGameState(User player, GameState gameState) {
        this.player = player;
        this.gameState = gameState;
    }

    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    @Override
    public User getRecipient() {
        return player;
    }

    @Override
    public Type getType() {
        return Type.GAMESTATE;
    }

    @Override
    public Object getContent() {
        return gameState;
    }

}
