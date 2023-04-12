class EmployeeDetails {
     int empid;
     String name;
     int age;
     int salary;







    public EmployeeDetails(int empid, String name, int age, int salary) {
        this.empid = empid;
        this.name = name;
        this.age = age;
        this.salary = salary;


    }



    public int  getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String toString() {
        return "EmployeeDetails{" +
                "empid='" + empid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }


    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
