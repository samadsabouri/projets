package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.User;

/**
 *
 * @author Samad g53960
 */
public class MessagePass implements Message {

    private final User player;

    public MessagePass(User player) {
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
        return Type.PASS;
    }

    @Override
    public Object getContent() {
        return player;
    }

}
