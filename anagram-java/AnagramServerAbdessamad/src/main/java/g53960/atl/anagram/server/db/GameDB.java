
package g53960.atl.anagram.server.db;

import g53960.atl.anagram.server.dto.GameDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import g53960.atl.anagram.server.specification.GameSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameDB {
    
    private static final String recordName = "GAME";
    /**
     *
     * @return
     * @throws g53960.atl.anagram.server.exception.DtoException
     * @throws DbException
     */
    public static List<GameDto> getAllWords() throws DtoException, DbException {
        List<GameDto> games = getCollection(new GameSpecification(0));
        return games;
    }

    /**
     *
     * @param sel
     * @return
     * @throws DbException
     */
    public static List<GameDto> getCollection(GameSpecification sel) throws DbException {
        List<GameDto> al = new ArrayList<>();
        try {
            String query = "Select id, user FROM GAME ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " id = ? ";
            }
            
            if (sel.getuser()!= null && !sel.getuser().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " text like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by id";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                
                if (sel.getuser() != null && !sel.getuser().isEmpty()) {
                    stmt.setString(i, sel.getuser() + "%");
                    i++;
                }
            } else {
                query = query + " Order by id";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new GameDto(rs.getInt("id"),rs.getString("user")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }
    
    
    /**
     *
     * @param record
     * @return
     * @throws DbException
     */
    public static int insertDb(GameDto record, String time) throws DbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.GAME);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Game(id,user,timeStart) "
                    + "values(?, ?, ?)");
            insert.setInt(1, num);
            insert.setString(2, record.getUser());
            insert.setString(3, time);
            insert.executeUpdate();
            return num;
        } catch (DbException | SQLException ex) {
            throw new DbException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }
}
