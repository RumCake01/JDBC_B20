package Day4;

import utility.DB_Utility;

public class WakeUp_Tasks {

    // Find out the name of top earner in each departments in HR Database

    public static void main(String[] args) {
     String query = "-- Find out the name of top earner in each departments in HR Database\n" +
             " select e.FIRST_NAME, d.DEPARTMENT_NAME, E.SALARY\n" +
             "from EMPLOYEES e\n" +
             "inner join DEPARTMENTS D\n" +
             "                    on e.DEPARTMENT_ID = d.DEPARTMENT_ID\n" +
             "where salary in (select max (SALARY) from EMPLOYEES e\n" +
             "inner join DEPARTMENTS d on e.DEPARTMENT_ID = d.DEPARTMENT_ID\n" +
             "group by d.DEPARTMENT_NAME)\n" +
             //we can order it by a certain column
             "order by 1;";

    }


}
