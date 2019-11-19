package com.project.spring.Endpoint;

import com.project.spring.dto.AddEmployeeRequest;
import com.project.spring.Services.AddEmployeeService;
import com.project.spring.dto.EmployeeLookupResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/AddEmployee")
public class AddEmployeeEndpoint {

    @Autowired
    private AddEmployeeService addEmployeeService;

    final static Logger logger = LogManager.getLogger(AddEmployeeEndpoint.class);

    @ApiOperation(value = "adds an employee to the database")
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public EmployeeLookupResult addEmployee(@RequestBody final AddEmployeeRequest addedEmployee){
        logger.info("Handling request for an employee");

        final EmployeeLookupResult addedResult = addEmployeeService.addEmployee(addedEmployee);

        return addedResult;
    }

}
