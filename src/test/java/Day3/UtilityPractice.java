package Day3;

import utility.DB_Utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

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

        System.out.println("====================\n");
        // method to get entire row and store it a Map (key-value format)
        Map<String, String> row1Map = new LinkedHashMap<>();
        // so i want to add this:
        //columnNames:                   [           JOB_ID,       JOB_TITLE,          MIN_SALARY,      MAX_SALARY]
        //match the colNames with the row1Info:     AC_ACCOUNT	   Public Accountant	   4200	          9000


        row1Map.put("JOB_ID", "AC_ACCOUNT");
        row1Map.put("JOB_TITLE","Public Accountant" );
        row1Map.put("MIN_SALARY", "4200");
        row1Map.put("MAX_SALARY","9000" );
        System.out.println("row1Map : "+ row1Map);

        // now do above programmatically:
        // create row1map like programmatically

        System.out.println("============= NEW ===========================\n");

        System.out.println("first row of rowMap = "+ DB_Utility.getRowMap(1));



        // if one row can be represented as one map object
        // what data structure is good to store 19 rows of data? - the answer is List of Map

        //get second row and fourthh row and save it to the list of maps:
        Map<String, String> row2Map = DB_Utility.getRowMap(2);
        Map<String, String> row4Map = DB_Utility.getRowMap(4);

        List<Map<String, String>> rowMapList = new ArrayList<>();
        rowMapList.add(row2Map);
        rowMapList.add(row4Map);
        System.out.println(rowMapList);



        DB_Utility.destroy();
    }
}
