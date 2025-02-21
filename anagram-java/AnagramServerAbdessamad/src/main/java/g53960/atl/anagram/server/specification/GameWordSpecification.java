
package g53960.atl.anagram.server.specification;

/**
 *
 * @author Samad g53960
 */
public class GameWordSpecification {
    
    private int game;
    private String word;
    private String status; 

    /**
     *
     * @param word
     * @param status
     */
    public GameWordSpecification(String word, String status) {
        this.game= 0;
        this.word = word;
        this.status = status;
    }

    /**
     *
     * @param game
     */
    public GameWordSpecification(int game) {
        this.game = game;
    }

    /**
     *
     * @return
     */
    public int getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(int game) {
        this.game = game;
    }

    /**
     *
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

