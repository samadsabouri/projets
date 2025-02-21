
package g53960.atl.anagram.server.specification;

/**
 *
 * @author Samad g53960
 */
public class GameSpecification {
    private int id;
    private String user;

    /**
     *
     * @param user
     */
    public GameSpecification(String user) {
        this.id = 0;
        this.user = user;
    }

    /**
     *
     * @param id
     */
    public GameSpecification(int id) {
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
    public String getuser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setName(String user) {
        this.user = user;
    }
}
