package com.project.spring.Services;

import com.project.spring.CompositeKeys.DeptEmpCompositeKey;
import com.project.spring.CompositeKeys.DeptManagerCompositeKey;
import com.project.spring.CompositeKeys.SalariesCompositeKey;
import com.project.spring.CompositeKeys.TitlesCompositeKey;
import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

     //populating employeeDAO

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastName(employeeRequest.getLastName());
        newEmployee.setSex(employeeRequest.getGender());
        newEmployee.setBirthDate(java.sql.Date.valueOf(employeeRequest.getBirthDate()));
        newEmployee.setHireDate(java.sql.Date.valueOf(employeeRequest.getHireDate()));
        employeeDAO.save(newEmployee);

     //retrieving the spring generated empNo
        int newEmployeeEmpNo = employeeDAO.findByFirstNameAndLastName(employeeRequest.getFirstName(),employeeRequest.getLastName()).getEmpNo();
     //populating titleDAO

        Title newEmployeeTitle = new Title();
        newEmployeeTitle.setEmpNo(newEmployeeEmpNo);
        newEmployeeTitle.setTitle(employeeRequest.getEmployeeTitle());
        newEmployeeTitle.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeTitle.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
        TitlesCompositeKey newEmployeeTitlesCompositeKey = new TitlesCompositeKey();
        newEmployeeTitlesCompositeKey.setEmpNo(newEmployeeEmpNo);
        newEmployeeTitlesCompositeKey.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeTitlesCompositeKey.setTitle(employeeRequest.getEmployeeTitle());
        newEmployeeTitle.setTitlesCompositeKey(newEmployeeTitlesCompositeKey);
        titleDAO.save(newEmployeeTitle);

     //populating salaryDAO
        Salary newEmployeeSalary = new Salary();
        newEmployeeSalary.setEmp_No(newEmployeeEmpNo);
        newEmployeeSalary.setPay(employeeRequest.getSalary());
        newEmployeeSalary.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeSalary.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
        SalariesCompositeKey newEmployeeSalariesCompositeKey = new SalariesCompositeKey();
        newEmployeeSalariesCompositeKey.setEmpNo(newEmployeeEmpNo);
        newEmployeeSalariesCompositeKey.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
        newEmployeeSalary.setSalariesCompositeKey(newEmployeeSalariesCompositeKey);
        salaryDAO.save(newEmployeeSalary);

        if (employeeRequest.getEmployeeTitle().equals("Manager")){
     //populate and save departmentManagerDAO

            DepartmentManager newEmployeeDepartmentManager = new DepartmentManager();
            newEmployeeDepartmentManager.setEmpNo(newEmployeeEmpNo);
            Department newEmployeeDepartment = new Department();
            newEmployeeDepartment.setDeptName(employeeRequest.getDepartments().get(0));
            switch(employeeRequest.getDepartments().get(0)){
                case "Production and Operations":
                    newEmployeeDepartment.setDeptNo("0000");
                case "Research and Development":
                    newEmployeeDepartment.setDeptNo("0001");
                case "Purchasing":
                    newEmployeeDepartment.setDeptNo("0002");
                case "Marketing":
                    newEmployeeDepartment.setDeptNo("0003");
                case "Human Resources":
                    newEmployeeDepartment.setDeptNo("0004");
                case "Accounting and Finance":
                    newEmployeeDepartment.setDeptNo("0005");
            }
            newEmployeeDepartmentManager.setDepartment(newEmployeeDepartment);
            newEmployeeDepartmentManager.setDeptNo(newEmployeeDepartment.getDeptNo());
            newEmployeeDepartmentManager.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
            newEmployeeDepartmentManager.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
            newEmployeeDepartmentManager.setEmployee(newEmployee);
            DeptManagerCompositeKey newEmployeeDeptManagerCompositeKey = new DeptManagerCompositeKey();
            newEmployeeDeptManagerCompositeKey.setDeptNo(Integer.parseInt(newEmployeeDepartment.getDeptNo()));
            newEmployeeDeptManagerCompositeKey.setEmpNo(newEmployeeEmpNo);
            newEmployeeDepartmentManager.setDeptManagerCompositeKey(newEmployeeDeptManagerCompositeKey);
            departmentManagerDAO.save(newEmployeeDepartmentManager);

        }
        else{
     //populate and save departmentEmployeeDAO
            DepartmentEmployee newEmployeeDepartmentEmployee = new DepartmentEmployee();
            newEmployeeDepartmentEmployee.setEmpNo(newEmployeeEmpNo);
            Department newEmployeeDepartment = new Department();
            newEmployeeDepartment.setDeptName(employeeRequest.getDepartments().get(0));
            switch(employeeRequest.getDepartments().get(0)){
                case "Production and Operations":
                    newEmployeeDepartment.setDeptNo("0000");
                case "Research and Development":
                    newEmployeeDepartment.setDeptNo("0001");
                case "Purchasing":
                    newEmployeeDepartment.setDeptNo("0002");
                case "Marketing":
                    newEmployeeDepartment.setDeptNo("0003");
                case "Human Resources":
                    newEmployeeDepartment.setDeptNo("0004");
                case "Accounting and Finance":
                    newEmployeeDepartment.setDeptNo("0005");
            }
            newEmployeeDepartmentEmployee.setDeptNo(newEmployeeDepartment.getDeptNo());
            newEmployeeDepartmentEmployee.setDepartment(newEmployeeDepartment);
            newEmployeeDepartmentEmployee.setFromDate(java.sql.Date.valueOf(employeeRequest.getFromDate()));
            newEmployeeDepartmentEmployee.setToDate(java.sql.Date.valueOf(employeeRequest.getToDate()));
            newEmployeeDepartmentEmployee.setEmployee(newEmployee);
            DeptEmpCompositeKey newEmployeeDeptEmpCompositeKey = new DeptEmpCompositeKey();
            newEmployeeDeptEmpCompositeKey.setDeptNo(Integer.parseInt(newEmployeeDepartment.getDeptNo()));
            newEmployeeDeptEmpCompositeKey.setEmpNo(newEmployeeEmpNo);
            departmentEmployeeDAO.save(newEmployeeDepartmentEmployee);
        }

        List<String> newEmployeeDepartments = new ArrayList<>();
        newEmployeeDepartments.add(employeeRequest.getDepartments().get(0));

        //populate the result to send back
        newEmployeeResult.setFirstName(employeeRequest.getFirstName());
        newEmployeeResult.setLastName(employeeRequest.getLastName());
        newEmployeeResult.setSalary(employeeRequest.getSalary());
        newEmployeeResult.setDepartments(newEmployeeDepartments);
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
