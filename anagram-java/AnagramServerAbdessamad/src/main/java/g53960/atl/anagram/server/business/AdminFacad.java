package g53960.atl.anagram.server.business;


import g53960.atl.anagram.server.dto.UserDto;
import g53960.atl.anagram.server.exception.BusinessException;
import java.util.List;

/**
 * 
 * @author Samad g53960
 */
public interface AdminFacad {

    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws BusinessException if the query failed.
     */
    List<UserDto> getUsers() throws BusinessException;

    /**
     * Returns the unique user with the given id.
     *
     * @param id user's id.
     * @return the unique user with the given id.
     * @throws 
     */
    UserDto getUser(int id) throws BusinessException;

    /**
     * Returns the last user with the given name.
     *
     * @param name user's name.
     * @return the last user with the given name.
     * @throws 
     * @throws SQLException if the query failed.
     */
    UserDto getUser(String name) throws BusinessException;

    /**
     * Creates a user and insert it in the database.Returns the user's id.
     *
     * @param name user's name.
     * @return the user's id.
     * @throws 
     * @throws SQLException if the query failed.
     */
    int addUser(String name) throws BusinessException;

    /**
     * Removes the given user.
     *
     * @param user user to delete.
     * @throws 
     * @throws SQLException if the query failed.
     */
    void removeUser(UserDto user) throws BusinessException;

    /**
     * Updates the given user.
     *
     * @param current
     * @param user user to update.
     * @throws 
     * @throws SQLException if the query failed.
     */
    void updateUser(UserDto current) throws BusinessException;

    /**
     * Returns a random user from the database.
     *
     * @return a random user from the database.
     * @throws 
     */
    UserDto getRandomUser() throws BusinessException;

}
