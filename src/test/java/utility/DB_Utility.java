package utility;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DB_Utility {

    static Connection conn;  // this is made static so we can reuse it in every method we write
    // the benefit is we can use it later directly in our ResultSet method
    static Statement statement;

    static ResultSet rs;


    public static void createConnection() { // this method will test if our connection is alive or not

        // now create connection:

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connection, username, password);
            System.out.println("CONNECTION SUCCESSFUL !! ");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED !!! " + e.getMessage());
        }
    }

    // now we create a method to get us ResultSet, run query
    // create method called runQuery and return result Object

    public static ResultSet runQuery(String query) { // this is where our code runs, most important port
        // if we want a variable to be acsessible in every method, where do we put it? --> We must make it a static variable

        // now, we are reusing the connection build from previous method
        //  ResultSet rs = null;
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Error while getting resultset " + e.getMessage());
        }
        return rs;
    }

    // create method to clean up all the connection statement and result set
    public static void destroy() {
        try {
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // this method below counts how many rows we have
    // returns the row count of the resultSet we got
    public static int getRowCount() {
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow(); // returns us row count

            // after we finished getting our row count , we need to return our cursor back to beforeFirst location
            // to avoid accident
            rs.beforeFirst();

        } catch (SQLException e) {

            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }

        return rowCount;
    }

    // another method to get the column count:
    public static int getColumnCount() {

        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error while counting the columns " + e.getMessage());

        }
        return columnCount;

    }

    // a method that returns all the column names as List <String>
    public static List<String> getColumnNames() {
        // whenever we want to return something, it is better if we create a variable of that type, create an object so that we can return

        // 1/ Create a list
        List<String> columnList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                // to get the column names, we created a separate variable:
                String columnName = rsmd.getColumnLabel(colNum);
                columnList.add(columnName);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting column names " + e.getMessage());
        }
        return columnList;
    }


    // method that will return all row data as  List<String>
    // we added int rowNumber parameter so we can enter the row number that we wan to tget info about:

    public static List<String> getRowDataAsList(int rowNumber) {

        List<String> rowDataList = new ArrayList<>();

        // 1. first we need to move the pointer to the specific rowNumber
        try {
            rs.absolute(rowNumber);
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                String cellValue = rs.getString(colNum); // this returns us a value

                // now we need to store it as a list
                rowDataList.add(cellValue);
            }
            rs.beforeFirst(); // alwats remember to return the pointer to the beforefirst

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA AS LIST " + e.getMessage());
        }
        return rowDataList;
    }

    // method to return the cell value at certain row certain column, so the method will take 2 parameters:
    // @param rowNum
    // @parem colNum
    // return Cell value as a String

    public static String getColumnDataAtRow(int rowNum, int colNum) {

        String result = "";
        try {
            rs.absolute(rowNum);
            result = rs.getString(colNum);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting cell valye ar RoNum ColNum" + e.getMessage());
        }
        return result;
    }


    // method to return the cell value at certain row certain column, so the method will take 2 parameters:
    // @param rowNum row Number
    // @parem colName column Name
    // return Cell value as a String


    // this below is the overloaded method, the same name but different parameter.
    public static String getColumnDataAtRow(int rowNum, String colName) {

        String result = "";
        try {
            rs.absolute(rowNum);
            result = rs.getString(colName);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting cell valye ar RoNum ColNum" + e.getMessage());
        }
        return result;
    }

    // return the value of all cell in that column
    // @param  colNum the column number you want to get the list out of
    // @return value of all cells in that column as a List<String>

    public static List<String> getColumnDataAsList(int colNum) {
        List<String> valueOfAllCells = new ArrayList<>();
        // now go through each and every row and get the value:

        try {
            while (rs.next()) { // this is how we can go to each and every row
                String cellvalue = rs.getString(colNum);
                valueOfAllCells.add(cellvalue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error message while getting one column data as list: " + e.getMessage());
        }
        return valueOfAllCells;
    }

    // below is overloaded method with same name but diff param
    // return the value of all cell in that column using columnName
    // @param  colName the column number you want to get the list out of
    // @return value of all cells in that column as a List<String>

    public static List<String> getColumnDataAsList(String colName) {
        List<String> valueOfAllCells = new ArrayList<>();
        // now go through each and every row and get the value:

        try {
            while (rs.next()) { // this is how we can go to each and every row
                String cellvalue = rs.getString(colName);
                valueOfAllCells.add(cellvalue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error message while getting one column data as list: " + e.getMessage());
        }
        return valueOfAllCells;
    }

    // method to display all the results set data on the console

    public static void displayAllData() {

        try {
            rs.beforeFirst();
            while (rs.next()) {
                for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                    // System.out.print(rs.getString(colNum) + "     \t");
                    // for making the table print out pretty:
                    System.out.printf("%-35s", rs.getString(colNum));

                }
                System.out.println();
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Errors while printing the whole table " + e.getMessage());
        }
    }

    // method that returns the rowData along with column name as Map object
    // @param rowNum rowNumber we want  to get the data
    // return Map object - column name as key, and cell value as value

    public static Map<String, String> getRowMap(int rowNum) {


        Map<String, String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);
            ResultSetMetaData rsmd = rs.getMetaData();


            for (int colNum = 1; colNum <= rsmd.getColumnCount(); colNum++) {

                String columnName = rsmd.getColumnLabel(colNum);
                String cellValue = rs.getString(colNum);
                ;
                rowMap.put(columnName, cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getting ROWMAP " + e.getMessage());
        }
        return rowMap;

    }


    public static List<Map<String, String>> getRowMapList() {

        List<Map<String, String>> rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount(); rowNum++) {

            rowMapList.add(getRowMap(rowNum));

        }
        return rowMapList;

    }
}