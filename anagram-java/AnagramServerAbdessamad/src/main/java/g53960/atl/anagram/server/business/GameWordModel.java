
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.db.DBManager;
import g53960.atl.anagram.server.dto.GameWordDto;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.DbException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameWordModel implements GameWordFacade{

    @Override
    public List<GameWordDto> getGameWords() throws BusinessException {
        try {
            DBManager.startTransaction();
            List<GameWordDto> col = GameWordActions.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Liste des Users inaccessible! \n" + msg);
            }
        }
    }

    @Override
    public int addGameWord(int game, String word, String status ) throws BusinessException {
        try {
            DBManager.startTransaction();
            GameWordDto gameWord = new GameWordDto(game,word, status);
            int id = GameWordActions.add(gameWord);
            DBManager.validateTransaction();
            return id;
        } catch (DbException ex) {
            try {
                DBManager.cancelTransaction();
                throw new BusinessException(ex.getMessage());
            } catch (DbException ex1) {
                throw new BusinessException(ex1.getMessage());
            }
        }
    }
}
    
