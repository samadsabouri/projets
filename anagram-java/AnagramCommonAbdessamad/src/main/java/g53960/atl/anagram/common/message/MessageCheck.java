package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.User;
import g53960.atl.anagram.common.model.Word;

/**
 *
 * @author Samad g53960
 */
public class MessageCheck implements Message {

    private final User player;
    private final Word word;

    public MessageCheck(User player, Word word) {
        this.player = player;
        this.word = word;
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
        return Type.CHECK;
    }

    @Override
    public Object getContent() {
        return word; //à faire encore la classe word
    }

}
