package com.project.spring.Services;

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

    public List<EmployeeLookupResult> findEmployee(String first, String last) {

        logger.info("Finding employee(s) by first name and last name");

        //capitalizes the first character of first name and last name if not inputted as capitalized
        if (Character.isLowerCase(first.charAt(0))) {
            first = Character.toUpperCase(first.charAt(0)) + first.substring(1);
        }
        if (Character.isLowerCase(last.charAt(0))) {
            last = Character.toUpperCase(last.charAt(0)) + last.substring(1);
        }

        List<Employee> employees = employeeDAO.findByFirstNameAndLastName(first, last);
        List<EmployeeLookupResult> listEmployeeLookupResult = new ArrayList<>();

        if (employees == null) {
            return listEmployeeLookupResult;
        }

        EmployeeLookupResult employeeLookupResult = new EmployeeLookupResult();

        for (Employee employee : employees) {

            employeeLookupResult.setFirstName(employee.getFirstName());
            employeeLookupResult.setLastName(employee.getLastName());

            Date now = new Date();

            //assumes only 1 title is relevant at a time
            employeeLookupResult.setEmployeeTitle(employee
                    .getTitles()
                    .stream()
                    .filter(position -> position.getFromDate() != null && now.compareTo(position.getFromDate()) >= 0)  //filter for ones that have started already
                    .filter(position -> position.getToDate() == null || now.compareTo(position.getToDate()) < 0) //filter for ones that havent ended yet
                    .max(Comparator.nullsFirst(Comparator.comparing(Title::getFromDate))) //get the "max" from date
                    .map(Title::getTitle)
                    .orElse(EmployeeTitle.NONE));

            //assumes only 1 salary is relevant at a time
            employeeLookupResult.setSalary(employee
                    .getSalaries()
                    .stream()
                    .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                    .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                    .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                    .map(wage -> wage.getPay())
                    .orElse(0.0));


            if (employeeLookupResult.getEmployeeTitle() == EmployeeTitle.MANAGER) {
                //a person can manage many departments
                employeeLookupResult.setDepartments(employee
                        .getDepartmentManager()
                        .stream()
                        .filter(depManager -> depManager.getFromDate() != null && now.compareTo(depManager.getFromDate()) >= 0)
                        .filter(depManager -> depManager.getToDate() == null || now.compareTo(depManager.getToDate()) < 0)
                        .map(DepartmentManager::getDepartment) //get the department
                        .map(Department::getDeptName) //get the department name
                        .collect(Collectors.toList()));
            } else {
                //a person can work at many departments
                employeeLookupResult.setDepartments(employee
                        .getDepartmentEmployee()
                        .stream()
                        .filter(depEmployee -> depEmployee.getFromDate() != null && now.compareTo(depEmployee.getFromDate()) >= 0)
                        .filter(depEmployee -> depEmployee.getToDate() == null || now.compareTo(depEmployee.getToDate()) < 0)
                        .map(DepartmentEmployee::getDepartment)
                        .map(Department::getDeptName)
                        .collect(Collectors.toList()));
            }

            listEmployeeLookupResult.add(employeeLookupResult);
        }


        return listEmployeeLookupResult;
    }
}

