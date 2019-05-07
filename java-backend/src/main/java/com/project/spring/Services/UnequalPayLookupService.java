package com.project.spring.Services;

import com.project.spring.DAO.EmployeeDAO;
import com.project.spring.DAO.SalaryDAO;
import com.project.spring.DTO.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
       ArrayList<Integer> unequalResultSet = new ArrayList<Integer>();
        List<Employee> employeeList = employeeDAO.findAll();

        //if the employee set is empty, return null
        if(isEmpty(employeeList)) {
            return null;
        }

        Collections.sort(employeeList);

        Employee seniorEmp, juniorEmp;
        /*for (int i = 0; i < (employeeList.size()-1); i++){
         seniorEmp = employeeList.get(i);
         for (int j = i+1; j <= (employeeList.size()-1); j++) {
            juniorEmp = employeeList.get(j);
            if(seniorEmp.getHireDate().before(juniorEmp.getHireDate())) {
                if(salaryDAO.findByEmpNo(seniorEmp.getEmpNo()).getPay() < salaryDAO.findByEmpNo(juniorEmp.getEmpNo()).getPay()) {
                    unequalResultSet.add(seniorEmp.getEmpNo());
                }
            }
            else {
                if(salaryDAO.findByEmpNo(seniorEmp.getEmpNo()).getPay() > salaryDAO.findByEmpNo(juniorEmp.getEmpNo()).getPay()) {
                    unequalResultSet.add(juniorEmp.getEmpNo());
                }
            }
         }
        }*/

        for (int i = 0; i < (employeeList.size()-1); i++){
            seniorEmp = employeeList.get(i);
            juniorEmp = employeeList.get(i+1);
            if(salaryDAO.findByEmpNo(seniorEmp.getEmpNo()).getPay() < salaryDAO.findByEmpNo(juniorEmp.getEmpNo()).getPay()) {
                unequalResultSet.add(seniorEmp.getEmpNo());
            }

        }

        return unequalResultSet;
    }
}
