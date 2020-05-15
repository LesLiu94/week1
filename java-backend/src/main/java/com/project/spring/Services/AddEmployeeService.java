package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DomainObjects.*;
import com.project.spring.DTO.EmployeeResult;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    private DepartmentDAO departmentDAO;

    private final static Logger logger = LogManager.getLogger(AddEmployeeService.class);

    public EmployeeResult addEmployee(EmployeeResult employeeRequest){

        logger.info("Adding employee by first name, last name, title, salary, birth date, hire date," +
                " gender, from date, and to date");
        EmployeeResult newEmployeeResult = new EmployeeResult();


        //checking to see if dates are in order
        Date fromDate = employeeRequest.getFromDate();
        Date toDate = employeeRequest.getToDate();
        Date hireDate = employeeRequest.getHireDate();
        Date birthDate = employeeRequest.getDob();

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

        //adjusting the employeeRequest dates' hours to 12
        //sometimes the dates are an hour off which sets the date to a day before the correct date
        employeeRequest.setFromDate(setHourToTwelve(employeeRequest.getFromDate()));
        employeeRequest.setToDate(setHourToTwelve(employeeRequest.getToDate()));
        employeeRequest.setHireDate(setHourToTwelve(employeeRequest.getHireDate()));
        employeeRequest.setDob(setHourToTwelve(employeeRequest.getDob()));

        //titles
        List<Title> titleList = new ArrayList<>();
        Title newEmployeeTitle = new Title();
        newEmployeeTitle.setTitle(employeeRequest.getEmployeeTitle());
        newEmployeeTitle.setFromDate(employeeRequest.getFromDate());
        newEmployeeTitle.setToDate(employeeRequest.getToDate());

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
        newEmployee.setBirthDate(employeeRequest.getDob());

        newEmployee.setHireDate(employeeRequest.getHireDate());
        newEmployee.setTitles(titleList);

        //Need to save employee first to generate the employee number.
        employeeDAO.save(newEmployee);

        newEmployeeTitle.setEmpNo(newEmployee.getEmpNo());
        titleDAO.save(newEmployeeTitle);

        //salaries
        List<Salary> salaryList = new ArrayList<>();
        Salary newEmployeeSalary = new Salary();
        newEmployeeSalary.setPay(employeeRequest.getSalary());

        newEmployeeSalary.setToDate(employeeRequest.getToDate());
        newEmployeeSalary.setFromDate(employeeRequest.getFromDate());
        newEmployeeSalary.setActive(true);

        newEmployeeSalary.setEmpNo(newEmployee.getEmpNo());
        salaryDAO.save(newEmployeeSalary);

        salaryList.add(newEmployeeSalary);
        newEmployee.setSalaries(salaryList);

        employeeDAO.save(newEmployee);

        //this finds DeptNo
        //because when adding an employee, they are only allowed one department to select, I am getting the 1st index.
        String[] departmentNameSplit = employeeRequest.getDepartments().get(0).split(" ");
        String departmentName="";
        for(String s:departmentNameSplit){
            s = (s != "and") ? s.charAt(0)+s.substring(1).toLowerCase():s.toLowerCase();
            departmentName = departmentName + s + " ";
        }

        //to get rid of the excess space
        departmentName= departmentName.substring(0,departmentName.length()-1);

        Department employeeRequestDepartment = departmentDAO.findByDeptName(departmentName);


        if(employeeRequest.getEmployeeTitle() == EmployeeTitle.MANAGER){
            DepartmentManager newDepartmentManager = new DepartmentManager();
            newDepartmentManager.setEmployee(newEmployee);
            newDepartmentManager.setFromDate(employeeRequest.getFromDate());
            newDepartmentManager.setToDate(employeeRequest.getToDate());
            newDepartmentManager.setDeptNo(employeeRequestDepartment.getDeptNo());
            newDepartmentManager.setDepartment(employeeRequestDepartment);
            //TODO Set Department Number
            newDepartmentManager.setEmpNo(newEmployee.getEmpNo());
            departmentManagerDAO.save(newDepartmentManager);

        }
        else{
            DepartmentEmployee newDepartmentEmployee = new DepartmentEmployee();
            newDepartmentEmployee.setEmployee(newEmployee);
            newDepartmentEmployee.setFromDate(employeeRequest.getFromDate());
            newDepartmentEmployee.setToDate(employeeRequest.getToDate());
            newDepartmentEmployee.setDeptNo(employeeRequestDepartment.getDeptNo());
            newDepartmentEmployee.setDepartment(employeeRequestDepartment);
            newDepartmentEmployee.setEmpNo(newEmployee.getEmpNo());
            departmentEmployeeDAO.save(newDepartmentEmployee);

        }


        //response
        newEmployeeResult.setFirstName(newEmployeeFirstName);
        newEmployeeResult.setLastName(newEmployeeLastName);
        newEmployeeResult.setSalary(employeeRequest.getSalary());
        newEmployeeResult.setEmployeeTitle(employeeRequest.getEmployeeTitle());
        newEmployeeResult.setActive(true);

        return newEmployeeResult;
    }

    //helper method to adjust the dates' hour to 12
    public Date setHourToTwelve(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 12);

        date = calendar.getTime();
        return date;
    }
}
