package com.project.spring.Endpoint;

import com.google.gson.Gson;
import com.project.spring.Services.UnequalPayLookupService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/unequalPayLookup")
public class UnequalPayEndpoint {

    @Autowired
    private UnequalPayLookupService unequalPayLookupService;

    final static Logger logger = LogManager.getLogger(UnequalPayEndpoint.class);

    @ApiOperation(value = "lists all of the employees that make less than their juniors")
    public ResponseEntity<String> findUnequallyPaidEmployees() {
        logger.info("Handling request for list of employees that make less than their juniors");
        ArrayList<Integer> unequalEmployees = unequalPayLookupService.findUnequallyPaidEmployees();
        if (unequalEmployees.size()<=0) {
            logger.info("Found no employees that make less than their juniors.");
            String json = new Gson().toJson("{}");
            return ResponseEntity.status(202).header("Could not find an employee").body(json);
        }
        else{
            logger.info("Successfully generated a response for unequally paid employees");
            String json = new Gson().toJson(unequalEmployees);
            return ResponseEntity.ok(json);
        }
    }




}
