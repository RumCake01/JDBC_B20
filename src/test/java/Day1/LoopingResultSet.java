package Day1;

import java.sql.*;

public class LoopingResultSet {
    public static void main(String[] args) throws SQLException {

        String connection = "jbdc:oracle:thin:54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM REGIONS");



    }
}
