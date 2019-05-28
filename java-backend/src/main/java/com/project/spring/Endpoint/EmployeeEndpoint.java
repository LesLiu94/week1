package com.project.spring.Endpoint;

import com.project.spring.Services.EmployeeLookupService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/EmployeeLookup")
public class EmployeeEndpoint {

    @Autowired
    private EmployeeLookupService employeeLookupService;

    final static Logger logger = LogManager.getLogger(EmployeeEndpoint.class);

    @ApiOperation(value = "returns an employee given first name, last name, and date of birth")
    @GetMapping(value = "/findEmployee", produces = "application/json")
    public String findEmployee(String first, String last, String dob) {
        logger.info("Handling request for an employee");
        String yourEmployee = employeeLookupService.findEmployee(first,last,dob);

        logger.info("Successfully generated a response for the employee look up");
        return yourEmployee;
    }
}
