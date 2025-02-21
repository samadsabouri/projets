
package g53960.atl.anagram.server.db;


import g53960.atl.anagram.server.dto.GameWordDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import g53960.atl.anagram.server.specification.GameWordSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class GameWordDB {
    
    
    private static final String recordName = "GAMEWORD";
    /**
     *
     * @return
     * @throws g53960.atl.anagram.server.exception.DtoException
     * @throws DbException
     */
    public static List<GameWordDto> getAllWords() throws DtoException, DbException {
        List<GameWordDto> games = getCollection(new GameWordSpecification(0));
        return games;
    }

    /**
     *
     * @param sel
     * @return
     * @throws DbException
     */
    public static List<GameWordDto> getCollection(GameWordSpecification sel) throws DbException {
        List<GameWordDto> al = new ArrayList<>();
        try {
            String query = "Select game, word, status FROM GAMEWORD ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getGame()!= 0) {
                where = where + " game = ? ";
            }
            
            if (sel.getWord()!= null && !sel.getWord().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " text like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by game";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getGame() != 0) {
                    stmt.setInt(i, sel.getGame());
                    i++;

                }
                
                if (sel.getWord() != null && !sel.getWord().isEmpty()) {
                    stmt.setString(i, sel.getWord() + "%");
                    i++;
                }
            } else {
                query = query + " Order by game";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new GameWordDto(rs.getInt("game"),rs.getString("word"),rs.getString("status") ));
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
    public static int insertDb(GameWordDto record) throws DbException {
        try {
            int num =1;
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into GameWord(game,word, status) "
                    + "values(?, ?, ?)");
            insert.setInt(1, record.getGame());
            insert.setString(2, record.getWord());
            insert.setString(3, record.getStatus());
            insert.executeUpdate();
            return num;
        } catch (DbException | SQLException ex) {
            throw new DbException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }
}
