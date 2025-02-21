package g53960.atl.anagram.server.db;

import g53960.atl.anagram.server.dto.UserDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import g53960.atl.anagram.server.specification.UserSpecification;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlc && @author Samad g53960 
 */
public class UserDB {

    private static final String recordName = "USER";
    
    /**
     *
     * @return
     * @throws DbException
     */
    public static List<UserDto> getAllUsers() throws DtoException, DbException {
        List<UserDto> users = getCollection(new UserSpecification(0));
        return users;
    }

    /**
     *
     * @param sel
     * @return
     * @throws DbException
     */
    public static List<UserDto> getCollection(UserSpecification sel) throws DbException {
        List<UserDto> al = new ArrayList<>();
        try {
            String query = "Select id, login FROM USERS ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " id = ? ";
            }
            
            if (sel.getName() != null && !sel.getName().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " login like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by login";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                
                if (sel.getName() != null && !sel.getName().isEmpty()) {
                    stmt.setString(i, sel.getName() + "%");
                    i++;
                }
            } else {
                query = query + " Order by login";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new UserDto(rs.getInt("id"),rs.getString("login")));
            }
        } catch (DtoException ex) {
            throw new DbException("Instanciation de "+recordName+" impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     *
     * @param id
     * @throws DbException
     */
    public static void deleteDb(int id) throws DbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("delete from USERS where id=" + id);
        } catch (Exception ex) {
            throw new DbException(recordName+": suppression impossible\n" + ex.getMessage());
        }
    }

    /**
     *
     * @param record
     * @throws DbException
     */
    public static void updateDb(UserDto record) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update USERS set "                   
                    + "login=? "
                    + "where id=?";
            update = connexion.prepareStatement(sql);            
            update.setString(1, record.getName());
            update.setInt(2, record.getId());
            update.executeUpdate();
        } catch (Exception ex) {
            throw new DbException(recordName+", modification impossible:\n" + ex.getMessage());
        }
    }

    /**
     *
     * @param record
     * @return
     * @throws DbException
     */
    public static int insertDb(UserDto record) throws DbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.USERS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into USERS(id,login) "
                    + "values(?, ?)");
            insert.setInt(1, num);
            insert.setString(2, record.getName());
            insert.executeUpdate();
            return num;
        } catch (Exception ex) {
            throw new DbException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }

}
