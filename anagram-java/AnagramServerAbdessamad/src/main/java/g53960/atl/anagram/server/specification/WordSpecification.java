
package g53960.atl.anagram.server.specification;

/**
 *
 * @author Samad g53960
 */
public class WordSpecification {

    private int id;
    private String text;

    /**
     *
     * @param text
     */
    public WordSpecification(String text) {
        this.id = 0;
        this.text = text;
    }

    /**
     *
     * @param id
     */
    public WordSpecification(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    
    public void setText(String text) {
        this.text = text;
    }

}


