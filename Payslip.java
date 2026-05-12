package payroll.model;

/**
 * Payslip class to hold salary computation details for an Employee.
 */
public class Payslip {

    private Employee employee;
    private double basicSalary;
    private double hra;           // House Rent Allowance (20% of basic)
    private double da;            // Dearness Allowance (10% of basic)
    private double ta;            // Travel Allowance (fixed)
    private double grossSalary;
    private double pfDeduction;   // Provident Fund (12% of basic)
    private double taxDeduction;  // Income Tax (10% of gross)
    private double netSalary;
    private String month;

    public Payslip(Employee employee, String month) {
        this.employee = employee;
        this.month = month;
        calculateSalary();
    }

    /**
     * Calculate all allowances, deductions, and net salary.
     */
    private void calculateSalary() {
        this.basicSalary = employee.getBasicSalary();

        // Allowances
        this.hra = basicSalary * 0.20;
        this.da  = basicSalary * 0.10;
        this.ta  = 1500.0;

        // Gross salary
        this.grossSalary = basicSalary + hra + da + ta;

        // Deductions
        this.pfDeduction  = basicSalary * 0.12;
        this.taxDeduction = grossSalary * 0.10;

        // Net salary
        this.netSalary = grossSalary - pfDeduction - taxDeduction;
    }

    // Getters
    public Employee getEmployee()   { return employee; }
    public double getBasicSalary()  { return basicSalary; }
    public double getHra()          { return hra; }
    public double getDa()           { return da; }
    public double getTa()           { return ta; }
    public double getGrossSalary()  { return grossSalary; }
    public double getPfDeduction()  { return pfDeduction; }
    public double getTaxDeduction() { return taxDeduction; }
    public double getNetSalary()    { return netSalary; }
    public String getMonth()        { return month; }
}
