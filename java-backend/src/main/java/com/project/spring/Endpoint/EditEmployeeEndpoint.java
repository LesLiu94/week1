package com.project.spring.Endpoint;

import com.project.spring.DTO.EditEmployee;
import com.project.spring.Services.EditEmployeeService;
import com.project.spring.DTO.AddEmployeeRequest;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/Edit")
public class EditEmployeeEndpoint {

    @Autowired
    private EditEmployeeService editEmployeeService;

    final static Logger logger = LogManager.getLogger(EditEmployeeEndpoint.class);

    @ApiOperation(value = "edit and update an employee in the database")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/employee", consumes = "application/json", produces = "text/plain")
    @ResponseBody
    public String editingEmployee(@RequestBody final EditEmployee editEmployee){
        logger.info("Handling request to edit an employee");

        editEmployeeService.editEmployee(editEmployee);
        String result = editEmployeeService.editEmployee(editEmployee);

        return result;
    }
}
