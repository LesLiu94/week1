package com.project.spring.Endpoint;

import com.project.spring.Services.FireEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value =  "/Fire")
public class FireEmployeeEndpoint {

    @Autowired
    private FireEmployeeService fireEmployeeService;

    final static Logger logger = LogManager.getLogger(FireEmployeeEndpoint.class);

    @ApiOperation(value = "fires an employee by ending current salary as of today.")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/employee/{employeeNumber}", produces = "text/plain")
    @ResponseBody
    public String firingEmployee(@PathVariable final Integer employeeNumber){
        logger.info("Handling request to fire an employee.");

        String result = fireEmployeeService.fireEmployee(employeeNumber);

        logger.info("Successfully fired employee with employee number " + employeeNumber.toString() + ".");
        return result;
    }
}
