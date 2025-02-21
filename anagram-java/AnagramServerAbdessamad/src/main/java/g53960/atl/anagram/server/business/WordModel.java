
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.server.db.DBManager;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.DbException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class WordModel implements WordFacade{

    @Override
    public List<WordDto> getWords() throws BusinessException {
        try {
            DBManager.startTransaction();
            List<WordDto> col = WordActions.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Liste des Words inaccessible! \n" + msg);
            }
        }
    }
    
}
