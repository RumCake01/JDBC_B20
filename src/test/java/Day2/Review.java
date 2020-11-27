package Day2;

import java.sql.*;

public class Review {
    public static void main(String[] args) throws SQLException {

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM JOBS");

        // read the first row:
        rs.next();
        System.out.println("first column value in Jobs "+ rs.getString(1));
        System.out.println("first column value in Jobs "+ rs.getString(2));
        System.out.println("========");
        // move to row number 7 and get above 2 columns values:
        rs.absolute(7);

        System.out.println("first column value in Jobs  in row 7 "+ rs.getString(1));
        System.out.println("first column value in Jobs in row 7 "+ rs.getString(2));

        System.out.println("=======");

        rs.last();
        System.out.println("first column value in Jobs  in last row "+ rs.getString(1));
        System.out.println("first column value in Jobs in last row "+ rs.getString(2));

        System.out.println("=====");

        rs.previous();
        System.out.println("first column value in Jobs in 2 row from last  "+ rs.getString(1));
        System.out.println("first column value in Jobs in 2 row from last  "+ rs.getString(2));


        System.out.println("== LOOP FROM TOP TO BUTTOM  PRINT JOB_IX ==");
        rs.beforeFirst();
        while(rs.next()){
            System.out.println("Lopp first column "+ rs.getString("JOB_ID"));
        }

        System.out.println("= loop throw row from last till first row and get min_salary column as number ==");

        // we are currently at after last location

        rs.afterLast(); // to make sure we are at the after last location
        while(rs.previous()){
            System.out.println("Min_Salary Column as a Number  $"+ rs.getDouble("MIN_SALARY"));
        }


        // clean up the connection  , statement and resultSet object after usage
        rs.close();
        statement.close();
        conn.close();
    }


}
