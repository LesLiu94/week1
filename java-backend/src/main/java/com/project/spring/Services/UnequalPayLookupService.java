package com.project.spring.Services;

import com.project.spring.DAO.EmployeeDAO;
import com.project.spring.DAO.SalaryDAO;
import com.project.spring.DomainObjects.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class UnequalPayLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SalaryDAO salaryDAO;

    private final static Logger logger = LogManager.getLogger(UnequalPayLookupService.class);

    public ArrayList<Integer> findUnequallyPaidEmployees() {
        logger.info("Finding unequally paid employees");
       ArrayList<Integer> unequalResultSet = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.findAll();

        Collections.sort(employeeList,Collections.reverseOrder());

        int maxSalary = 0;

        for (Employee e: employeeList){
            if(maxSalary < e.getSalary().getPay()) {
                maxSalary = e.getSalary().getPay();
            }
            else {
                unequalResultSet.add(e.getEmpNo());
            }
        }

        return unequalResultSet;
    }
}
