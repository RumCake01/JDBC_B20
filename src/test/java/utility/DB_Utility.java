package utility;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            columnCount= rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error while counting the columns "+ e.getMessage());

        }
        return columnCount;

    }

    // a method that returns all the column names as List <String>
    public static List<String> getColumnNames(){
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
            System.out.println("Error while getting column names "+ e.getMessage());
        }
        return columnList;


    }




}
