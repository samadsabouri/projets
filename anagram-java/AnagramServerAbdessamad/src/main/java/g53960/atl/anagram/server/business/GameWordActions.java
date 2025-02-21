
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.db.GameWordDB;
import g53960.atl.anagram.server.dto.GameWordDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.specification.GameWordSpecification;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameWordActions {
    /**
     * Insert an user in the database. Returns the user's id.
     *
     * @param name user's name.
     * @return the user's id.
     * @throws SQLException if the query failed.
     */
    static int add(GameWordDto game) throws DbException {
        return GameWordDB.insertDb(game);
    }
    
    
    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws BusinessException if the query failed.
     */
    static List<GameWordDto> findAll() throws DbException {
        GameWordSpecification sel = new GameWordSpecification(0);
        List<GameWordDto> col = GameWordDB.getCollection(sel);
        return col;
    }
}
