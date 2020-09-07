package com.project.spring.Endpoint;

import com.project.spring.DTO.EmployeeResult;
import com.project.spring.Services.EmployeeLookupService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/EmployeeLookup")
public class EmployeeEndpoint {

    @Autowired
    private EmployeeLookupService employeeLookupService;

    final static Logger logger = LogManager.getLogger(EmployeeEndpoint.class);

    @ApiOperation(value = "returns an employee given first name and last name")
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/findEmployee", produces= "application/json")
    @ResponseBody
    public List<EmployeeResult> findEmployee(String first, String last) {
        logger.info("Handling request for an employee");
        List<EmployeeResult> yourEmployees = employeeLookupService.findEmployee(first,last);

        if (yourEmployees == null){
            logger.info("We could not find the person you were looking for.");
            return yourEmployees;
        }

        logger.info("Successfully generated a response for the employee look up");
        return yourEmployees;
    }
}
