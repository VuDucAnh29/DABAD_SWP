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
        String user = "sa";
        String password = "M!nhduc2102";
        String url = ("jdbc:sqlserver://13.212.197.27:1433;databaseName=" + databaseName);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
