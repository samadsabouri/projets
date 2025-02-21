
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.dto.GameDto;
import g53960.atl.anagram.server.exception.BusinessException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public interface GameFacade {
    /**
     * Returns a list of all games.
     *
     * @return a list of all games.
     * @throws BusinessException if the query failed.
     */
    List<GameDto> getGames() throws BusinessException;

    
    /**
     * Creates a user and insert it in the database.Returns the user's id.
     *
     * @param user user's name.
     * @return the user's id.
     * @throws g53960.atl.anagram.server.exception.BusinessException
     * @throws
     */
    int addGame(String user, String time) throws BusinessException;

   
}
