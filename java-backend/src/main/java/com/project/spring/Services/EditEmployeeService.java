package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DTO.EditEmployee;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.DomainObjects.Salary;
import com.project.spring.DomainObjects.Title;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EditEmployeeService {
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

    private final static Logger logger = LogManager.getLogger(EditEmployeeService.class);

    public String editEmployee(EditEmployee inputEmployee) {

        String first = inputEmployee.getFirstName().toLowerCase();
        first = Character.toUpperCase(first.charAt(0)) + first.substring(1);
        String last = inputEmployee.getLastName().toLowerCase();
        last = Character.toUpperCase(last.charAt(0)) + last.substring(1);

        Employee editedEmployee = employeeDAO.findByEmpNo(inputEmployee.getEmpNo());

        editedEmployee.setFirstName(first);
        editedEmployee.setLastName(last);
        editedEmployee.setHireDate(inputEmployee.getHireDate());

        Date now = new Date();

        employeeDAO.save(editedEmployee);

        //if salary was changed
        if(editedEmployee.getSalaries().get(0).getPay() != inputEmployee.getSalary()){
            //terminate the existing salary
            Salary oldSalary = salaryDAO.findByEmpNoAndActive(editedEmployee.getEmpNo(), true);
            oldSalary.setActive(false);
            oldSalary.setToDate(now);
            salaryDAO.save(oldSalary);

            //create a new salary and add to database
            Salary newSalary = new Salary();
            newSalary.setToDate(inputEmployee.getToDate());
            newSalary.setFromDate(inputEmployee.getFromDate());
            newSalary.setActive(true);
            newSalary.setPay(inputEmployee.getSalary());
            newSalary.setEmpNo(inputEmployee.getEmpNo());
            editedEmployee.getSalaries().add(newSalary);
            salaryDAO.save(newSalary);
        }
        else{
            Salary salary = salaryDAO.findByEmpNoAndActive(editedEmployee.getEmpNo(), true);

            salary.setFromDate(inputEmployee.getFromDate());
            salary.setToDate(inputEmployee.getToDate());

            salaryDAO.save(salary);
        }

        return editedEmployee.getFirstName() + " " + inputEmployee.getLastName();
    }

}
