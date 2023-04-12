import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.logging.Logger;

public class PartTimeEmployee extends EmployeeDetails{

    private int hoursWorked;
    private static Connection conn;
    private static PreparedStatement insertEmployeeStmt2;
    private static PreparedStatement deleteEmployeeStmt2;
    private static PreparedStatement searchEmployeeStmt2;
    private static PreparedStatement updateEmployeeStmtN2;
    private static PreparedStatement updateEmployeeStmtA2;
    private static PreparedStatement updateEmployeeStmtS2;
    private static PreparedStatement updateEmployeeStmtH2;

    static Logger logger = Logger.getLogger(PartTimeEmployee.class.getName());


    public PartTimeEmployee(int empid, String name, int age, int salary, int hoursWorked) {
        super(empid, name, age, salary);
        this.hoursWorked = hoursWorked;

    }


    public int getHoursWorked() {
        return hoursWorked;
    }


    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public static void Connection()
    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Jasmin@20");
            insertEmployeeStmt2 = conn.prepareStatement("INSERT INTO PartTimeEmployee (empId, name, age, salary, hoursWorked) VALUES (?, ?, ?, ?, ?)");
            deleteEmployeeStmt2 = conn.prepareStatement("DELETE FROM PartTimeEmployee WHERE empId = ?");
            searchEmployeeStmt2 = conn.prepareStatement("SELECT empId, name, age, salary, hoursWorked from PartTimeEmployee WHERE empId = ?");
            updateEmployeeStmtN2 = conn.prepareStatement("UPDATE PartTimeEmployee SET name =? WHERE empId = ?");
            updateEmployeeStmtA2 = conn.prepareStatement("UPDATE PartTimeEmployee SET age=? WHERE empId = ?");
            updateEmployeeStmtS2 = conn.prepareStatement("UPDATE PartTimeEmployee SET salary=? WHERE empId =?");
            updateEmployeeStmtH2 = conn.prepareStatement("UPDATE PartTimeEmployee SET hoursWorked=? WHERE empId = ?");
        }
        catch(Exception e)
        {
            logger.info("Something went wrong");
        }

    }

    public static void addRecord(PartTimeEmployee partTimeEmployee) throws SQLException
    {
        try {
            Connection();
            insertEmployeeStmt2.setInt(1, partTimeEmployee.getEmpid());
            insertEmployeeStmt2.setString(2, partTimeEmployee.getName());
            insertEmployeeStmt2.setInt(3, partTimeEmployee.getAge());
            insertEmployeeStmt2.setInt(4, partTimeEmployee.getSalary());
            insertEmployeeStmt2.setInt(5, partTimeEmployee.getHoursWorked());
            int rows=insertEmployeeStmt2.executeUpdate();
            if(rows>0) {
                logger.info("Inserted Successfully");
            }
            else{
                logger.info("Not inserted");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void deleteRecord(int id) throws SQLException
    {
        try {
            Connection();
            deleteEmployeeStmt2.setInt(1, id);
            int rows=deleteEmployeeStmt2.executeUpdate();
            if(rows>0) {
                logger.info("Employee remove successfully");
            }
            else{
                logger.info("Employee id not found");
            }
        }
        finally{
            conn.close();
        }
    }

    public static void listOfEmployees() throws SQLException
    {
        try {
            Connection();
            String sqlQuery = "SELECT empId, name, age, salary, hoursWorked  FROM PartTimeEmployee";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt("empId"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));
                jsonObject.put("salary", resultSet.getInt("salary"));
                jsonObject.put("hoursWorked", resultSet.getInt("hoursWorked"));
                jsonArray.put(jsonObject);
            }
            System.out.println(jsonArray);
        }
        finally {
            conn.close();
        }
    }

    public static void searchRecord(int id) throws SQLException
    {
        try {
            Connection();
            searchEmployeeStmt2.setInt(1, id);
            ResultSet resultSet = searchEmployeeStmt2.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt(1));
                jsonObject.put("name", resultSet.getString(2));
                jsonObject.put("age", resultSet.getInt(3));
                jsonObject.put("salary", resultSet.getInt(4));
                jsonObject.put("hoursWorked", resultSet.getInt(5));
                jsonArray.put(jsonObject);
            }
            if (jsonArray.isEmpty()) {
                logger.info("Employee id not found");
            } else {
                System.out.println(jsonArray);

            }
        }
        finally {
            conn.close();
        }
    }

    public static void employeeName(int id,String name) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtN2.setString(1, name);
            updateEmployeeStmtN2.setInt(2, id);
            int rows = updateEmployeeStmtN2.executeUpdate();
            if (rows > 0) {
                logger.info("Name modified Successfully");
            } else {
                logger.info("Name is not modified");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void employeeAge(int id,int age) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtA2.setInt(1, age);
            updateEmployeeStmtA2.setInt(2, id);
            int rows = updateEmployeeStmtA2.executeUpdate();
            if (rows > 0) {
                logger.info("Age modified Successfully");
            } else {
                logger.info("Age is not modified");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void employeeSalary(int id,int salary) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtS2.setInt(1, salary);
            updateEmployeeStmtS2.setInt(2, id);
            int rows = updateEmployeeStmtS2.executeUpdate();
            if (rows > 0) {
                logger.info("Salary modified Successfully");
            } else {
                logger.info("Salary is not modified Successfully");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void employeeHoursWorked(int id,int hoursWorked) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtH2.setInt(1, hoursWorked);
            updateEmployeeStmtH2.setInt(2, id);
            int rows = updateEmployeeStmtH2.executeUpdate();
            if (rows > 0) {
                logger.info("HoursWorked modified Successfully");
            } else {
                logger.info("HoursWorked is not modified Successfully");
            }
        }
        finally {
            conn.close();
        }

    }

}
