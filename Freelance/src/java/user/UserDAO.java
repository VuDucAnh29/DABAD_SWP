/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Utech
 */
public interface UserDAO {

    public UserDTO checkLogin(String email, String password) throws SQLException;
//   CRUD

    public List<UserDTO> getListUser(String search) throws SQLException;

    public boolean delete(String userID) throws SQLException;

    public boolean update(UserDTO user) throws SQLException;

    public boolean create(UserDTO user) throws SQLException;
}
