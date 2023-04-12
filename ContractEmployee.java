import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.logging.Logger;

public class ContractEmployee extends EmployeeDetails{


   private static Connection conn;
    private static PreparedStatement insertEmployeeStmt3;
    private static PreparedStatement deleteEmployeeStmt3;
    private static PreparedStatement searchEmployeeStmt3;
    private static PreparedStatement updateEmployeeStmtN3;
    private static PreparedStatement updateEmployeeStmtA3;
    private static PreparedStatement updateEmployeeStmtS3;
    private static PreparedStatement updateEmployeeStmtC3;

    static Logger logger = Logger.getLogger(ContractEmployee.class.getName());


    private int contractDuration;
    public ContractEmployee(int empid, String name, int age, int salary,int contractDuration) {
        super(empid, name, age, salary);

        this.contractDuration = contractDuration;
    }



    public int getContractDuration()
    {
        return contractDuration;
    }


    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public static void Connection()
    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Jasmin@20");
            insertEmployeeStmt3 = conn.prepareStatement("INSERT INTO ContractEmployee (empId, name, age, salary, contractDuration) VALUES (?, ?, ?, ?, ?)");
            deleteEmployeeStmt3 = conn.prepareStatement("DELETE FROM ContractEmployee WHERE empId = ?");
            searchEmployeeStmt3 = conn.prepareStatement("SELECT empId, name, age, salary, contractDuration from ContractEmployee WHERE empId = ?");
            updateEmployeeStmtN3 = conn.prepareStatement("UPDATE ContractEmployee SET name =? WHERE empId = ?");
            updateEmployeeStmtA3 = conn.prepareStatement("UPDATE ContractEmployee SET  age=? WHERE empId = ?");
            updateEmployeeStmtS3 = conn.prepareStatement("UPDATE ContractEmployee SET salary=? WHERE empId = ?");
            updateEmployeeStmtC3 = conn.prepareStatement("UPDATE ContractEmployee SET contractDuration=? WHERE empId = ?");
        }
        catch(Exception e)
        {
            logger.info("something went wrong");
        }

    }

    public static void addRecord(ContractEmployee contractEmployee) throws SQLException
    {
        try {
            Connection();
            insertEmployeeStmt3.setInt(1, contractEmployee.getEmpid());
            insertEmployeeStmt3.setString(2, contractEmployee.getName());
            insertEmployeeStmt3.setInt(3, contractEmployee.getAge());
            insertEmployeeStmt3.setInt(4, contractEmployee.getSalary());
            insertEmployeeStmt3.setInt(5, contractEmployee.getContractDuration());
            int rows= insertEmployeeStmt3.executeUpdate();
            if(rows>0)
            {
                logger.info("Inserted Successfully");
            }
            else
            {
                logger.info("Not inserted");
            }
        }
        finally {
            conn.close();
        }
    }

    public static  void deleteRecord(int id) throws SQLException
    {
        try {
            Connection();
            deleteEmployeeStmt3.setInt(1, id);
            int rows=deleteEmployeeStmt3.executeUpdate();
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

    public static void listOfEmployees() throws SQLException {
        try {
            Connection();
            String sqlQuery = "SELECT empId, name, age, salary, contractDuration  FROM ContractEmployee";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt("empId"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));
                jsonObject.put("salary", resultSet.getInt("salary"));
                jsonObject.put("contractDuration", resultSet.getInt("contractDuration"));
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
            searchEmployeeStmt3.setInt(1, id);
            ResultSet resultSet = searchEmployeeStmt3.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("empId", resultSet.getInt("empId"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));
                jsonObject.put("salary", resultSet.getInt("salary"));
                jsonObject.put("ContractDuration", resultSet.getInt("contractDuration"));
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

    public static  void employeeName(int id,String name) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtN3.setString(1, name);
            updateEmployeeStmtN3.setInt(2, id);
            int rows = updateEmployeeStmtN3.executeUpdate();
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
            updateEmployeeStmtA3.setInt(1, age);
            updateEmployeeStmtA3.setInt(2, id);
            int rows = updateEmployeeStmtA3.executeUpdate();
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
            updateEmployeeStmtS3.setInt(1, salary);
            updateEmployeeStmtS3.setInt(2, id);
            int rows = updateEmployeeStmtS3.executeUpdate();
            if (rows > 0) {
                logger.info("Salary modified Successfully");
            } else {
                logger.info("Salary is not modified");
            }
        }
        finally {
            conn.close();
        }
    }

    public static void employeeContractDuration(int id,int contractDuration) throws SQLException
    {
        try {
            Connection();
            updateEmployeeStmtC3.setInt(1, contractDuration);
            updateEmployeeStmtC3.setInt(2, id);
            int rows = updateEmployeeStmtC3.executeUpdate();
            if (rows > 0) {
                logger.info("ContractDuration modified Successfully");
            } else {
                logger.info("ContractDuration is not modified");
            }
        }
        finally {
            conn.close();
        }

    }
}
