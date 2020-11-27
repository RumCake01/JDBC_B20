package Day2;

import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {
    public static void main(String[] args) throws SQLException {

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM JOBS");

    }
}
