
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.server.db.DBManager;
import g53960.atl.anagram.server.dto.GameDto;
import g53960.atl.anagram.server.dto.UserDto;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameModel implements GameFacade{

    @Override
    public List<GameDto> getGames() throws BusinessException {
         try {
            DBManager.startTransaction();
            List<GameDto> col = GameActions.findAll();
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
    public int addGame(String user, String time) throws BusinessException {
        try {
            DBManager.startTransaction();
            GameDto game = new GameDto(user);
            int id = GameActions.add(game, time);
            DBManager.validateTransaction();
            return id;
        } catch (DbException | DtoException ex) {
            try {
                DBManager.cancelTransaction();
                throw new BusinessException(ex.getMessage());
            } catch (DbException ex1) {
                throw new BusinessException(ex1.getMessage());
            }
        }
    }
    
}
