package payroll.model;

/**
 * Employee class representing an employee in the payroll system.
 * Demonstrates OOP concepts: Encapsulation, Constructors, Getters & Setters
 */
public class Employee {

    private int employeeId;
    private String name;
    private String department;
    private String designation;
    private double basicSalary;

    // Constructor
    public Employee(int employeeId, String name, String department, String designation, double basicSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
    }

    // Getters
    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getBasicSalary() { return basicSalary; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Dept: %-15s | Role: %-20s | Basic: %.2f",
                employeeId, name, department, designation, basicSalary);
    }
}
