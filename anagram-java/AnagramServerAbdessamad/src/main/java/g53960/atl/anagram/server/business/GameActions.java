
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.db.GameDB;
import g53960.atl.anagram.server.dto.GameDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.specification.GameSpecification;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameActions {
    
    /**
     * Insert an user in the database. Returns the user's id.
     *
     * @param name user's name.
     * @return the user's id.
     * @throws SQLException if the query failed.
     */
    static int add(GameDto game, String time) throws DbException {
        return GameDB.insertDb(game, time);
    }
    
    
    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws BusinessException if the query failed.
     */
    static List<GameDto> findAll() throws DbException {
        GameSpecification sel = new GameSpecification(0);
        List<GameDto> col = GameDB.getCollection(sel);
        return col;
    }
}
