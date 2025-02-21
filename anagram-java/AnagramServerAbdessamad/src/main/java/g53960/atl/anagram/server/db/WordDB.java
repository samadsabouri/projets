
package g53960.atl.anagram.server.db;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.exception.DtoException;
import g53960.atl.anagram.server.specification.WordSpecification;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class WordDB {
    
    
    private static final String recordName = "WORD";
    /**
     *
     * @return
     * @throws DbException
     */
    public static List<WordDto> getAllWords() throws DtoException, DbException {
        List<WordDto> words = getCollection(new WordSpecification(0));
        return words;
    }

    /**
     *
     * @param sel
     * @return
     * @throws DbException
     */
    public static List<WordDto> getCollection(WordSpecification sel) throws DbException {
        List<WordDto> al = new ArrayList<>();
        try {
            String query = "Select id, text FROM WORDS ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " id = ? ";
            }
            
            if (sel.getText() != null && !sel.getText().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " text like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by text";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                
                if (sel.getText() != null && !sel.getText().isEmpty()) {
                    stmt.setString(i, sel.getText() + "%");
                    i++;
                }
            } else {
                query = query + " Order by text";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new WordDto(rs.getInt("id"),rs.getString("text")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }
}
