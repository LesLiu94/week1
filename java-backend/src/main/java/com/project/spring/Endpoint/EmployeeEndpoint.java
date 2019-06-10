package com.project.spring.Endpoint;

import com.project.spring.Services.EmployeeLookupResult;
import com.project.spring.Services.EmployeeLookupService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/EmployeeLookup")
public class EmployeeEndpoint {

    @Autowired
    private EmployeeLookupService employeeLookupService;

    final static Logger logger = LogManager.getLogger(EmployeeEndpoint.class);

    @ApiOperation(value = "returns an employee given first name, last name, and date of birth")
    @GetMapping(value = "/findEmployee", produces= "application/json")
    @ResponseBody
    public EmployeeLookupResult findEmployee(String first, String last, String dobString) {
        logger.info("Handling request for an employee");
        EmployeeLookupResult yourEmployee = employeeLookupService.findEmployee(first,last,dobString);

        if (yourEmployee == null){
            logger.info("We could not find the person you were looking for.");
            return yourEmployee;
        }

        logger.info("Successfully generated a response for the employee look up");
        return yourEmployee;
    }
}
