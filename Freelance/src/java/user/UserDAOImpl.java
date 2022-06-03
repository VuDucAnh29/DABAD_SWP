/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ultis.DBUtils;

/**
 *
 * @author Utech
 */
public class UserDAOImpl implements UserDAO {
    
    private static final String CREATE = "INSERT INTO UserAccount ([Email], [Password], [FullName], [Avatar], [RoleID], [CreateDate], [UpdateDate], [Balance], [Status]) Values (?,?,?,?,?,?,?,?,1)";
    private static final String LOGIN = "SELECT fullName, roleID FROM UserAccount WHERE email=? AND password=?";

    @Override
    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(roleID, fullName, roleID, password, email, email, CREATE, CREATE, 0);
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    @Override
    public List<UserDTO> getListUser(String search) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String userID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserDTO user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getEmail());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getFullName());
                ptm.setString(4, user.getAvatar());
                ptm.setString(5, user.getRoleID());
                ptm.setString(6, user.getCreateDate());
                ptm.setString(7, user.getUpdateDate());
                ptm.setDouble(8, 0);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
