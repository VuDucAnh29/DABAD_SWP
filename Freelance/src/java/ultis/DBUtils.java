/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Utech
 */
public class DBUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        
        String databaseName = "FreelancePlatform";
        String user = "admin";
        String password = "DABAD1234567890";
        String url = ("jdbc:sqlserver://it-freelancer.caauhn1cbnja.ap-southeast-1.rds.amazonaws.com:1433;databaseName=" +databaseName);

//        String databaseName = "FoodManagement";
//        String user = "sa";
//        String password = "M!nhduc2102";
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = ("jdbc:sqlserver://localhost:1433;databaseName=" + databaseName);

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
