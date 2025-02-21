 
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.server.exception.BusinessException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public interface WordFacade {
    /**
     * Returns a list of all words.
     *
     * @return a list of all words.
     * @throws BusinessException if the query failed.
     */
    List<WordDto> getWords() throws BusinessException;


}
