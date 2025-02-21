package g53960.atl.anagram.server.business;


import g53960.atl.anagram.server.db.DBManager;
import g53960.atl.anagram.server.dto.UserDto;
import g53960.atl.anagram.server.exception.BusinessException;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import g53960.atl.anagram.server.specification.UserSpecification;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Samad g53960
 */
public class UserModel implements AdminFacad {

 
    @Override
    public List<UserDto> getUsers() throws BusinessException {
        try {
            DBManager.startTransaction();
            List<UserDto> col = UserActions.findAll();
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
    public UserDto getUser(int userId) throws BusinessException {
        try {
            DBManager.startTransaction();
            UserDto user = UserActions.findById(userId);
            DBManager.validateTransaction();
            return user;
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
    public UserDto getUser(String userName) throws BusinessException {
        try {
            DBManager.startTransaction();
            UserDto user = UserActions.findByName(userName);
            DBManager.validateTransaction();
            return user;
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

    public static Collection<UserDto> getSelectedUsers(UserSpecification sel) throws BusinessException {
        try {
            DBManager.startTransaction();
            Collection<UserDto> col = UserActions.findBySel(sel);
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
    public int addUser(String userName) throws BusinessException {
        try {
            DBManager.startTransaction();
            UserDto user = new UserDto(userName);
            int id = UserActions.add(user);
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

    @Override
    public void removeUser(UserDto user) throws BusinessException {
        try {
            if (user.isPersistant()) {
                DBManager.startTransaction();
                UserActions.delete(user.getId());
                DBManager.validateTransaction();
            } else {
                throw new BusinessException("User: impossible de supprimer un user inexistant!");
            }
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Suppression de User impossible! \n" + msg);
            }
        }
    }

    @Override
    public void updateUser(UserDto user) throws BusinessException {
        try {
            DBManager.startTransaction();
            UserActions.update(user);
            DBManager.validateTransaction();
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Mise à jour de User impossible! \n" + msg);
            }
        }
    }

    @Override
    public UserDto getRandomUser() throws BusinessException {
        List<UserDto> users = getUsers();
        if (users.isEmpty()) {
            throw new BusinessException("Aucun utilisateur présent");
        }
        Collections.shuffle(users);
        return users.get(0);
    }

}
