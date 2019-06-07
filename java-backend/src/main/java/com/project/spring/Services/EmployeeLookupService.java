package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    @Autowired
    private DepartmentDAO departmentDAO;

    private final static Logger logger = LogManager.getLogger(EmployeeLookupService.class);

    @ResponseBody
    public String findEmployee(String first, String last, String dobString){
        logger.info("Finding employee by first name, last name, and date of birth");
        String resultString="";
        Employee resultEmployee;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        //convert String to Date
        try{
            dob = formatter.parse(dobString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        resultEmployee = employeeDAO.findByFirstNameAndLastNameAndBirthDate(first, last, dob);

        logger.info("Finding title, salary and department of this employee.");
        resultString = resultEmployee.getFirstName() + " " + resultEmployee.getLastName() + " is a(n) " + resultEmployee.getTitles().get(0).getTitle().toString() + " of ";
        if (resultEmployee.getTitles().get(0).getTitle().equals("Manager")){
            resultString = resultString + resultEmployee.getDepartmentManager().get(0).getDepartment().getDeptName();
        }
        else {
            resultString = resultString + resultEmployee.getDepartmentEmployee().get(0).getDepartment().getDeptName();
        }

        resultString = resultString + " with a salary of " + resultEmployee.getSalaries().get(0).getPay() + ".";

        return resultString;
    }
}
