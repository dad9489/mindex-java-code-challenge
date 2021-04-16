package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataBootstrap {
    private static final String DATASTORE_LOCATION = "/static/employee_database.json";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION);

        Employee[] employees = null;

        try {
            employees = objectMapper.readValue(inputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // For each employee, link the objects in their directReports list to the actual corresponding Employee object.
        // Without this, the Employee object inside the directReports list is null except for the id. Having it linked
        // to the real object allows fully traversing the reporting tree.
        for (Employee employee : employees) {
            if(employee.getDirectReports() != null) {
                List<Employee> linkedDirectReports = new ArrayList<>();
                for (Employee reportingEmp : employee.getDirectReports()) {
                    linkedDirectReports.add(Arrays.stream(employees)
                            .filter(e -> e.getEmployeeId().equals(reportingEmp.getEmployeeId()))
                            .collect(Collectors.toList())
                            .get(0));
                }
                employee.setDirectReports(linkedDirectReports);
            }
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }
    }
}
