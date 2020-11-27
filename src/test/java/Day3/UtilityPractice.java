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


        System.out.println("I am getting the Cell Value at row 2 from column 4:  "+ DB_Utility.getColumnDataAtRow(2, 4));


        System.out.println("I am getting the Cell Value at row 2 from column Min_Salary:  "+ DB_Utility.getColumnDataAtRow(2, "MIN_SALARY"));

        System.out.println("Get Cell value at row 5 col JOB_TITLE " + DB_Utility.getColumnDataAtRow(2, "JOB_TITLE") );

        System.out.println("3rd Column Value:  "+ DB_Utility.getColumnDataAsList(3));

        System.out.println("JOB_title column value: " +DB_Utility.getColumnDataAsList("JOB_TITLE"));

        System.out.println("=======NEW==================");

        DB_Utility.displayAllData();


        DB_Utility.destroy();

    }
}
