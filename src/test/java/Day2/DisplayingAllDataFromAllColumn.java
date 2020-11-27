package Day2;

import java.sql.*;

public class DisplayingAllDataFromAllColumn {

    public static void main(String[] args) throws SQLException {

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEES");


        // print out entire first row of Employee Table from above query
        ResultSetMetaData  rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        System.out.println("all columns " + columnCount);

        // print out column name in the beginning row, then print first row
        for (int colNum = 1; colNum <=columnCount ; colNum++) {
            System.out.print(rsmd.getColumnLabel(colNum) + "\t");
        }
        System.out.println("====================================");

        // this whole loop below is getting ys one row of data
        rs.next();
        for (int colNum = 1; colNum <=columnCount ; colNum++) {
            System.out.print(rs.getString(colNum) + "\t");
        }

        System.out.println(rs.getString(1));



        //  we can get the whole row , if we know how to get one row?
        // first move the cursor to the first row and print all coluumns
        rs.beforeFirst();
        while(rs.next()){

            for (int colNum = 1; colNum <=columnCount ; colNum++) {
                System.out.print(rs.getString(colNum) + "     \t");
            }
            System.out.println();
        }

        rs.close();
        statement.close();
        conn.close();
    }
}