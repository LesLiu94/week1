package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

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

    public String findEmployee(String first, String last, String dobString) {

        logger.info("Finding employee by first name, last name, and date of birth");

        LocalDate dob;

        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException e) {
            return "Your date format is trash, try again using the format: 'yyyy-MM-dd' ";
        }

        Employee employee = employeeDAO.findByFirstNameAndLastNameAndBirthDate(first, last, dob.toDate());


        if (employee == null) {
            return "We could not find the person you were looking for.";
        }


        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        Date now = new Date();

        //assumes only 1 title is relevant at a time
        EmployeeTitle title = employee
                .getTitles()
                .stream()
                .filter(position -> position.getFromDate() != null && now.compareTo(position.getFromDate()) >= 0)  //filter for ones that have started already
                .filter(position -> position.getToDate() == null || now.compareTo(position.getToDate()) < 0) //filter for ones that havent ended yet
                .max(Comparator.nullsFirst(Comparator.comparing(Title::getFromDate))) //get the "max" from date
                .map(Title::getTitle)
                .orElse(EmployeeTitle.NONE);


        //assumes only 1 salary is relevant at a time
        int salary = employee
                .getSalaries()
                .stream()
                .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                .map(wage -> wage.getPay())
                .orElse(0);

        String departments;

        if (title == EmployeeTitle.Manager) {
            //a person can manage many departments
            List<String> managedDepartments = employee
                    .getDepartmentManager()
                    .stream()
                    .filter(depManager -> depManager.getFromDate() != null && now.compareTo(depManager.getFromDate()) >= 0)
                    .filter(depManager -> depManager.getToDate() == null || now.compareTo(depManager.getToDate()) < 0)
                    .map(DepartmentManager::getDepartment) //get the department
                    .map(Department::getDeptName) //get the department name
                    .collect(Collectors.toList());

            departments = String.join(", ", managedDepartments);
        } else {
            //a person can work at many departments
            List<String> employedDepartments = employee
                    .getDepartmentEmployee()
                    .stream()
                    .filter(depEmployee -> depEmployee.getFromDate() != null && now.compareTo(depEmployee.getFromDate()) >= 0)
                    .filter(depEmployee -> depEmployee.getToDate() == null || now.compareTo(depEmployee.getToDate()) < 0)
                    .map(DepartmentEmployee::getDepartment)
                    .map(Department::getDeptName)
                    .collect(Collectors.toList());

            departments = String.join(", ", employedDepartments);
        }

        String result = String.format("%s %s is a(n) %s with departments %s with a salary of %d.", firstName, lastName, title, departments, salary);

        return result;
    }
}
