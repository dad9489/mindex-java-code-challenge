package com.mindex.challenge;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testReportingStructure() {
		// Create test employee objects
		Employee aEmployee = new Employee();
		aEmployee.setFirstName("A");
		Employee bEmployee = new Employee();
		bEmployee.setFirstName("B");
		Employee cEmployee = new Employee();
		cEmployee.setFirstName("C");
		Employee dEmployee = new Employee();
		dEmployee.setFirstName("D");
		Employee eEmployee = new Employee();
		eEmployee.setFirstName("E");
		Employee fEmployee = new Employee();
		fEmployee.setFirstName("F");

		// Set direct reports to create a reporting structure. The structure is as follows:
		//   A
		//  / \
		// B   C
		//    / \
		//   D   E
		//       |
		//       F
		aEmployee.setDirectReports(new ArrayList<>(Arrays.asList(bEmployee, cEmployee)));
		cEmployee.setDirectReports(new ArrayList<>(Arrays.asList(dEmployee, eEmployee)));
		eEmployee.setDirectReports(new ArrayList<>(Collections.singletonList(fEmployee)));

		// Create ReportingStructure objects
		ReportingStructure aReportingStructure = new ReportingStructure(aEmployee);
		ReportingStructure bReportingStructure = new ReportingStructure(bEmployee);
		ReportingStructure cReportingStructure = new ReportingStructure(cEmployee);

		// B, C, D, E, and F are all under A, so A should have 5 reports
		assertEquals(5, aReportingStructure.getNumberOfReports());

		// Nobody reports to B, so B should have 0 reports
		assertEquals(0, bReportingStructure.getNumberOfReports());

		// D, E, and F are all under C, so C should have 3 reports
		assertEquals(3, cReportingStructure.getNumberOfReports());
	}

}
