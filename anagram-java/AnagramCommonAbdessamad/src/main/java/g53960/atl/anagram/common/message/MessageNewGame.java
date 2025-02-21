package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.User;

/**
 *
 * @author Samad g53960
 */
public class MessageNewGame implements Message {

    private final User player;

    public MessageNewGame(User player) {
        this.player = player;
    }

    @Override
    public User getAuthor() {
        return player;
    }

    @Override
    public User getRecipient() {
        return User.ADMIN;
    }

    @Override
    public Type getType() {
        return Type.NEWGAME;
    }

    @Override
    public Object getContent() {
        return null;
    }

}
