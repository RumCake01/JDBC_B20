package Day2;

import utility.DB_Utility;

import java.sql.*;

public class DisplayingAllDataFromAllColumn {

    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM EMPLOYEES");

        // print out entire first row of Employee Table from above query
        ResultSetMetaData  rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        System.out.println("all columns " + columnCount);

        // print out column name in the beginning row, then print first row
        for (int colNum = 1; colNum <=columnCount ; colNum++) {
            System.out.print(rsmd.getColumnLabel(colNum) + "\t");
        }
        System.out.println("====================================");

        // this whole loop below is getting us one row of data
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
        DB_Utility.destoy();
    }
}