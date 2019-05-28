package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.DepartmentEmployee;
import com.project.spring.DomainObjects.DepartmentManager;
import com.project.spring.DomainObjects.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SalaryDAO salaryDAO;
    @Autowired
    private TitleDAO titleDAO;
    @Autowired
    private DepartmentEmployeeDAO departmentEmployeeDAO;
    @Autowired
    private DepartmentManagerDAO departmentManagerDAO;

    private final static Logger logger = LogManager.getLogger(EmployeeLookupService.class);

    @ResponseBody
    public String findEmployee(String first, String last, String dob){
        logger.info("Finding employee by first name, last name, and date of birth");
        String resultString="";
        Employee resultEmployee;
        resultEmployee = employeeDAO.findByFirstNameLastNameBirthDate(first, last, dob);

        logger.info("Finding title, salary and department of this employee.");
        resultString = resultEmployee.getFirstName() + " " + resultEmployee.getLastName() + " is a(n) " + resultEmployee.getTitle() + " of " + resultEmployee.getDepartmentManager().getDepartment().getDeptName() + " with a salary of " + resultEmployee.getSalary().getPay() +".";

        return resultString;
    }
}
