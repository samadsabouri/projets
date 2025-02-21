
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.dto.GameWordDto;
import g53960.atl.anagram.server.exception.BusinessException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public interface GameWordFacade {
    /**
     * Returns a list of all games.
     *
     * @return a list of all games.
     * @throws BusinessException if the query failed.
     */
    List<GameWordDto> getGameWords() throws BusinessException;

    
    /**
     * Creates a user and insert it in the database.Returns the user's id.
     *
     * @param game
     * @param word
     * @param status
     * @return the user's id.
     * @throws g53960.atl.anagram.server.exception.BusinessException
     * @throws
     */
    int addGameWord(int game,String word, String status) throws BusinessException;
}
