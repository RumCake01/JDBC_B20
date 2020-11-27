package Day1;

import java.sql.*;

public class MovingResultSetPointer {
    public static void main(String[] args) throws SQLException {


        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);


        // this way of creating a statement will give us ability to generate ResultSet that can move forward and backwards anytime
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("SELECT * FROM REGIONS");

        while(rs.next()){
            System.out.println("Region_Name"+ rs.getString("REGION_NAME"));
        }
        System.out.println("=====");
        while(rs.previous()) {
            System.out.println("Region_Name" + rs.getString("REGION_NAME"));
        }

        // other resultSet methods for moving our pinter to specicic location:

        rs.first(); //first row
        rs.last();// last row
        rs.isBeforeFirst(); // before first location
        rs.afterLast(); // after last location
        rs.absolute(3); // move to specific row

        // how to find out at which row the pointer is right now:
        int currentRowNum = rs.getRow();

        // how to find the row count?
        //is to move the pointer to the last row and then print the current row:
        System.out.println("====");
        rs.last();
        System.out.println("Row count " +currentRowNum);

    }
}
