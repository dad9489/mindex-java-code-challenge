package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Retrieving reporting structure for employee with id [{}]", id);

        Employee employee = employeeService.read(id);
        return new ReportingStructure(employee);
    }
}
