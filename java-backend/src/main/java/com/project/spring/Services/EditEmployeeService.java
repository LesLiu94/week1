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

        Date now = new Date();

        editedEmployee
                    .getTitles()
                    .stream()
                    .filter(position -> position.getFromDate() != null && now.compareTo(position.getFromDate()) >= 0)  //filter for ones that have started already
                    .filter(position -> position.getToDate() == null || now.compareTo(position.getToDate()) < 0) //filter for ones that havent ended yet
                    .max(Comparator.nullsFirst(Comparator.comparing(Title::getFromDate))) //get the "max" from date
                    .get()
                    .setTitle(inputEmployee.getTitle());

        editedEmployee
                .getSalaries()
                .stream()
                .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                .get()
                .setPay(inputEmployee.getSalary());

        employeeDAO.save(editedEmployee);

        return editedEmployee.getFirstName() + " " + inputEmployee.getLastName();
    }

}
