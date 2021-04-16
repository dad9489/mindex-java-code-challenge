package com.mindex.challenge.data;

import java.util.List;

public class ReportingStructure {

    private final Employee employee;
    private final int numberOfReports;

    public ReportingStructure(Employee employee) {
        this.employee = employee;
        this.numberOfReports = retrieveReports(employee);
    }

    /**
     * Recursive method to determine the number of employees under the given employee. If the employee has no direct
     * reports, returns 0. Otherwise, recursively adds the number of reports for each employee below this one.
     * @param employee The Employee to be analyzed
     * @return the integer number of Employees that report to this Employee
     */
    private static int retrieveReports(Employee employee) {
        List<Employee> reports = employee.getDirectReports();
        if(reports == null || reports.isEmpty()) {
            return 0;
        } else {
            int sum = reports.size();
            for(Employee reportingEmp: reports) {
                sum += retrieveReports(reportingEmp);
            }
            return sum;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}
