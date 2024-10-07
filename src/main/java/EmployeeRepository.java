import java.sql.*;

public class EmployeeRepository {

    Connection con;

    public EmployeeRepository() {
        getConncetion();
    }

    public void getConncetion() {
        try {
            String database = "jdbc:mysql://localhost:3306/emp_dept";
            String username = "meb";
            String password = "nad80";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //a) En metode, der udskriver medarbejdernummer og -navn på alle medarbejdere.
    public void printEmployeeNumberAndName() {
        try {
            String SQL = "SELECT * FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("EmployeeName: " + rs.getString("ENAME") + " " + "EmployeeNumber:" + rs.getString("EMPNO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //b) En metode, der udskriver medarbejdernummer og -navn på medarbejdere, der starter med ”S”.
    public void printEmployeeNameStartsWithS() {
        try {
            String SQL = "SELECT * FROM emp WHERE ENAME LIKE 'S%'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("EmployeeName: " + rs.getString("ENAME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //c) En metode, der udskriver den samlede lønudgift for alle medarbejdere.
    public void printAllExpenses() {
        try {
            String SQL = "SELECT SUM(sal) AS total_salary FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("Total Salary: " + rs.getInt("total_salary"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //d) En metode der udskriver navnet på den højst lønnede medarbejder.
    public void printHighestPaidEmployee() {
        try {
            String SQL = "SELECT MAX(sal) AS highest_salary FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("Highest salary: " + rs.getString("highest_salary"));
        }
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //e) En metode, der udskriver antal medarbejdere som tjener mere end gennemsnittet.
public void printNumberOfEmployeesWhoEarnsMoreThanAverage() {
        try {
            String SQL = "SELECT COUNT(*) AS employee_count FROM emp WHERE sal < (SELECT AVG(sal) FROM emp)";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("People who earn more than average: " + rs.getInt("employee_count"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}

    //f) En metode, der udskriver navnet på medarbejdere med en given manager (managers navngives som parameter til metoden)
public void printEmployeesBasedOnManager() {
        //VIRKER IKKE
        try {
            String paramQuery = "SELECT emp.ENAME AS 'Name', emp.SAL, emp.MGR AS 'Manager Number', emp2.ENAME AS 'MGNR Name'" + "FROM emp JOIN emp as emp2 on emp.MGR = emp2.EMPNO WHERE emp.MGR =?";
            PreparedStatement ps = con.prepareStatement(paramQuery);
            ps.setInt(1, 8000);
            ResultSet rs = ps.executeQuery(paramQuery);
            System.out.println("Employees based on manager number");

            while (rs.next()) {
                System.out.println("Name: "+rs.getString("Name")+": "+"Salary: "+rs.getInt("SAL") + ": " + "MGR Number: "+rs.getInt("Manager Number") +": "+"MNGR Name: "+rs.getString("MNGR Name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}

    //g) En metode, der udskriver afdelingsnavn på afdeling med mere end 5 medarbejdere.

}
