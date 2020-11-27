package Day1;

import java.sql.*;

public class LoopingResultSet {
    public static void main(String[] args) throws SQLException {

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM REGIONS");

        // how do i know if my row has ended:

        rs.next();
        // will return boolean value is theres next row -> return true and move the pointer to next row
        // if theres no next row -> returns false

        /*
        while(rs.next()){
            System.out.println("Region_id "+ rs.getString("REGION_ID"));
            System.out.println("Region_name  "+ rs.getString("REGION_NAME"));
        }
        */
        // iterate over all countries
        // we can reuse our existing connection
        // But in order to get a new resultSet we need to run a new query
        // so we can copy/paste the same query again
        // so as long as we have a new query, we will get a new ResultSet object

        rs = statement.executeQuery("SELECT * FROM COUNTRIES");

        while(rs.next() ){
            System.out.print( rs.getString(1) + "\t");
            System.out.print( rs.getString(2)+  "\t");
            System.out.println(rs.getString(3)+ "\t");
        }

        rs.previous();




    }
}
