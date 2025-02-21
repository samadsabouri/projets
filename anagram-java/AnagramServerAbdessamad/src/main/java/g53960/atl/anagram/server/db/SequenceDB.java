package g53960.atl.anagram.server.db;

import g53960.atl.anagram.server.exception.DbException;

/**
 * @author Samad g53960
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 */
public class SequenceDB {

    static final String USERS = "USERS";
    static final String GAME = "GAME";
    static final String WORDS = "WORDS";

    static synchronized int getNextNum(String sequence) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select sValue FROM SEQUENCES where id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("sValue");
                return nvId;
            } else {
                throw new DbException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }

}
