package Day3;

import utility.DB_Utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilityPractice {

    public static void main(String[] args) throws SQLException {

        // test out all methods we created this far:

        // step 1 . create connection

        DB_Utility.createConnection();

        // step 2. run some query
        // store our restul into semething
        ResultSet jobRS =  DB_Utility.runQuery("Select * from JOBS");

        // get row count of ResultSet:
        // we can move the pointer to the last row and get the row number

        int rowCount =  DB_Utility.getRowCount() ;
        System.out.println("rowCount = " + rowCount); // returns the total row count

        int colCount = DB_Utility.getColumnCount() ;
        System.out.println("colCount = " + colCount); // returns the total column count

        System.out.println("All Column Names: " + DB_Utility.getColumnNames());

        System.out.println("All data from all rows "+DB_Utility.getRowDataAsList(1));


        System.out.println("Data at specific row, from the specific column "+ DB_Utility.getColumnDataAtRow(2, 2));

        DB_Utility.destroy();

    }
}
