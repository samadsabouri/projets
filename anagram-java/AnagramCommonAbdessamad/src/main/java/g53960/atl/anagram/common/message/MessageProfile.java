package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.User;

/**
 *
 * @author Samad g53960
 */
public class MessageProfile implements Message {

    private final User player;

    public MessageProfile(User player) {
        this.player = player;
    }

    public MessageProfile(int id, String name) {
        player = new User(id, name);
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
        return Type.PROFILE;
    }

    @Override
    public Object getContent() {
        return player;
    }

}
