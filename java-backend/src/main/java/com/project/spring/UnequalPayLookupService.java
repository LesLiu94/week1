package com.project.spring;

import com.project.spring.DAO.EmployeeDAO;
import com.project.spring.DAO.SalaryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
@Transactional
public class UnequalPayLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SalaryDAO salaryDAO;

    private final static Logger logger = LogManager.getLogger(UnequalPayLookupService.class);

    public ArrayList<String> findUnequallyPaidEmployees() {

    }
}
