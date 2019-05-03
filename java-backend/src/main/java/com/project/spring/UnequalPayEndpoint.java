package com.project.spring;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
        ArrayList<String> unequalEmployees = UnequalPayLookupService
    }




}
