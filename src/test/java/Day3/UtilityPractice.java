package Day3;

import utility.DB_Utility;

import java.sql.ResultSet;

public class UtilityPractice {

    public static void main(String[] args) {

        // test out all methods we created this far:

        // step 1 . create connection

        DB_Utility.createConnection();

        // step 2. run some query
        // store our restul into semething
        ResultSet jobRS =  DB_Utility.runQuery("Select * from JOBS");



    }
}
