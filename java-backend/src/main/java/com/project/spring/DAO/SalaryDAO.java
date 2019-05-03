package com.project.spring.DAO;

import com.project.spring.DTO.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryDAO extends JpaRepository<Salary,Long> {
    Salary findByEmpNo (int empNo);
}
