package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compensation {

    private Employee employee;
    private final BigDecimal salary;
    private final LocalDate effectiveDate;

    public Compensation(Employee employee, BigDecimal salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
