package com.project.spring.Endpoint;

import com.project.spring.DomainObjects.Employee;
import com.project.spring.Services.EmployeeListLookupService;
import com.project.spring.Services.EmployeeLookupResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/EmployeeListLookup")
public class EmployeeListEndpoint {

    @Autowired
    private EmployeeListLookupService employeeListLookupService;

    final static Logger logger = LogManager.getLogger(EmployeeListEndpoint.class);

    @ApiOperation(value = "lists all of the employees")
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/allEmployees", produces = "application/json")
    @ResponseBody
    public List<EmployeeLookupResult> findAllEmployees(){
        logger.info("Handling request for list of employees");
        List<EmployeeLookupResult> employeeList = employeeListLookupService.findAllEmplyees();

        logger.info("Successfully generated a response for all employees");
        return employeeList;
    }
}
