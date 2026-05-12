package payroll.ui;

import payroll.model.Employee;
import payroll.model.Payslip;
import payroll.service.PayrollService;

import java.util.List;
import java.util.Scanner;

/**
 * PayrollUI - Console-based menu interface for the Employee Payroll System.
 * Demonstrates: Scanner input, switch-case, loops, formatted output
 */
public class PayrollUI {

    private PayrollService service;
    private Scanner scanner;

    public PayrollUI() {
        service = new PayrollService();
        scanner = new Scanner(System.in);
    }

    // ─── Main Menu ──────────────────────────────────────────────────────────
    public void start() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     EMPLOYEE PAYROLL MANAGEMENT SYSTEM   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewAllEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> generatePayslip();
                case 7 -> viewDepartmentEmployees();
                case 8 -> viewTotalPayroll();
                case 9 -> {
                    System.out.println("\nThank you for using Payroll System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("\n[!] Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│                 MAIN MENU                │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Add New Employee                     │");
        System.out.println("│  2. View All Employees                   │");
        System.out.println("│  3. Search Employee by ID                │");
        System.out.println("│  4. Update Employee Details              │");
        System.out.println("│  5. Delete Employee                      │");
        System.out.println("│  6. Generate Payslip                     │");
        System.out.println("│  7. View Employees by Department         │");
        System.out.println("│  8. View Total Payroll                   │");
        System.out.println("│  9. Exit                                 │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    // ─── Feature Methods ────────────────────────────────────────────────────

    private void addEmployee() {
        System.out.println("\n── Add New Employee ──");
        System.out.print("Enter Name        : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Department  : ");
        String dept = scanner.nextLine().trim();
        System.out.print("Enter Designation : ");
        String desig = scanner.nextLine().trim();
        double salary = getDoubleInput("Enter Basic Salary: ");

        Employee emp = service.addEmployee(name, dept, desig, salary);
        System.out.println("\n[✓] Employee added successfully!");
        System.out.println("    Employee ID: " + emp.getEmployeeId());
    }

    private void viewAllEmployees() {
        System.out.println("\n── All Employees (" + service.getEmployeeCount() + ") ──");
        List<Employee> list = service.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        printDivider();
        for (Employee emp : list) {
            System.out.println(emp);
        }
        printDivider();
    }

    private void searchEmployee() {
        int id = getIntInput("\nEnter Employee ID to search: ");
        Employee emp = service.findById(id);
        if (emp != null) {
            System.out.println("\n── Employee Found ──");
            System.out.println(emp);
        } else {
            System.out.println("[!] Employee with ID " + id + " not found.");
        }
    }

    private void updateEmployee() {
        int id = getIntInput("\nEnter Employee ID to update: ");
        Employee emp = service.findById(id);
        if (emp == null) {
            System.out.println("[!] Employee not found.");
            return;
        }
        System.out.println("Current details: " + emp);
        System.out.println("Enter new details (press Enter to keep current):");

        System.out.print("Name [" + emp.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = emp.getName();

        System.out.print("Department [" + emp.getDepartment() + "]: ");
        String dept = scanner.nextLine().trim();
        if (dept.isEmpty()) dept = emp.getDepartment();

        System.out.print("Designation [" + emp.getDesignation() + "]: ");
        String desig = scanner.nextLine().trim();
        if (desig.isEmpty()) desig = emp.getDesignation();

        System.out.print("Basic Salary [" + emp.getBasicSalary() + "]: ");
        String salaryStr = scanner.nextLine().trim();
        double salary = salaryStr.isEmpty() ? emp.getBasicSalary() : Double.parseDouble(salaryStr);

        service.updateEmployee(id, name, dept, desig, salary);
        System.out.println("[✓] Employee updated successfully!");
    }

    private void deleteEmployee() {
        int id = getIntInput("\nEnter Employee ID to delete: ");
        Employee emp = service.findById(id);
        if (emp == null) {
            System.out.println("[!] Employee not found.");
            return;
        }
        System.out.println("Are you sure you want to delete: " + emp.getName() + "? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            service.deleteEmployee(id);
            System.out.println("[✓] Employee deleted successfully.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private void generatePayslip() {
        int id = getIntInput("\nEnter Employee ID: ");
        System.out.print("Enter Month (e.g., May 2025): ");
        String month = scanner.nextLine().trim();

        Payslip payslip = service.generatePayslip(id, month);
        if (payslip == null) {
            System.out.println("[!] Employee not found.");
            return;
        }

        Employee emp = payslip.getEmployee();
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf( "║              PAYSLIP - %-24s ║%n", payslip.getMonth());
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf( "║  Employee ID  : %-32d ║%n", emp.getEmployeeId());
        System.out.printf( "║  Name         : %-32s ║%n", emp.getName());
        System.out.printf( "║  Department   : %-32s ║%n", emp.getDepartment());
        System.out.printf( "║  Designation  : %-32s ║%n", emp.getDesignation());
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║              EARNINGS                            ║");
        System.out.printf( "║  Basic Salary : ₹ %,-29.2f ║%n", payslip.getBasicSalary());
        System.out.printf( "║  HRA (20%%)    : ₹ %,-29.2f ║%n", payslip.getHra());
        System.out.printf( "║  DA  (10%%)    : ₹ %,-29.2f ║%n", payslip.getDa());
        System.out.printf( "║  TA (Fixed)   : ₹ %,-29.2f ║%n", payslip.getTa());
        System.out.printf( "║  Gross Salary : ₹ %,-29.2f ║%n", payslip.getGrossSalary());
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║              DEDUCTIONS                          ║");
        System.out.printf( "║  PF (12%%)     : ₹ %,-29.2f ║%n", payslip.getPfDeduction());
        System.out.printf( "║  Tax (10%%)    : ₹ %,-29.2f ║%n", payslip.getTaxDeduction());
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf( "║  NET SALARY   : ₹ %,-29.2f ║%n", payslip.getNetSalary());
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private void viewDepartmentEmployees() {
        System.out.print("\nEnter Department name: ");
        String dept = scanner.nextLine().trim();
        List<Employee> list = service.findByDepartment(dept);
        if (list.isEmpty()) {
            System.out.println("[!] No employees found in department: " + dept);
        } else {
            System.out.println("\n── Employees in " + dept + " (" + list.size() + ") ──");
            printDivider();
            for (Employee emp : list) {
                System.out.println(emp);
            }
            printDivider();
        }
    }

    private void viewTotalPayroll() {
        System.out.print("\nEnter Month (e.g., May 2025): ");
        String month = scanner.nextLine().trim();
        double total = service.getTotalPayroll(month);
        System.out.printf("\n[✓] Total Payroll for %s: ₹ %,.2f%n", month, total);
    }

    // ─── Utility Methods ────────────────────────────────────────────────────

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("[!] Please enter a valid number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("[!] Please enter a valid amount.");
            }
        }
    }

    private void printDivider() {
        System.out.println("─".repeat(80));
    }
}
