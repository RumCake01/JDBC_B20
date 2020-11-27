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
        jobRS.last();
        int rowCount = jobRS.getRow(); // returns us the total raw number, prints the current row
        System.out.println("row number " + rowCount );

        DB_Utility.destoy();

    }
}
