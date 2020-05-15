package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Service
@Transactional
public class FireEmployeeService {
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

    private final static Logger logger = LogManager.getLogger(FireEmployeeService.class);

    public String fireEmployee(Integer empNo){

        Employee firingEmployee = employeeDAO.findByEmpNo(empNo);

        Date now = new Date();

        for (Salary s: firingEmployee.getSalaries()) {
            if(s.getActive() == true){
                s.setActive(false);
                s.setToDate(now);
                salaryDAO.save(s);
            }
        }

        employeeDAO.save(firingEmployee);

        for (Title t: firingEmployee.getTitles()) {
            if(t.getToDate().compareTo(now) > 0){
                t.setToDate(now);
            }
        }
        Title firingEmployeeTitle = titleDAO.findByEmpNo(empNo);

        if(firingEmployeeTitle.getTitle() == EmployeeTitle.MANAGER){
            DepartmentManager firingDepartmentManager = departmentManagerDAO.findByEmpNo(empNo);
            firingDepartmentManager.setToDate(now);
            departmentManagerDAO.save(firingDepartmentManager);
        }
        else{
            DepartmentEmployee firingDepartmentEmployee = departmentEmployeeDAO.findByEmpNo(empNo);
            firingDepartmentEmployee.setToDate(now);
            departmentEmployeeDAO.save(firingDepartmentEmployee);
        }

        titleDAO.save(firingEmployeeTitle);

        return firingEmployee.getFirstName() + " " + firingEmployee.getLastName() + " is fired.";
    }

}
