import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getLogger(Main.class.getName());
        boolean bool = true;
        try {
            while (bool) {
                System.out.println("\n\n------ Employee Management System ------\n");
                System.out.println("1. Add employee");
                System.out.println("2. Remove employee");
                System.out.println("3. List all employees");
                System.out.println("4. Search employee");
                System.out.println("5. Modify employee");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n------ Add employee ------\n");
                        System.out.print("Enter employee ID: ");
                        int empId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter employee name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter employee age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter employee salary: ");
                        int salary = scanner.nextInt();
                        System.out.print("Enter employee type (1-Permanent, 2-PartTime, 3-Contract): ");
                        int empType = scanner.nextInt();

                        if (empType == 1) {
                            System.out.print("Enter employee bonus: ");
                            int bonus = scanner.nextInt();
                            PermanentEmployee permanentEmployee = new PermanentEmployee(empId, name, age, salary, bonus);
                            PermanentEmployee.addRecord(permanentEmployee);

                        } else if (empType == 2) {
                            System.out.print("Enter employee hours worked: ");
                            int hoursWorked = scanner.nextInt();
                            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(empId, name, age, salary, hoursWorked);
                            PartTimeEmployee.addRecord(partTimeEmployee);

                        } else if (empType == 3) {
                            System.out.print("Enter employee contract duration: ");
                            int contractDuration = scanner.nextInt();
                            ContractEmployee contractEmployee = new ContractEmployee(empId, name, age, salary, contractDuration);
                            ContractEmployee.addRecord(contractEmployee);

                        } else {
                            System.out.println("Invalid employee type.");
                        }
                        break;
                    case 2:
                        System.out.println("\n------ Remove employee ------\n");
                        System.out.print("Enter employee type (1-Permanent, 2-PartTime, 3-Contract): ");
                        int empTypeRemove = scanner.nextInt();
                        if (empTypeRemove == 1) {
                            System.out.print("Enter employee ID: ");
                            int empId1 = scanner.nextInt();
                            PermanentEmployee.deleteRecord(empId1);

                        } else if (empTypeRemove == 2) {
                            System.out.print("Enter employee ID: ");
                            int empId1 = scanner.nextInt();
                            PartTimeEmployee.deleteRecord(empId1);

                        } else if (empTypeRemove == 3) {
                            System.out.print("Enter employee ID: ");
                            int empId1 = scanner.nextInt();
                            ContractEmployee.deleteRecord(empId1);
                        }
                        break;

                    case 3:
                        System.out.println("\n------ List of employees------\n");
                        System.out.print("Enter employee type (1-Permanent, 2-PartTime, 3-Contract, 4.All types of employees): ");
                        int emplist = scanner.nextInt();
                        if (emplist == 1) {
                            PermanentEmployee.listOfEmployees();
                        } else if (emplist == 2) {
                            PartTimeEmployee.listOfEmployees();
                        } else if (emplist == 3) {
                            ContractEmployee.listOfEmployees();
                        } else if (emplist == 4) {
                            PermanentEmployee.listOfEmployees();
                            PartTimeEmployee.listOfEmployees();
                            ContractEmployee.listOfEmployees();

                        }
                        break;

                    case 4:
                        System.out.println("\n------Search employee--------\n");
                        System.out.print("Enter employee type (1-Permanent, 2-PartTime, 3-Contract): ");
                        int empsearch = scanner.nextInt();
                        if (empsearch == 1) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            PermanentEmployee.searchRecord(empid);
                        } else if (empsearch == 2) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            PartTimeEmployee.searchRecord(empid);

                        } else if (empsearch == 3) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            ContractEmployee.searchRecord(empid);
                        }
                        break;

                    case 5:
                        System.out.println("\n------Modify Employee--------\n");
                        System.out.print("Enter employee type (1-Permanent, 2-PartTime, 3-Contract): ");
                        int empmod = scanner.nextInt();
                        if (empmod == 1) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            System.out.println("Enter which credintials you want to edit: ");
                            System.out.println("1.name 2.age 3.salary 4.bonus");
                            int select = scanner.nextInt();
                            switch (select) {
                                case 1:
                                    System.out.println("Enter name to modify:");
                                    scanner.nextLine();
                                    String newName = scanner.nextLine();
                                    PermanentEmployee.employeeName(empid, newName);
                                    break;

                                case 2:
                                    System.out.println("Enter age to modify:");
                                    int newAge = scanner.nextInt();
                                    PermanentEmployee.employeeAge(empid, newAge);
                                    break;

                                case 3:
                                    System.out.println("Enter salary to modify:");
                                    int newSalary= scanner.nextInt();
                                    PermanentEmployee.employeeSalary(empid, newSalary);
                                    break;

                                case 4:
                                    System.out.println("Enter bonus to modify:");
                                    int newBonus = scanner.nextInt();
                                    PermanentEmployee.employeeBonus(empid, newBonus);
                                    break;
                            }
                        }

                        else if (empmod == 2) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            System.out.println("Enter which credintials you want to edit: ");
                            System.out.println("1.name 2.age 3.salary 4.HoursWorked");
                            int select = scanner.nextInt();
                            switch(select) {
                                case 1:
                                    System.out.println("Enter name to modify:");
                                    scanner.nextLine();
                                    String newName = scanner.nextLine();
                                    PartTimeEmployee.employeeName(empid, newName);
                                    break;

                                case 2:
                                    System.out.println("Enter age to modify:");
                                    int newAge = scanner.nextInt();
                                    PartTimeEmployee.employeeAge(empid, newAge);
                                    break;

                                case 3:
                                    System.out.println("Enter salary to modify:");
                                    int newSalary = scanner.nextInt();
                                    PartTimeEmployee.employeeSalary(empid, newSalary);
                                    break;

                                case 4:
                                    System.out.println("Enter hours time to modify:");
                                    int newHours = scanner.nextInt();
                                    PartTimeEmployee.employeeHoursWorked(empid, newHours);
                                    break;

                                }
                            }


                        else if (empmod == 3) {
                            System.out.println("Enter empid: ");
                            int empid = scanner.nextInt();
                            System.out.println("Enter which credintials you want to edit: ");
                            System.out.println("1.name 2.age 3.salary 4.DurationTime");
                            int select = scanner.nextInt();
                            switch(select) {
                                case 1:
                                    System.out.println("Enter name to modify:");
                                    scanner.nextLine();
                                    String newName = scanner.nextLine();
                                    ContractEmployee.employeeName(empid, newName);
                                    break;

                                case 2:
                                    System.out.println("Enter age to modify:");
                                    int newAge = scanner.nextInt();
                                    ContractEmployee.employeeAge(empid, newAge);
                                    break;

                                case 3:
                                    System.out.println("Enter salary to modify:");
                                    int newSalary = scanner.nextInt();
                                    ContractEmployee.employeeSalary(empid, newSalary);
                                    break;

                                case 4:
                                    System.out.println("Enter duration time to modify:");
                                    int newDuration = scanner.nextInt();
                                    ContractEmployee.employeeContractDuration(empid, newDuration);
                                    break;

                                }
                            }
                        break;
                }
                System.out.println("Enter true if you want to continue: ");
                bool = scanner.nextBoolean();


            }
        } catch (Exception e)
        {
            logger.info("Something went wrong");
        }
    }
}

