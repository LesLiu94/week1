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
       ArrayList<String> unequalResultList = new ArrayList<>();
       String resultEmployee;
       List<Employee> employeeList = employeeDAO.findAll();
       List<List<Salary>> salaryListArrayList = new ArrayList<>();
       List<Salary> salaryArrayList= new ArrayList<>();

       Collections.sort(employeeList, Collections.reverseOrder(UnequalPayLookupUtilities.sortByHireDateComparator));
        employeeList.forEach(employee -> salaryListArrayList.add(employee.getSalaries()));
        salaryListArrayList.forEach(salaries -> salaryArrayList.add(salaries.get(salaries.size()-1)));

        int maxSalary = 0;
        List<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < salaryArrayList.size(); i++){
            if (maxSalary < salaryArrayList.get(i).getPay()){
                maxSalary = salaryArrayList.get(i).getPay();
            }
            else{
                indexes.add(i);
            }
        }
        for (int i: indexes){
            unequalResultList.add(employeeList.get(i).toString());
        }

        return unequalResultList;
    }
}
