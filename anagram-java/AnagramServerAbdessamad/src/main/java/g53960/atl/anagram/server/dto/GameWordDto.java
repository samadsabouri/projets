
package g53960.atl.anagram.server.dto;

import g53960.atl.anagram.server.exception.DtoException;

/**
 *
 * @author Samad g53960
 */
public class GameWordDto {
    /**
     * The text of the word.
     */
    private int game; 
    private String word ; 
    private String status;
    

    /**
     * Constructs the <code> WordDto </code>.
     *
     * @param game
     * @param word
     * @param status
     */
    public GameWordDto(Integer game, String word, String status ) {
        this.game= game;
        this.word = word;
        this.status= status; 
    }
    
    
    public GameWordDto(String word, String status) throws DtoException {
        if (word == null) {
            throw new DtoException("l'attribut word est obligatoire");
        }
        this.word = word;
        this.status = status; 

    }

    /**
     * Return the text of the word.
     *
     * @return the text of the word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Set the text of the word.
     * 
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    public int getGame(){
        return game;
    }

}
