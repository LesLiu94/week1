package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.DomainObjects.Salary;
import com.project.spring.DomainObjects.Title;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddEmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private DepartmentEmployeeDAO departmentEmployeeDAO;

    @Autowired
    private DepartmentManagerDAO departmentManagerDAO;

    @Autowired
    private SalaryDAO salaryDAO;

    @Autowired
    private TitleDAO titleDAO;

    private final static Logger logger = LogManager.getLogger(AddEmployeeService.class);

    public EmployeeLookupResult addEmployee(AddEmployeeRequest employeeRequest) throws Exception{

        logger.info("Adding employee by first name, last name, title, department, salary, birth date, hire date, gender, from date, and to date");
        EmployeeLookupResult newEmployeeResult = new EmployeeLookupResult();

        //checking all dates to see if they are the correct format
        checkDateFormat(employeeRequest.getBirthDate());
        checkDateFormat(""+employeeRequest.getFromDate());
        checkDateFormat(""+employeeRequest.getToDate());
        checkDateFormat(employeeRequest.getHireDate());

        //checking to see if dates are in order
        LocalDate fromLocalDate = LocalDate.parse(employeeRequest.getFromDate());
        LocalDate toLocalDate = LocalDate.parse(employeeRequest.getToDate());
        LocalDate hireLocalDate = LocalDate.parse(employeeRequest.getHireDate());
        LocalDate birthLocalDate = LocalDate.parse(employeeRequest.getBirthDate());
        if(toLocalDate.isBefore(fromLocalDate)){
            logger.info("Your 'from date' is after your 'to date' which does not make sense.");
            return null;
        }
        if(fromLocalDate.isBefore(hireLocalDate)){
            logger.info("Your 'hire date' is after your 'from date'/'start date'.");
            return null;
        }
        if(fromLocalDate.isBefore(birthLocalDate)){
            logger.info("Your 'birth date' is after your 'hire date' which makes absolutely no sense.");
            return null;
        }

        //salaries
        List<Salary> salaryList = new ArrayList<>();
        Salary newEmployeeSalary = new Salary();
        newEmployeeSalary.setPay(employeeRequest.getSalary());
        newEmployeeSalary.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeSalary.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
        salaryList.add(newEmployeeSalary);

        //titles
        List<Title> titleList = new ArrayList<>();
        Title newEmployeeTitle = new Title();
        newEmployeeTitle.setTitle(employeeRequest.getEmployeeTitle());
        newEmployeeTitle.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeTitle.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
        titleList.add(newEmployeeTitle);

        //employee
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastName(employeeRequest.getLastName());
        newEmployee.setSex(employeeRequest.getGender());
        newEmployee.setBirthDate(java.sql.Date.valueOf(employeeRequest.getBirthDate()));
        newEmployee.setHireDate(java.sql.Date.valueOf(employeeRequest.getHireDate()));
        newEmployee.setSalaries(salaryList);
        newEmployee.setTitles(titleList);

        employeeDAO.save(newEmployee);

        //response
        newEmployeeResult.setFirstName(employeeRequest.getFirstName());
        newEmployeeResult.setLastName(employeeRequest.getLastName());
        newEmployeeResult.setSalary(employeeRequest.getSalary());
        newEmployeeResult.setEmployeeTitle(employeeRequest.getEmployeeTitle());

        return newEmployeeResult;
    }

    private void checkDateFormat(String newEmployeeDate){
        LocalDate newEmployeeLocalDate;
        try {
            newEmployeeLocalDate = LocalDate.parse(""+newEmployeeDate);
        } catch (DateTimeParseException e) {
            logger.info("Your date format is incorrect, try again using the format: 'yyyy-MM-dd' ");
        }
    }

}
