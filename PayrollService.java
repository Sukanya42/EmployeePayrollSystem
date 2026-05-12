package payroll.service;

import payroll.model.Employee;
import payroll.model.Payslip;

import java.util.ArrayList;
import java.util.List;

/**
 * PayrollService handles all operations related to employees and payroll.
 * Demonstrates: ArrayList, CRUD operations, Business Logic
 */
public class PayrollService {

    private List<Employee> employeeList;
    private int nextId;

    public PayrollService() {
        employeeList = new ArrayList<>();
        nextId = 1001;
        loadSampleData();
    }

    // ─── Load some sample employees ─────────────────────────────────────────
    private void loadSampleData() {
        addEmployee("Arjun Kumar",   "Engineering",  "Software Engineer",  55000);
        addEmployee("Priya Sharma",  "HR",           "HR Manager",         48000);
        addEmployee("Rahul Verma",   "Finance",      "Accountant",         42000);
        addEmployee("Sneha Reddy",   "Engineering",  "Senior Developer",   72000);
        addEmployee("Karthik Nair",  "Marketing",    "Marketing Executive",38000);
    }

    // ─── CRUD Operations ────────────────────────────────────────────────────

    /**
     * Add a new employee.
     */
    public Employee addEmployee(String name, String department, String designation, double basicSalary) {
        Employee emp = new Employee(nextId++, name, department, designation, basicSalary);
        employeeList.add(emp);
        return emp;
    }

    /**
     * Get all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    /**
     * Find employee by ID.
     */
    public Employee findById(int id) {
        for (Employee emp : employeeList) {
            if (emp.getEmployeeId() == id) {
                return emp;
            }
        }
        return null;
    }

    /**
     * Find employees by department.
     */
    public List<Employee> findByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (emp.getDepartment().equalsIgnoreCase(department)) {
                result.add(emp);
            }
        }
        return result;
    }

    /**
     * Update employee details.
     */
    public boolean updateEmployee(int id, String name, String department, String designation, double salary) {
        Employee emp = findById(id);
        if (emp != null) {
            emp.setName(name);
            emp.setDepartment(department);
            emp.setDesignation(designation);
            emp.setBasicSalary(salary);
            return true;
        }
        return false;
    }

    /**
     * Delete an employee by ID.
     */
    public boolean deleteEmployee(int id) {
        Employee emp = findById(id);
        if (emp != null) {
            employeeList.remove(emp);
            return true;
        }
        return false;
    }

    /**
     * Generate payslip for an employee for a given month.
     */
    public Payslip generatePayslip(int employeeId, String month) {
        Employee emp = findById(employeeId);
        if (emp == null) return null;
        return new Payslip(emp, month);
    }

    /**
     * Get total payroll expenditure for all employees.
     */
    public double getTotalPayroll(String month) {
        double total = 0;
        for (Employee emp : employeeList) {
            Payslip p = new Payslip(emp, month);
            total += p.getNetSalary();
        }
        return total;
    }

    /**
     * Get count of employees.
     */
    public int getEmployeeCount() {
        return employeeList.size();
    }
}
