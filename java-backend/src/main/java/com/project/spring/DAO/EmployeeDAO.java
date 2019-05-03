package com.project.spring.DAO;

import com.project.spring.DTO.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Long> {
    Employee findByEmpNo(int empNo);
    Employee findByHireDate(Date hireDate);
}
