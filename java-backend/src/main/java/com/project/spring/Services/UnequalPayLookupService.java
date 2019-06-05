package com.project.spring.Services;

import com.project.spring.DAO.EmployeeDAO;
import com.project.spring.DAO.SalaryDAO;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.DomainObjects.Salary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class UnequalPayLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SalaryDAO salaryDAO;

    private final static Logger logger = LogManager.getLogger(UnequalPayLookupService.class);

    @ResponseBody
    public ArrayList<String> findUnequallyPaidEmployees() {
        logger.info("Finding unequally paid employees");
       //get all employees
       List<Employee> employeeList = employeeDAO.findAll();

       //sort by hire date descending. so the most recent employees are first.
       Collections.sort(employeeList, Collections.reverseOrder(UnequalPayLookupUtilities.sortByHireDateComparator));

       String resultEmployee;
       List<Salary> salaryArrayList= new ArrayList<>();

       ArrayList<String> unequalResultList = new ArrayList<>();

       employeeList.forEach(employee -> salaryArrayList.add(employee.getSalaries().get(employee.getSalaries().size()-1)));

        int maxSalary = 0;

        for(int i = 0; i < salaryArrayList.size(); i++){
            if (maxSalary < salaryArrayList.get(i).getPay()){
                maxSalary = salaryArrayList.get(i).getPay();
            }
            else{
                unequalResultList.add(employeeList.get(i).toString());
            }
        }

        return unequalResultList;
    }
}
