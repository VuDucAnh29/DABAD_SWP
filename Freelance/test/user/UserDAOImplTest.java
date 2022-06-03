/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.junit.Test;
import static org.junit.Assert.*;
import ultis.DBUtils;

/**
 *
 * @author Utech
 */
public class UserDAOImplTest {

    public UserDAOImplTest() {
    }

    @Test
    public void testConnection() throws Exception {
        Connection conn = DBUtils.getConnection();
        if (conn == null) {
            fail("Cannot Connect to database");
        }
    }

    @Test
    public void testCreate() throws Exception {
        Connection conn = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                // clear database 
                conn.prepareStatement("DELETE FROM UserAccount").executeUpdate();
                conn.prepareStatement("DBCC CHECKIDENT ([UserAccount], RESEED, 0)").executeUpdate();
                // setting input
                String fullName = "AdminS";
                String roleID = "1";
                String password = "12";
                String Email = "Admin@gmail.com";
                String Avatar = "admin.jpg";
                String CreateDate = "12-12-2000";
                String UpdateDate = "12-12-2022";
                double Balance = 0;

                // Call Create
                UserDTO userDTO = new UserDTO(fullName, roleID, password, Email, Avatar, CreateDate, UpdateDate, Balance);
                UserDAOImpl dao = new UserDAOImpl();
                check = dao.create(userDTO);
                if (!check) {
                    fail("cannot add");
                }
            }
        } catch (Exception e) {
            fail(e.toString());
        } 

    }

    @Test
    public void testGetListUser() throws Exception {
    }

    /**
     * Test of delete method, of class UserDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of update method, of class UserDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of create method, of class UserDAOImpl.
     */
}
