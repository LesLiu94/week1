package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.DomainObjects.Salary;
import com.project.spring.DomainObjects.Title;
import com.project.spring.dto.AddEmployeeRequest;
import com.project.spring.dto.EmployeeLookupResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    public EmployeeLookupResult addEmployee(AddEmployeeRequest employeeRequest){

        logger.info("Adding employee by first name, last name, title, salary, birth date, hire date," +
                " gender, from date, and to date");
        EmployeeLookupResult newEmployeeResult = new EmployeeLookupResult();

        //checking to see if dates are in order
        boolean onlyEmployeeDAO = false;
        if(employeeRequest.getFromDate() == null || employeeRequest.getToDate() == null){
            onlyEmployeeDAO = true;
            Date hireDate = employeeRequest.getHireDate();
            Date birthDate = employeeRequest.getBirthDate();
            if(hireDate.before(birthDate)){
                logger.info("Your 'birth date' is after your 'hire date' which makes absolutely no sense.");
                return null;
            }
        }
        else{
            Date fromDate = employeeRequest.getFromDate();
            Date toDate = employeeRequest.getToDate();
            Date hireDate = employeeRequest.getHireDate();
            Date birthDate = employeeRequest.getBirthDate();

            if(toDate.before(fromDate)){
                logger.info("Your 'from date' is after your 'to date' which does not make sense.");
                return null;
            }
            if(fromDate.before(hireDate)){
                logger.info("Your 'hire date' is after your 'from date'/'start date'.");
                return null;
            }
            if(fromDate.before(birthDate)){
                logger.info("Your 'birth date' is after your 'hire date' which makes absolutely no sense.");
                return null;
            }
        }

        //salaries
        List<Salary> salaryList = new ArrayList<>();
        Salary newEmployeeSalary = new Salary();
        newEmployeeSalary.setPay(employeeRequest.getSalary());
        if(!onlyEmployeeDAO){
            newEmployeeSalary.setToDate(employeeRequest.getToDate());
            newEmployeeSalary.setFromDate(employeeRequest.getFromDate());
        }
        salaryList.add(newEmployeeSalary);

        //titles
        List<Title> titleList = new ArrayList<>();
        Title newEmployeeTitle = new Title();
        newEmployeeTitle.setTitle(employeeRequest.getEmployeeTitle());
        if(!onlyEmployeeDAO){
            newEmployeeTitle.setFromDate(employeeRequest.getFromDate());
            newEmployeeTitle.setToDate(employeeRequest.getToDate());
        }
        titleList.add(newEmployeeTitle);

        //employee
        Employee newEmployee = new Employee();
        String newEmployeeFirstName = employeeRequest.getFirstName().toLowerCase();
        newEmployeeFirstName = Character.toUpperCase(newEmployeeFirstName.charAt(0)) + newEmployeeFirstName.substring(1);
        newEmployee.setFirstName(newEmployeeFirstName);
        String newEmployeeLastName = employeeRequest.getLastName().toLowerCase();
        newEmployeeLastName = Character.toUpperCase(newEmployeeLastName.charAt(0)) + newEmployeeLastName.substring(1);
        newEmployee.setLastName(newEmployeeLastName);
        newEmployee.setSex(employeeRequest.getGender());
        newEmployee.setBirthDate(employeeRequest.getBirthDate());

        newEmployee.setHireDate(employeeRequest.getHireDate());
        newEmployee.setSalaries(salaryList);
        newEmployee.setTitles(titleList);

        newEmployeeSalary.setEmployee(newEmployee);
        newEmployeeTitle.setEmployee(newEmployee);

        employeeDAO.save(newEmployee);

        //response
        newEmployeeResult.setFirstName(newEmployeeFirstName);
        newEmployeeResult.setLastName(newEmployeeLastName);
        newEmployeeResult.setSalary(employeeRequest.getSalary());
        newEmployeeResult.setEmployeeTitle(employeeRequest.getEmployeeTitle());

        return newEmployeeResult;
    }

}
