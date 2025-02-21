
package g53960.atl.anagram.server.dto;

import g53960.atl.anagram.server.exception.DtoException;

/**
 *
 * @author Samad g53960
 */
public class GameDto extends EntityDto<Integer>{
    /**
     * The text of the word.
     */
    private String user;
    

    /**
     * Constructs the <code> WordDto </code>.
     *
     * @param id ID fo the word.
     * @param user
     */
    public GameDto(Integer id, String user) {
        this.id = id;
        this.user = user;
    }
    
    
    public GameDto(String user) throws DtoException {
        if (user == null) {
            throw new DtoException("l'attribut name est obligatoire");
        }
        this.user = user;

    }

    /**
     * Return the text of the word.
     *
     * @return the text of the word.
     */
    public String getUser() {
        return user;
    }

    /**
     * Set the text of the word.
     *
     * @param text the text of the word.
     */
    public void setUser(String text) {
        this.user = text;
    }

}
