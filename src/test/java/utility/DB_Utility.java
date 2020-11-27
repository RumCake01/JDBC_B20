package utility;
import java.sql.*;

public class DB_Utility {

    static Connection conn;  // this is made static so we can reuse it in every method we write
    // the benefit is we can use it later directly in our ResultSet method
    static Statement statement;

    static ResultSet resultSet;


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

    public static ResultSet runQuery(String query){ // this is where our code runs, most important port
        // if we want a variable to be acsessible in every method, where do we put it? --> We must make it a static variable

        // now, we are reusing the connection build from previous method
        ResultSet rs = null;
            try {
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = statement.executeQuery(query);

            } catch (SQLException e) {
                System.out.println("Error while getting resultset " + e.getMessage());
            }
            return rs;

        }

        // create method to clean up all the connection statement and result set
    public static void destoy (){

    }
    public static void main(String[] args) throws SQLException {
        createConnection();
       ResultSet rs =  runQuery("SELECT * FROM REGIONS");

       // print out the second column first row
        rs.next();
        System.out.println(rs.getString(2));
    }
}
