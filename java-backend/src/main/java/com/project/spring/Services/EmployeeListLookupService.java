package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeListLookupService {
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

    public List<EmployeeLookupResult> findAllEmplyees(){

        logger.info("Finding all employees.");

        List<Employee> employeeList = employeeDAO.findAll();

        List<EmployeeLookupResult> employeeListResult = new ArrayList<EmployeeLookupResult>();

        Date now = new Date();

        for (Employee e: employeeList) {

            EmployeeLookupResult employeeLookupResult = new EmployeeLookupResult();

            employeeLookupResult.setFirstName(e.getFirstName());
            employeeLookupResult.setLastName(e.getLastName());

            //assume only 1 title is relevant at a time
            employeeLookupResult.setEmployeeTitle( e
                .getTitles()
                .stream()
                    .filter(position -> position.getFromDate() != null && now.compareTo(position.getFromDate()) >= 0)  //filter for ones that have started already
                    .filter(position -> position.getToDate() == null || now.compareTo(position.getToDate()) < 0) //filter for ones that havent ended yet
                    .max(Comparator.nullsFirst(Comparator.comparing(Title::getFromDate))) //get the "max" from date
                    .map(Title::getTitle)
                    .orElse(EmployeeTitle.NONE));

            //assume only 1 salary is relevant at a time
            employeeLookupResult.setSalary( e
                .getSalaries()
                .stream()
                    .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                    .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                    .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                    .map(wage -> wage.getPay())
                    .orElse(0.0));

            if (employeeLookupResult.getEmployeeTitle() == EmployeeTitle.Manager) {
                //a person can manage many departments
                employeeLookupResult.setDepartments( e
                        .getDepartmentManager()
                        .stream()
                        .filter(depManager -> depManager.getFromDate() != null && now.compareTo(depManager.getFromDate()) >= 0)
                        .filter(depManager -> depManager.getToDate() == null || now.compareTo(depManager.getToDate()) < 0)
                        .map(DepartmentManager::getDepartment) //get the department
                        .map(Department::getDeptName) //get the department name
                        .collect(Collectors.toList()));
            }
            else {
                //a person can work at many departments
                employeeLookupResult.setDepartments( e
                        .getDepartmentEmployee()
                        .stream()
                        .filter(depEmployee -> depEmployee.getFromDate() != null && now.compareTo(depEmployee.getFromDate()) >= 0)
                        .filter(depEmployee -> depEmployee.getToDate() == null || now.compareTo(depEmployee.getToDate()) < 0)
                        .map(DepartmentEmployee::getDepartment)
                        .map(Department::getDeptName)
                        .collect(Collectors.toList()));
            }

            employeeListResult.add(employeeLookupResult);
        }

        return employeeListResult;


    }



}
