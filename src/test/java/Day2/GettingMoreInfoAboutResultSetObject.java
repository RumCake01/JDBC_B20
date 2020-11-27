package Day2;

import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {
    public static void main(String[] args) throws SQLException {

        String connection = "jdbc:oracle:thin:@54.172.140.77:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connection, username, password);

        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEES");

        // to get more information about ResultSEt, we can get entire table which is the MetaData
        // MetaData is data about the data, not what is inside the columns or rows
        // resultSetMetaData - is data about the ResultSet object than contain our resulting rows and columns, it has the readl data.
        // for ex: column names, column counts etc

        // this is how we get the ResultSet

        ResultSetMetaData rsmd = rs.getMetaData();
        // getmetaData - doesnt contain the actual result we got by our query, it does not provide any values of the resultSet
        // it has information about the result table that we got from our query
        // ResultSetMetaData - itself does not contain any row data, but only information about the columns

        // we only need 2 methods from this to get column count and column name / label
        int colCount = rsmd.getColumnCount();
        System.out.println("column count"+colCount);

        // to get the column name we can use - getColumnLabel method:
        System.out.println("First columns Name is " + rsmd.getColumnLabel(1));
        System.out.println("Second column name is " + rsmd.getColumnLabel(2));

        // get all the column names:
        for(int colNum = 1; colNum <=colCount; colNum++){
            System.out.println("Column name "+ rsmd.getColumnLabel(colNum));



        }
        rs.close();
        statement.close();
        conn.close();

    }
}
