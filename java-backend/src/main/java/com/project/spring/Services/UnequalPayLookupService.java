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
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class UnequalPayLookupService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SalaryDAO salaryDAO;

    private final static Logger logger = LogManager.getLogger(UnequalPayLookupService.class);

    public ArrayList<UnequalLookupResult> findUnequallyPaidEmployees() {
        logger.info("Finding unequally paid employees");
        //get all employees
        List<Employee> employeeList = employeeDAO.findAll();

        //sort by hire date descending. so the most recent employees are first.
        Collections.sort(employeeList, Collections.reverseOrder(UnequalPayLookupUtilities.sortByHireDateComparator));

        List<Salary> salaryArrayList = new ArrayList<>();
        ArrayList<UnequalLookupResult> unequalResultList = new ArrayList<>();
        Date today = new Date();

        employeeList.forEach(employee -> {
            salaryArrayList.add(employee
                    .getSalaries()
                    .stream()
                    .filter(wage -> wage.getFromDate() != null && today.compareTo(wage.getFromDate()) >= 0)
                    .filter(wage -> wage.getToDate() != null && today.compareTo(wage.getToDate()) < 0)
                    .findFirst()
                    .orElse(null));
        });

        double maxSalary = 0.0;

        for (int i = 0; i < salaryArrayList.size(); i++) {
            if (salaryArrayList.get(i) == null) {
                continue;
            }
            if (maxSalary < salaryArrayList.get(i).getPay()) {
                maxSalary = salaryArrayList.get(i).getPay();
            }
            else {
                UnequalLookupResult currentUnequalEmployee = new UnequalLookupResult();
                currentUnequalEmployee.setFirstName(employeeList.get(i).getFirstName());
                currentUnequalEmployee.setLastName(employeeList.get(i).getLastName());

                //formatting date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dobString = formatter.format(employeeList.get(i).getBirthDate());
                currentUnequalEmployee.setBirthDate(dobString);

                Date hireDate = employeeList.get(i).getHireDate();
                currentUnequalEmployee.setHireDate(hireDate);
                currentUnequalEmployee.setSalary((salaryArrayList.get(i).getPay()));

                unequalResultList.add(currentUnequalEmployee);
            }
        }
        return unequalResultList;
    }
}
