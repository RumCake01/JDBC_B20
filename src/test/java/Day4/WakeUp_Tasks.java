package Day4;

import utility.DB_Utility;

public class WakeUp_Tasks {

    // Find out the name of top earner in each departments in HR Database

    public static void main(String[] args) {
        DB_Utility.createConnection();

        String query = "SELECT e.FIRST_NAME , d.DEPARTMENT_NAME , e.SALARY " +
                "FROM EMPLOYEES e " +
                "INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID " +
                "WHERE SALARY IN (   SELECT MAX(e.SALARY)  " +
                "                        FROM EMPLOYEES e " +
                "                        INNER JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID " +
                "                        GROUP BY d.DEPARTMENT_NAME   ) " +
                "ORDER BY 1  ";

        DB_Utility.runQuery(query) ;

        System.out.println("Row count is  " + DB_Utility.getRowCount());
        System.out.println("column count is  " + DB_Utility.getColumnCount());
        System.out.println("DB_Utility.getColumnDataAsList(\"FIRST_NAME\") =\n\t " + DB_Utility.getColumnDataAsList("FIRST_NAME"));
        System.out.println("DB_Utility.getRowMap(2) = \n\t" + DB_Utility.getRowMap(2));
        System.out.println("DB_Utility.getColumnDataAtRow(3, \"DEPARTMENT_NAME\") = \n\t" + DB_Utility.getColumnDataAtRow(3, "DEPARTMENT_NAME"));
        System.out.println("DB_Utility.getRowDataAsList(3) = \n\t" + DB_Utility.getRowDataAsList(3));
        DB_Utility.get

        DB_Utility.destroy();


    }


}









