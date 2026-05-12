import payroll.ui.PayrollUI;

/**
 * Main entry point for the Employee Payroll Management System.
 *
 * Features:
 *  - Add, View, Update, Delete Employees
 *  - Generate detailed Payslip (with HRA, DA, TA, PF, Tax)
 *  - Search by Employee ID or Department
 *  - View total payroll cost
 *
 * Concepts demonstrated:
 *  - Object-Oriented Programming (Classes, Encapsulation)
 *  - Collections (ArrayList)
 *  - Scanner for user input
 *  - Formatted console output
 *  - Layered architecture (model / service / ui)
 */
public class Main {
    public static void main(String[] args) {
        PayrollUI ui = new PayrollUI();
        ui.start();
    }
}
