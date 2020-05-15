package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.DTO.EmployeeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;

    private final static Logger logger = LogManager.getLogger(EmployeeLookupService.class);

    public List<EmployeeResult> findEmployee(String first, String last) {

        logger.info("Finding employee(s) by first name and last name");

        first = first.toLowerCase();
        first = Character.toUpperCase(first.charAt(0)) + first.substring(1);
        last = last.toLowerCase();
        last = Character.toUpperCase(last.charAt(0)) + last.substring(1);

        List<Employee> employees = employeeDAO.findByFirstNameAndLastName(first, last);

        List<EmployeeResult> listEmployeeResult = new ArrayList<>();

        for (Employee employee : employees) {

            EmployeeResult employeeResult = new EmployeeResult();

            employeeResult.setFirstName(employee.getFirstName());
            employeeResult.setLastName(employee.getLastName());
            employeeResult.setDob(employee.getBirthDate());
            employeeResult.setHireDate(employee.getHireDate());

            Date now = new Date();

            //assumes only 1 title is relevant at a time
            employeeResult.setEmployeeTitle(employee
                    .getTitles()
                    .stream()
                    .filter(position -> position.getFromDate() != null && now.compareTo(position.getFromDate()) >= 0)  //filter for ones that have started already
                    .filter(position -> position.getToDate() == null || now.compareTo(position.getToDate()) < 0) //filter for ones that havent ended yet
                    .max(Comparator.nullsFirst(Comparator.comparing(Title::getFromDate))) //get the "max" from date
                    .map(Title::getTitle)
                    .orElse(EmployeeTitle.NONE));

            //assumes only 1 salary is relevant at a time
            employeeResult.setSalary(employee
                    .getSalaries()
                    .stream()
                    .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                    .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                    .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                    .map(wage -> wage.getPay())
                    .orElse(0.0));

            if(employeeResult.getSalary()!= 0){
                employeeResult.setFromDate(employee
                        .getSalaries()
                        .stream()
                        .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                        .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                        .filter(wage-> wage.getActive() == true)
                        .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                        .map(wage -> wage.getFromDate())
                        .get());

                employeeResult.setToDate(employee
                        .getSalaries()
                        .stream()
                        .filter(wage -> wage.getFromDate() != null && now.compareTo(wage.getFromDate()) >= 0)
                        .filter(wage -> wage.getToDate() == null || now.compareTo(wage.getToDate()) < 0)
                        .filter(wage-> wage.getActive() == true)
                        .max(Comparator.nullsFirst(Comparator.comparing(Salary::getFromDate)))
                        .map(wage -> wage.getToDate())
                        .get());

            }

            if (employeeResult.getEmployeeTitle() == EmployeeTitle.MANAGER) {
                //a person can manage many departments
                employeeResult.setDepartments(employee
                        .getDepartmentManager()
                        .stream()
                        .filter(depManager -> depManager.getFromDate() != null && now.compareTo(depManager.getFromDate()) >= 0)
                        .filter(depManager -> depManager.getToDate() == null || now.compareTo(depManager.getToDate()) < 0)
                        .map(DepartmentManager::getDepartment) //get the department
                        .map(Department::getDeptName) //get the department name
                        .collect(Collectors.toList()));
            } else {
                //a person can work at many departments
                employeeResult.setDepartments(employee
                        .getDepartmentEmployee()
                        .stream()
                        .filter(depEmployee -> depEmployee.getFromDate() != null && now.compareTo(depEmployee.getFromDate()) >= 0)
                        .filter(depEmployee -> depEmployee.getToDate() == null || now.compareTo(depEmployee.getToDate()) < 0)
                        .map(DepartmentEmployee::getDepartment)
                        .map(Department::getDeptName)
                        .collect(Collectors.toList()));
            }

            //check if
            if(employeeResult.getSalary()==0){
                employeeResult.setActive(false);
            }
            else{
                employeeResult.setActive(true);
            }

            employeeResult.setEmpNo(employee.getEmpNo());
            listEmployeeResult.add(employeeResult);

        }

        return listEmployeeResult;
    }
}

