package g53960.atl.anagram.common.message;

import g53960.atl.anagram.common.model.User;
import java.io.Serializable;
/**
 * 
 * @author Samad g53960
 */
public interface Message extends Serializable {

    Type getType();

    User getAuthor();

    User getRecipient();

    Object getContent();

}
