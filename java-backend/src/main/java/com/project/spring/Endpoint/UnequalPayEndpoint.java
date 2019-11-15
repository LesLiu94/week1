package com.project.spring.Endpoint;

import com.project.spring.dto.UnequalLookupResult;
import com.project.spring.Services.UnequalPayLookupService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/PayLookup")
public class UnequalPayEndpoint {

    @Autowired
    private UnequalPayLookupService unequalPayLookupService;

    final static Logger logger = LogManager.getLogger(UnequalPayEndpoint.class);

    @ApiOperation(value = "lists all of the employees that make less than their juniors")
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/unequalEmployees", produces = "application/json")
    @ResponseBody
    public ArrayList<UnequalLookupResult> findUnequallyPaidEmployees() {
        logger.info("Handling request for list of employees that make less than their juniors");
        ArrayList<UnequalLookupResult> unequalEmployees = unequalPayLookupService.findUnequallyPaidEmployees();

        logger.info("Successfully generated a response for unequally paid employees");
        return unequalEmployees;
    }

}
