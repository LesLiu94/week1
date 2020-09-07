package com.project.spring.Endpoint;

import com.project.spring.Services.AddEmployeeService;
import com.project.spring.DTO.EmployeeResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/AddEmployee")
public class AddEmployeeEndpoint {

    @Autowired
    private AddEmployeeService addEmployeeService;

    final static Logger logger = LogManager.getLogger(AddEmployeeEndpoint.class);

    @ApiOperation(value = "adds an employee to the database")
    @CrossOrigin(origins = {"http://localhost:9000", "http://localhost:4200"})
    @PostMapping(path = "/employee",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public EmployeeResult addEmployee(@RequestBody EmployeeResult addedEmployee){
        logger.info("Handling request for an employee");

        final EmployeeResult addedResult = addEmployeeService.addEmployee(addedEmployee);

        logger.info("Successfully added " + addedResult.getFirstName() + " " + addedResult.getLastName() + ".");

        return addedResult;
    }

}
