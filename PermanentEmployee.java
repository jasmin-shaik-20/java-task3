import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.logging.Logger;

class PermanentEmployee extends EmployeeDetails{

   private static Connection conn;
    private static PreparedStatement insertEmployeeStmt1;
    private static PreparedStatement deleteEmployeeStmt1;
    private static PreparedStatement searchEmployeeStmt1;
    private static PreparedStatement updateEmployeeStmtN1;

    private static PreparedStatement updateEmployeeStmtA1;
    private static PreparedStatement updateEmployeeStmtS1;
    private static PreparedStatement updateEmployeeStmtB1;
    static Logger logger = Logger.getLogger(PermanentEmployee.class.getName());
    private int  bonus;


    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public PermanentEmployee(int empid, String name, int age, int salary, int bonus) {
        super(empid, name, age, salary);
        this.bonus = bonus;

    }


    public int  getBonus() {

        return bonus;
    }
    public static void Connection()
    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Jasmin@20");
            insertEmployeeStmt1 = conn.prepareStatement("INSERT INTO PermanentEmployee (empId, name, age, salary, bonus) VALUES (?, ?, ?, ?, ?)");
            deleteEmployeeStmt1 = conn.prepareStatement("DELETE FROM PermanentEmployee WHERE empId = ?");
            searchEmployeeStmt1 = conn.prepareStatement("SELECT empId, name, age, salary, bonus from PermanentEmployee WHERE empId = ?");
            updateEmployeeStmtN1 = conn.prepareStatement("UPDATE PermanentEmployee SET name =? WHERE empId = ?");
            updateEmployeeStmtA1 = conn.prepareStatement("UPDATE PermanentEmployee SET age=? WHERE empId = ?");
            updateEmployeeStmtS1 = conn.prepareStatement("UPDATE PermanentEmployee SET salary=? WHERE empId = ?");
            updateEmployeeStmtB1 = conn.prepareStatement("UPDATE PermanentEmployee SET bonus=? WHERE empId = ?");
        }
        catch(Exception e){
                logger.info("something went wrong");
        }


    }

    public static void addRecord(PermanentEmployee permanentEmployee) throws SQLException
    {
        try {
            Connection();
            insertEmployeeStmt1.setInt(1, permanentEmployee.getEmpid());
            insertEmployeeStmt1.setString(2, permanentEmployee.getName());
            insertEmployeeStmt1.setInt(3, permanentEmployee.getAge());
            insertEmployeeStmt1.setInt(4, permanentEmployee.getSalary());
            insertEmployeeStmt1.setInt(5, permanentEmployee.getBonus());
            int rows=insertEmployeeStmt1.executeUpdate();
            if(rows>0) {
                logger.info("Employee inserted Successfully");
            }
            else{
                logger.info("Not inserted");
            }
        }
        finally{
            conn.close();
        }


    }

    public static void deleteRecord(int id) throws SQLException
    {
        try {
            Connection();
            deleteEmployeeStmt1.setInt(1, id);
            int rows=deleteEmployeeStmt1.executeUpdate();
            if(rows>0) {
                logger.info("Employee remove successfully");
            }
            else{
                logger.info("Employee id not found");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void listOfEmployees() throws SQLException
    {
        try {
            Connection();
            String sqlQuery = "SELECT empId, name, age, salary, bonus FROM PermanentEmployee";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt("empId"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));
                jsonObject.put("salary", resultSet.getInt("salary"));
                jsonObject.put("bonus", resultSet.getInt("bonus"));
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
            searchEmployeeStmt1.setInt(1, id);
            ResultSet resultSet = searchEmployeeStmt1.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt("empId"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));
                jsonObject.put("salary", resultSet.getInt("salary"));
                jsonObject.put("bonus", resultSet.getInt("bonus"));
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
            updateEmployeeStmtN1.setString(1, name);
            updateEmployeeStmtN1.setInt(2, id);
            int rows = updateEmployeeStmtN1.executeUpdate();
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


    public static void employeeAge(int id,int age) throws SQLException {
        try {
            Connection();
            updateEmployeeStmtA1.setInt(1, age);
            updateEmployeeStmtA1.setInt(2, id);
            int rows = updateEmployeeStmtA1.executeUpdate();
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
            updateEmployeeStmtS1.setInt(1, salary);
            updateEmployeeStmtS1.setInt(2, id);
            int rows = updateEmployeeStmtS1.executeUpdate();
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

    public static void employeeBonus(int id,int bonus) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtB1.setInt(1, bonus);
            updateEmployeeStmtB1.setInt(2, id);
            int rows = updateEmployeeStmtB1.executeUpdate();
            if (rows > 0) {
                logger.info("Bonus modified Successfully");
            } else {
                logger.info("Bonus not modified Successfully");
            }
        }
        finally {
            conn.close();
        }
    }


}
