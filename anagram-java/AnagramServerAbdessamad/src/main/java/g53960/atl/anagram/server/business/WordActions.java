  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g53960.atl.anagram.server.business;

import g53960.atl.anagram.common.dto.WordDto;
import g53960.atl.anagram.server.db.WordDB;
import g53960.atl.anagram.server.exception.DbException;
import g53960.atl.anagram.server.specification.WordSpecification;
import java.util.List;

/**
 *
 * @author Samad g53960
 */
public class WordActions {
    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws BusinessException if the query failed.
     */
    static List<WordDto> findAll() throws DbException {
        WordSpecification sel = new WordSpecification(0);
        List<WordDto> col = WordDB.getCollection(sel);
        return col;
    }
}
