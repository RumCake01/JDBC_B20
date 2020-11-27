package Day1;

import java.sql.*;

public class Test_Connection {

    public static void main(String[] args) throws SQLException {

        // CONNECTION -->> STATEMENT --->> RESULTSET

        // Driver manager is used to get the connection
        // The IP address is the IP address of EC2 instance that have Oracle database
        // This is mine , so yours will your EC2 instance IP



        String connectionStr = "jdbc:oracle:thin:@54.172.140.77:1521:XE";

        // This is known as connection string or url
        // it has few part
        // "jdbc:   making jdbc connection , always start with this
        // oracle:  the database vendor (RDBMS) name we are trying to connect
        // thin:    1 type of oracle driver we are using ( downloaded using pom dependency)
        // @52.71.242.164: IP address of your database server
        // 1521:    port number of your database server ,  usually 1521 for oracle , 3306 for MySql Database
        // XE  :   SID name System identifier for the database we are connecting
        //  In a nutshell , see it as a full address of your database you can use to make connection


        // what are we trying to connect
        // jdbc protocol
        // oracle is the relational database vendors
        // thin: The JDBC Thin driver communicates with the server using SQL*Net to access the Oracle Database, is the dependency we addded in our pom xml to connect to the vendors driver.
        // @ - is the server adddress i m trying to connect which is my IP address
        // 1521: is the port ID. Allows Oracle client connections to the database via the Oracle's SQL*Net protocol.
        // XE: SID stands for System Identifier which is a unique name for your database. By default its either ORCL or XE. this will be diff in my company


        String username = "hr";
        String password = "hr";

        // JDBC ship with JDK , and has a lot of pre-wirtten codes to work with database
        // everything we do below comes from java.sql package

        // creating connection object using DriverManager's static method Connection ;
        // DriverManager is the utility class that has diff static methods:
        Connection conn = DriverManager.getConnection(connectionStr, username, password);
        // get connection creates the connection, then returns us connection object

        // all these three Connection, Statement, ResultSet are interfaces and coming from java.sql package, they dont provide the implementation
        // the implementation is provided by the vendor's driver like Oracle etc.


        // If something does not work automatically, always test it manually.

        // creating statement object using the connection we have established
        Statement stmnt = conn.createStatement();
        //

        // ResultSet object is what we use to store the actual result we get from query
        ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");
        // this above is how we run our query to ge the data and store in rs variable.
        // ResultSet comes with a cursor used to navigate between rows
        // initially the cursor is at before first location (right before the first row)
        // in order to come to the first row we need to move the cursor
        // next() method is used to move the cursor and return the result as boolean
        rs.next(); // currently we are at the very first row

        // getting the column data  we use multiple get methods available in ResultSet
        //  print out region id and region name  , both as String
        System.out.println("first column value using index: --> " + rs.getString(1));
        // we can provide column name or the number of the column as the columnindex
        // we use column index to read the data
        System.out.println(rs.getString(2));
        System.out.println("======");

        System.out.println("region_id " + rs.getString("REGION_ID"));
        System.out.println("region_id " + rs.getString("REGION_NAME"));

        System.out.println("first column value using column_name: --> " + rs.getString("REGION_ID"));
        // printing out second column data
        System.out.println("second column value using index: --> " + rs.getString(2));
        System.out.println("second column value using column_name: --> " + rs.getString("REGION_NAME"));

        System.out.println("============");
        // try to move to next row and get second row data as a task
        rs.next(); // im at the second row now/
        // if we want to return the data under specific column, we can the index number of that column, and it will return us the data from the column
        System.out.println("moving to the next row " + rs.getString(1));
        System.out.println("moving to the next row " + rs.getString(2));

        // we can also return the data under specific column, by indicating the column name, and then it will return the data under that column
        System.out.println("moving to the next row " + rs.getString("REGION_ID"));
        System.out.println("moving to the next row " + rs.getString("REGION_NAME"));
        System.out.println("======");
        System.out.println("first column value using index: --> " + rs.getString(1));
        System.out.println("first column value using column_name: --> " + rs.getString("REGION_ID"));
        // printing out second column data
        System.out.println("second column value using index: --> " + rs.getString(2));
        System.out.println("second column value using column_name: --> " + rs.getString("REGION_NAME"));

        // ORDER WE CREATED
        // CONNECTION -->> STATEMENT --->> RESULTSET

        // ORDER WHEN WE CLOSE
        // RESULTSET -->> STATEMENT --> CONNECTION
        /// IT'S ALWAYS GOOD PRACTICE TO CLOSE THE RESOURCES ONCE FINISH USING THEM
        // TO AVOID ISSUES LATER.
//------ cleaning up -----
        rs.close();
        stmnt.close();
        conn.close();

        System.out.println("THE END ");
    }
}



